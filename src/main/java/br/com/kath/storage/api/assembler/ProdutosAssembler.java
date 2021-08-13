package br.com.kath.storage.api.assembler;

import br.com.kath.storage.api.model.ProdutosDTO;
import br.com.kath.storage.api.model.input.ProdutosInputDTO;
import br.com.kath.storage.domain.model.Produtos;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProdutosAssembler {

    private ModelMapper modelMapper;

    public ProdutosDTO toModel(Produtos produtos) {
        return modelMapper.map(produtos, ProdutosDTO.class);
    }

    public List<ProdutosDTO> toCollectionModel(List<Produtos> produtos) {
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList()
        );
    }

    public Produtos toEntity(ProdutosInputDTO produtosInputDTO) {
        return modelMapper.map(produtosInputDTO, Produtos.class);
    }

}
