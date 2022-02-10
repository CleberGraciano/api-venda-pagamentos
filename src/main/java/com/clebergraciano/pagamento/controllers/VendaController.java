package com.clebergraciano.pagamento.controllers;

import com.clebergraciano.pagamento.dtos.VendaDto;
import com.clebergraciano.pagamento.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;
    private final PagedResourcesAssembler<VendaDto> assembler; //Para hetoas, para retornar o link de um determinado produto

    @Autowired
    public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaDto> assembler) {
        this.vendaService = vendaService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public VendaDto findById(@PathVariable(name = "id") Long id){
        VendaDto vendaDto = vendaService.findById(id);
        vendaDto.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return vendaDto;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "12") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "data"));

        Page<VendaDto> vendaDtos = vendaService.findAll(pageable);
        vendaDtos.stream()
                .forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<VendaDto>> pagedModel = assembler.toModel(vendaDtos);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public VendaDto create(@RequestBody VendaDto vendaDto){
        VendaDto venDto = vendaService.create(vendaDto);
        venDto.add(linkTo(methodOn(VendaController.class).findById(venDto.getId())).withSelfRel());
        return venDto;
    }


}
