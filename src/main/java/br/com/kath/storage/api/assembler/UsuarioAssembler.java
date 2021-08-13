package br.com.kath.storage.api.assembler;

import br.com.kath.storage.api.model.UsuarioDTO;
import br.com.kath.storage.api.model.input.UsuarioInputDTO;
import br.com.kath.storage.domain.model.Pessoas;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsuarioAssembler {

    private ModelMapper modelMapper;

    public UsuarioDTO toModel(Pessoas pessoas) {
        return modelMapper.map(pessoas, UsuarioDTO.class);
    }

    public Pessoas toEntity(UsuarioInputDTO usuarioInputDTO) {
        return modelMapper.map(usuarioInputDTO, Pessoas.class);
    }

}
