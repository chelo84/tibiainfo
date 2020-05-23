package com.tibiainfo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageSupportDTO<T> {

    List<T> content;

    Long totalElements;

    Integer totalPages;

    Integer size;

    Integer numberOfElements;

    boolean empty;

    boolean last;

    public PageSupportDTO(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.size = page.getSize();
        this.numberOfElements = page.getNumberOfElements();
        this.empty = page.isEmpty();
        this.last = page.isLast();
    }

}
