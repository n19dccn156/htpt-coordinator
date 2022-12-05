package com.htpt.coordinator.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htpt.coordinator.Models.LogModel;

@Repository
public interface LogRepository extends JpaRepository<LogModel, Integer>{
    
    public List<LogModel> findAll();

}
