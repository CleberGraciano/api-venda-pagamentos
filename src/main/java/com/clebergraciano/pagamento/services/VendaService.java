package com.clebergraciano.pagamento.services;

import com.clebergraciano.pagamento.dtos.VendaDto;
import com.clebergraciano.pagamento.entities.ProdutoVenda;
import com.clebergraciano.pagamento.entities.Venda;
import com.clebergraciano.pagamento.exceptions.ResourceNotFundException;
import com.clebergraciano.pagamento.repositories.ProdutoVendaRepository;
import com.clebergraciano.pagamento.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaDto create(VendaDto vendaDto){
        Venda venda = vendaRepository.save(Venda.create(vendaDto));
        List<ProdutoVenda> produtoSalvos =  new ArrayList<>();
        vendaDto.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.create(p);
            pv.setVenda(venda);
            produtoSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtoSalvos);
        return VendaDto.create(venda);
    }

    public Page<VendaDto> findAll(Pageable pageable){
        var page = vendaRepository.findAll(pageable);
        return page.map(this::converterToVendaDto);
    }
    private VendaDto converterToVendaDto(Venda venda){
        return VendaDto.create(venda);
    }

    public VendaDto findById(Long id){
        var entity = vendaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFundException("No records found for this ID"));
        return VendaDto.create(entity);
    }


}
