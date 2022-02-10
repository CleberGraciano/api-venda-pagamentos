package com.clebergraciano.pagamento.dtos;

import com.clebergraciano.pagamento.entities.Venda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VendaDto extends RepresentationModel<VendaDto> implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long id;

    @JsonProperty("data")
    private Date data;

    @JsonProperty("produtos")
    private List<ProdutoVendaDto> produtos;

    @JsonProperty("valorTotal")
    private Double valorTotal;

    public static VendaDto create(Venda venda){
        return new ModelMapper().map(venda, VendaDto.class);
    }

}
