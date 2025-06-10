package com.darasa.darasa_ai.repository;
import com.darasa.darasa_ai.model.Offering;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferingRepository extends JpaRepository<Offering, Long> {
    Optional<Offering> findByCode(String code);
}
