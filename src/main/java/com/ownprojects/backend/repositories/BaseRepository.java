package com.ownprojects.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.ownprojects.backend.entities.Base;

@NoRepositoryBean
public interface BaseRepository <E extends Base> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}
