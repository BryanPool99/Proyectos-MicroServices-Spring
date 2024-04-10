package com.bryan.book_service.controllers;

import com.bryan.book_service.model.dto.request.GenderRequest;
import com.bryan.book_service.model.dto.response.GenderResponse;
import com.bryan.book_service.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genders")
public class GenderController {
    @Autowired
    private GenderService genderService;
    //listar los géneros literarios
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllGenders() {
        List<GenderResponse> genders = genderService.getAllGenders();
        return ResponseEntity.ok(genders);
    }
    //agregar un nuevo géneros literarios
    @PostMapping("/save")
    public ResponseEntity addGender(@RequestBody GenderRequest genderRequest) {
        genderService.addGender(genderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //actualizar los generos literarios
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGender(@PathVariable Long id, @RequestBody GenderRequest genderRequest){
        genderService.updateGender(id,genderRequest);
        return ResponseEntity.ok("Actualizacion exitosa");
    }
    //eliminar un género literario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenderById(@PathVariable Long id) {
        genderService.deleteGender(id);
        return ResponseEntity.ok().build();
    }
}
