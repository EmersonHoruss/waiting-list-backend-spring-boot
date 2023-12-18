package com.ownprojects.backend.DTOs.reponses;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageableDTO {
    private PageDTO page;
    private int totalPages;
    private long totalElements;

    public PageableDTO(Page page){
        this.page = new PageDTO(page);
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
    }
}
