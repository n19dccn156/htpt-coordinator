package com.htpt.coordinator.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpt.coordinator.Models.LogModel;
import com.htpt.coordinator.Repositories.LogRepository;

import reactor.core.publisher.Flux;

@Service
public class LogResponse {

    @Autowired LogRepository logRepository;
    
    public Flux<LogModel> findAll() {
        return Flux.interval(Duration.ofSeconds(2))
                .onBackpressureDrop()
                .map(this::readLog)
                .flatMapIterable(x -> x)
                .distinct(LogModel::getId);
    }

    private List<LogModel> readLog(long interval) {

        // Set<Employee> em = new HashSet<Employee>(employee.findAll());

        return logRepository.findAll();

    }
}
