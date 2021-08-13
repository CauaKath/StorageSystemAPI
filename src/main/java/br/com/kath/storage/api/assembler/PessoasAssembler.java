package br.com.kath.storage.api.assembler;

import br.com.kath.storage.api.model.PessoasDTO;
import br.com.kath.storage.api.model.input.PessoasInputDTO;
import br.com.kath.storage.domain.model.Pessoas;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PessoasAssembler {

    private ModelMapper modelMapper;

    public PessoasDTO toModel(Pessoas pessoas) {
        return modelMapper.map(pessoas, PessoasDTO.class);
    }

    public List<PessoasDTO> toCollectionModel(List<Pessoas> pessoas) {
        return pessoas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Pessoas toEntity(PessoasInputDTO pessoasInputDTO) {
        return modelMapper.map(pessoasInputDTO, Pessoas.class);
    }

}
