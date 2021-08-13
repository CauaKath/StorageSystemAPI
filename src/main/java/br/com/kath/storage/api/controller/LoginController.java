package br.com.kath.storage.api.controller;

import br.com.kath.storage.api.assembler.PessoasAssembler;
import br.com.kath.storage.api.assembler.UsuarioAssembler;
import br.com.kath.storage.api.model.input.UsuarioInputDTO;
import br.com.kath.storage.domain.model.AuthenticationResponse;
import br.com.kath.storage.domain.model.Pessoas;
import br.com.kath.storage.security.ImplementsUserDetailsService;
import br.com.kath.storage.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private ImplementsUserDetailsService implementsUserDetailsService;
    private JWTUtil jwtUtil;

    private UsuarioAssembler usuarioAssembler;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UsuarioInputDTO usuarioInputDTO) throws Exception {
        Pessoas pessoas = usuarioAssembler.toEntity(usuarioInputDTO);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            pessoas.getUsername(), pessoas.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Usuario e/ou senha inválidos!", ex);
        }

        final UserDetails userDetails = implementsUserDetailsService.loadUserByUsername(
                pessoas.getUsername()
        );
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
