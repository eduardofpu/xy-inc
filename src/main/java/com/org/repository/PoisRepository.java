package com.org.repository;

import com.org.model.Pois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoisRepository extends JpaRepository<Pois, Long>{
    List<Pois> findAll();
}
