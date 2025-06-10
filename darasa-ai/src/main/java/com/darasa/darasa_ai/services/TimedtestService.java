package com.darasa.darasa_ai.services;

import com.darasa.darasa_ai.model.Timedtest;
import com.darasa.darasa_ai.repository.TimedtestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimedtestService {

    private final TimedtestRepository timedtestRepository;

    @Autowired
    public TimedtestService(TimedtestRepository timedtestRepository) {
        this.timedtestRepository = timedtestRepository;
    }

    public List<Timedtest> getAllTimedtests() {
        return timedtestRepository.findAll();
    }

    public Optional<Timedtest> getTimedtestById(Long id) {
        return timedtestRepository.findById(id);
    }

    public Timedtest createTimedtest(Timedtest timedtest) {
        return timedtestRepository.save(timedtest);
    }

    public void deleteTimedtest(Long id) {
        timedtestRepository.deleteById(id);
    }
}
