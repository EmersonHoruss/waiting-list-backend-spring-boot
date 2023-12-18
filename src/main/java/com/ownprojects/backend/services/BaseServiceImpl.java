package com.ownprojects.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ownprojects.backend.entities.Base;
import com.ownprojects.backend.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;

public abstract class BaseServiceImpl<E extends Base> {
    @Autowired
    protected BaseRepository<E> baseRepository;

    @Transactional
    public E create(E entity) {
        return baseRepository.save(entity);
    }

    public E get(Long id) {
        return baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found, change later"));
    }

    public Page<E> get(Specification<E> specification, Pageable pageable) {
        return baseRepository.findAll(specification, pageable);
    }

    @Transactional
    public E update(E entity) {
        E foundEntity = baseRepository.getReferenceById(entity.getId());
        return baseRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        E entity = baseRepository.getReferenceById(id);
        baseRepository.delete(entity);
    }
}
