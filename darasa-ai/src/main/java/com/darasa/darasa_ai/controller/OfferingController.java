package com.darasa.darasa_ai.controller;

import com.darasa.darasa_ai.dto.OfferingDTO;
import com.darasa.darasa_ai.dto.CreateOfferingRequest;
import com.darasa.darasa_ai.dto.UpdateOfferingRequest;
import com.darasa.darasa_ai.model.Offering;
import com.darasa.darasa_ai.services.OfferingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offerings")
@CrossOrigin(origins = "*")
public class OfferingController {

    private final OfferingService offeringService;

    public OfferingController(OfferingService offeringService) {
        this.offeringService = offeringService;
    }

    @GetMapping
    public ResponseEntity<List<OfferingDTO>> getAllOfferings() {
        List<Offering> offerings = offeringService.getAllOfferings();
        List<OfferingDTO> offeringDTOs = offerings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(offeringDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferingDTO> getOffering(@PathVariable Long id) {
        return offeringService.getOfferingById(id)
                .map(offering -> ResponseEntity.ok(convertToDTO(offering)))
                .orElse(ResponseEntity.notFound().build());
    }

        @PostMapping
    public ResponseEntity<OfferingDTO> createOffering(@RequestBody @Valid CreateOfferingRequest request) {
        Offering offering = new Offering();
        offering.setCode(request.getCode());
        offering.setStartDate(request.getStartDate());
        offering.setEndDate(request.getEndDate());
        offering.setStartTime(request.getStartTime());
        offering.setEndTime(request.getEndTime());

        // Convert String days to DayOfWeek enum
        if (request.getDaysOfWeek() != null) {
            offering.setDaysOfWeek(request.getDaysOfWeek().stream()
                    .map(day -> Offering.DayOfWeek.valueOf(day.toUpperCase()))
                    .collect(java.util.stream.Collectors.toSet()));
        }

        Offering savedOffering = offeringService.createOffering(offering);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedOffering));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferingDTO> updateOffering(@PathVariable Long id,
                                                     @RequestBody @Valid UpdateOfferingRequest request) {
        return offeringService.getOfferingById(id)
                .map(existingOffering -> {
                    existingOffering.setCode(request.getCode());
                    existingOffering.setStartDate(request.getStartDate());
                    existingOffering.setEndDate(request.getEndDate());
                    existingOffering.setStartTime(request.getStartTime());
                    existingOffering.setEndTime(request.getEndTime());

                    // Convert String days to DayOfWeek enum
                    if (request.getDaysOfWeek() != null) {
                        existingOffering.setDaysOfWeek(request.getDaysOfWeek().stream()
                                .map(day -> Offering.DayOfWeek.valueOf(day.toUpperCase()))
                                .collect(java.util.stream.Collectors.toSet()));
                    }

                    Offering updatedOffering = offeringService.createOffering(existingOffering);
                    return ResponseEntity.ok(convertToDTO(updatedOffering));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffering(@PathVariable Long id) {
        if (offeringService.getOfferingById(id).isPresent()) {
            offeringService.deleteOffering(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private OfferingDTO convertToDTO(Offering offering) {
        OfferingDTO dto = new OfferingDTO();
        dto.setId(offering.getId());
        dto.setCode(offering.getCode());
        dto.setStartDate(offering.getStartDate());
        dto.setEndDate(offering.getEndDate());
        dto.setStartTime(offering.getStartTime());
        dto.setEndTime(offering.getEndTime());

        // Convert DayOfWeek enum to String
        if (offering.getDaysOfWeek() != null) {
            dto.setDaysOfWeek(offering.getDaysOfWeek().stream()
                    .map(Enum::name)
                    .collect(java.util.stream.Collectors.toSet()));
        }

        return dto;
    }
}