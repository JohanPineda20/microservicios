package com.ejemplo.microservicio2.controller;

import com.ejemplo.microservicio2.entity.Carro;
import com.ejemplo.microservicio2.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> getAll(){
        List<Carro> carros = carroService.getAll();
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable int id){
        Carro carro = carroService.getCarroById(id);
        if(carro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }
    @PostMapping
    public ResponseEntity<Carro> save(@RequestBody Carro carro){
        Carro carroNuevo = carroService.save(carro);
        return ResponseEntity.ok(carroNuevo);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarroByUsuario(@PathVariable int usuarioId){
        List<Carro> carros = carroService.getCarroByUsuarioId(usuarioId);
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
}
