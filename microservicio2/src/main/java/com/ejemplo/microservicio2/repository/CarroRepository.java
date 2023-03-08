package com.ejemplo.microservicio2.repository;

import com.ejemplo.microservicio2.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    List<Carro> findByUsuarioId(int usuarioId);

}
