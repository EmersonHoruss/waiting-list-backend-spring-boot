package com.ownprojects.backend.entities;

import javax.persistence.*;

import com.ownprojects.backend.DTOs.entities.BaseShowDTO;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Base<ShowDTO extends BaseShowDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract ShowDTO asShowDTO();
}
