package com.ownprojects.backend.DTOs.reponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public class SortDTO {
    private List<OrderDTO> orders;

    public SortDTO(Page page){
        this.orders = new ArrayList<OrderDTO>();
        Iterator<Sort.Order> iterator = page.getSort().iterator();
        while(iterator.hasNext()){
            Sort.Order order = iterator.next();
            String property = order.getProperty();
            String direction = order.getDirection().name();
            this.orders.add(new OrderDTO(property, direction.toLowerCase()));
        }
    }
}
