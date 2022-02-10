package com.clebergraciano.pagamento.dtos;

import com.clebergraciano.pagamento.entities.ProdutoVenda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "idProduto", "quantidade"})
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVendaDto extends RepresentationModel<ProdutoVendaDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduto")
    private Long idProduto;

    @JsonProperty("quantidade")
    private Integer quantidade;

    public static ProdutoVendaDto create(ProdutoVenda produtoVenda){
        return new ModelMapper().map(produtoVenda, ProdutoVendaDto.class);
    }
}
