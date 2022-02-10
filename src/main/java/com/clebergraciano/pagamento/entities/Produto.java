package com.clebergraciano.pagamento.entities;

import com.clebergraciano.pagamento.dtos.ProdutoDto;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    public static Produto create(ProdutoDto produtoDto){
        return new ModelMapper().map(produtoDto, Produto.class);
    }

}
