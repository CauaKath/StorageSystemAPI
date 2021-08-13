package br.com.kath.storage.security;

import br.com.kath.storage.domain.exception.TrataException;
import br.com.kath.storage.domain.model.Pessoas;
import br.com.kath.storage.domain.repository.PessoasRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    private PessoasRepository pessoasRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoas pessoa = pessoasRepository.findByEmail(email);

        if(pessoa == null) {
            throw new TrataException("Usuário ou senha inválido.");
        }

        return new User(
                pessoa.getUsername(),
                pessoa.getPassword(),
                true,
                true,
                true,
                true,
                pessoa.getAuthorities()
        );
    }

}
