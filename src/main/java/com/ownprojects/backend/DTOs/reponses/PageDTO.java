package com.ownprojects.backend.DTOs.reponses;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageDTO {
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;
    private boolean last;

    public PageDTO(Page page) {
        size = page.getSize();
        number = page.getNumber();
        numberOfElements = page.getNumberOfElements();
        first = page.isFirst();
        last = page.isLast();
    }
}
