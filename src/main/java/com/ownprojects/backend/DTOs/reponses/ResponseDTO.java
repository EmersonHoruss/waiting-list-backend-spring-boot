package com.ownprojects.backend.DTOs.reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ResponseDTO {
    private Object content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageableDTO pageable;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SortDTO sort;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String query;

    public ResponseDTO(Object content, Page page, String query) {
        this(content);
        if (page != null) {
            this.pageable = new PageableDTO(page);
            this.sort = new SortDTO(page);
        }
        if (query == null) {
            this.query = "";
        } else {
            this.query = query;
        }
    }

    public ResponseDTO(Object content) {
        this.content = content;
    }
}
