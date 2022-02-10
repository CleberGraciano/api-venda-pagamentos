package com.clebergraciano.pagamento.dtos;

import com.clebergraciano.pagamento.entities.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "estoque"})
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto extends RepresentationModel<ProdutoDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("estoque")
    private Integer estoque;

    public static ProdutoDto create(Produto produto){
        return new ModelMapper().map(produto, ProdutoDto.class);
    }
}
