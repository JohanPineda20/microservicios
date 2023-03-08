package com.ejemplo.microservicio2.service;

import com.ejemplo.microservicio2.entity.Carro;
import com.ejemplo.microservicio2.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAll(){
        return carroRepository.findAll();
    }

    public Carro getCarroById(int id){
        return carroRepository.findById(id).orElse(null);
    }
    public Carro save(Carro usuario){
        return carroRepository.save(usuario);
    }
    public List<Carro> getCarroByUsuarioId(int usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }
}
