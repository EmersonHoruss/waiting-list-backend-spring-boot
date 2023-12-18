package com.ownprojects.backend.controllers;

import com.ownprojects.backend.DTOs.entities.*;
import com.ownprojects.backend.DTOs.reponses.ResponseDTO;
import com.ownprojects.backend.DTOs.requests.Deletion;
import com.ownprojects.backend.services.BaseServiceImpl;
import com.ownprojects.backend.utils.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ownprojects.backend.entities.Base;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class BaseController<
        E extends Base,
        CreateDTO extends BaseCreateDTO<E>,
        ShowDTO extends BaseShowDTO<E>,
        UpdateDTO extends BaseUpdateDTO<E>
        > {
    @Autowired
    private BaseServiceImpl<E> service;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody CreateDTO createDTO,
            UriComponentsBuilder uriBuilder,
            HttpServletRequest request
    ) {
        E entity = service.create(createDTO.asEntity());
        String requestUri = request.getRequestURI() + "/{id}";
        URI uri = uriBuilder.path(requestUri).buildAndExpand(entity.getId()).toUri();
        BaseShowDTO<E> dto = entity.asShowDTO();
        return ResponseEntity.created(uri).body(new ResponseDTO(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
        E entity = service.get(id);
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<E> page = service.get(new Specification<E>(query), pageable);
        System.out.println(pageable);
        List<BaseShowDTO> dtos = page
                .getContent()
                .stream()
                .map(e -> e.asShowDTO())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, query));
    }

    @PutMapping("")
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody UpdateDTO dto
    ) {
        E entity = service.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivate(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
