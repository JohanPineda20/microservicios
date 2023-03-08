package com.ejemplo.microservicio3.controller;

import com.ejemplo.microservicio3.entity.Moto;
import com.ejemplo.microservicio3.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> getAll(){
        List<Moto> motos = motoService.getAll();
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMotoById(@PathVariable int id){
        Moto moto = motoService.getMotoById(id);
        if(moto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }
    @PostMapping
    public ResponseEntity<Moto> save(@RequestBody Moto moto){
        Moto motoNueva = motoService.save(moto);
        return ResponseEntity.ok(motoNueva);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotoByUsuario(@PathVariable int usuarioId){
        List<Moto> moto = motoService.getMotoByUsuarioId(usuarioId);
        if(moto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(moto);
    }
}
