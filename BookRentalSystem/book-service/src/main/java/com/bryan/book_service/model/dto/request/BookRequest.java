package com.bryan.book_service.model.dto.request;

import com.bryan.book_service.model.entities.Gender;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private String author;
    private Long genderId;
    private String synopsis;
    private Boolean isAvailable;
}
