package com.bryan.book_service.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @ManyToOne(targetEntity = Gender.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private Gender gender;
    private String synopsis;
    private Boolean isAvailable;
}
