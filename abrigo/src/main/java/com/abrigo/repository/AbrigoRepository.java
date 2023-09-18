package com.abrigo.repository;

import com.abrigo.model.AbrigoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository <AbrigoModel, Long> {
}
