package com.clebergraciano.pagamento.entities;

import com.clebergraciano.pagamento.dtos.VendaDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venda")
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyy")
    private Date data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
    private List<ProdutoVenda> produtos;

    @Column(name = "valorTotal", nullable = false, length = 10)
    private Double valorTotal;

    public static Venda create(VendaDto vendaDto){
        return new ModelMapper().map(vendaDto, Venda.class);
    }


}
