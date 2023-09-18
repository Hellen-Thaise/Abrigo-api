package com.abrigo.repository;

import com.abrigo.model.AbrigoModel;
import com.abrigo.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByUsername(String username);
}
