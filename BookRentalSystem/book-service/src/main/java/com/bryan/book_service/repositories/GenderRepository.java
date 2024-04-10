package com.bryan.book_service.repositories;

import com.bryan.book_service.model.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender,Long> {
}
