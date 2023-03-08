package com.ejemplo.microservicio3.service;

import com.ejemplo.microservicio3.entity.Moto;
import com.ejemplo.microservicio3.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }
    public Moto save(Moto usuario){
        return motoRepository.save(usuario);
    }
    public List<Moto> getMotoByUsuarioId(int usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }
}
