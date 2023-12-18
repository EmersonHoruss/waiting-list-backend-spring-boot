package com.ownprojects.backend.DTOs.reponses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDTO {
    private String property;
    private String direction;
}
