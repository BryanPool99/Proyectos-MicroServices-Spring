package com.bryan.book_service.services;

import com.bryan.book_service.model.dto.request.BookRequest;
import com.bryan.book_service.model.dto.request.GenderRequest;
import com.bryan.book_service.model.dto.response.BookResponse;
import com.bryan.book_service.model.dto.response.GenderResponse;
import com.bryan.book_service.model.entities.Book;
import com.bryan.book_service.model.entities.Gender;
import com.bryan.book_service.repositories.GenderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GenderService {
    @Autowired
    GenderRepository genderRepository;
    //agregar un nuevo genero literario
    public void addGender(GenderRequest genderRequest){

        var newGender= Gender.builder()
                .name(genderRequest.getName())
                .build();
        genderRepository.save(newGender);
        log.info("gender added: {}", newGender);
    }
    //listar generos literarios
    public List<GenderResponse> getAllGenders() {
        var genders = genderRepository.findAll();

        return genders.stream().map(this::mapToGenderResponse).toList();
    }
    private GenderResponse mapToGenderResponse(Gender gender) {
        return GenderResponse.builder()
                .id(gender.getId())
                .name(gender.getName())
                .build();
    }
    public void updateGender(Long id, GenderRequest genderRequest) {
        Gender existingGender = genderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));

        existingGender.setName(genderRequest.getName());
        genderRepository.save(existingGender);
        log.info("Gender updated: {}", existingGender);
    }
    public void deleteGender(Long id) {
        Gender existingGender = genderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));

        genderRepository.delete(existingGender);
        log.info("Gender deleted with id "+id);
    }

}
