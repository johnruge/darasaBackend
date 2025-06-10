package com.darasa.darasa_ai.services;

import com.darasa.darasa_ai.model.Offering;
import com.darasa.darasa_ai.repository.OfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferingService {

    private final OfferingRepository offeringRepository;

    @Autowired
    public OfferingService(OfferingRepository offeringRepository) {
        this.offeringRepository = offeringRepository;
    }

    public List<Offering> getAllOfferings() {
        return offeringRepository.findAll();
    }

    public Optional<Offering> getOfferingById(Long id) {
        return offeringRepository.findById(id);
    }

    public Offering createOffering(Offering offering) {
        return offeringRepository.save(offering);
    }

    public void deleteOffering(Long id) {
        offeringRepository.deleteById(id);
    }
}
