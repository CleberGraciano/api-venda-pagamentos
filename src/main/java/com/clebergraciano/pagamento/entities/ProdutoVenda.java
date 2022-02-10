package com.clebergraciano.pagamento.entities;

import com.clebergraciano.pagamento.dtos.ProdutoVendaDto;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto_venda")
public class ProdutoVenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 10)
    private Long idProduto;

    @Column(name = "quantidade", nullable = false, length = 10)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public static ProdutoVenda create(ProdutoVendaDto produtoVendaDto){
        return new ModelMapper().map(produtoVendaDto, ProdutoVenda.class);
    }


}
