package com.darasa.darasa_ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darasa.darasa_ai.model.Timedtest;

public interface TimedtestRepository extends JpaRepository<Timedtest, Long> {
    //no custom methods
}
