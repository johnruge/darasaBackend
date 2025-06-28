package com.darasa.darasa_ai.controller;

import com.darasa.darasa_ai.model.Timedtest;
import com.darasa.darasa_ai.services.TimedtestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timedtests")
@CrossOrigin(origins = "*")
public class TimedtestController {

    private final TimedtestService timedtestService;

    public TimedtestController(TimedtestService timedtestService) {
        this.timedtestService = timedtestService;
    }

    @GetMapping
    public ResponseEntity<List<Timedtest>> getAllTimedtests() {
        List<Timedtest> timedtests = timedtestService.getAllTimedtests();
        return ResponseEntity.ok(timedtests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timedtest> getTimedtest(@PathVariable Long id) {
        return timedtestService.getTimedtestById(id)
                .map(timedtest -> ResponseEntity.ok(timedtest))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Timedtest> createTimedtest(@RequestBody Timedtest timedtest) {
        Timedtest savedTimedtest = timedtestService.createTimedtest(timedtest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTimedtest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timedtest> updateTimedtest(@PathVariable Long id, @RequestBody Timedtest timedtestDetails) {
        return timedtestService.getTimedtestById(id)
                .map(existingTimedtest -> {
                    // Update fields here when Timedtest model has getters/setters
                    Timedtest updatedTimedtest = timedtestService.createTimedtest(existingTimedtest);
                    return ResponseEntity.ok(updatedTimedtest);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimedtest(@PathVariable Long id) {
        if (timedtestService.getTimedtestById(id).isPresent()) {
            timedtestService.deleteTimedtest(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}