package com.ejemplo.microservicio.controller;

import com.ejemplo.microservicio.entities.Usuario;
import com.ejemplo.microservicio.microservicios.Carro;
import com.ejemplo.microservicio.microservicios.Moto;
import com.ejemplo.microservicio.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return ResponseEntity.ok(usuarioNuevo);
    }


    //RestTemplate
    @CircuitBreaker(name="carroCircuitBreaker", fallbackMethod = "defaultt")
    @GetMapping("/carro/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable int usuarioId){
        List<Carro> carros = usuarioService.getCarros(usuarioId);
        if (carros==null || carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
    @CircuitBreaker(name="motoCircuitBreaker", fallbackMethod = "defaultt")
    @GetMapping("/moto/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable int usuarioId){
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        if (motos==null || motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    //FeignClients
    @CircuitBreaker(name="carroCircuitBreaker", fallbackMethod = "defaultt")
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro, @PathVariable int usuarioId){
        Carro nuevoCarro= usuarioService.saveCarro(carro, usuarioId);
        return ResponseEntity.ok(nuevoCarro);
    }
    @CircuitBreaker(name="motoCircuitBreaker", fallbackMethod = "defaultt")
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto, @PathVariable int usuarioId){
        Moto nuevaMoto= usuarioService.saveMoto(moto, usuarioId);
        return ResponseEntity.ok(nuevaMoto);
    }
    @CircuitBreaker(name="todosCircuitBreaker", fallbackMethod = "defaultt")
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String,Object>> getCarrosyMotods(@PathVariable int usuarioId){
        Map<String,Object> resultado = usuarioService.getMotosyCarros(usuarioId);
        return ResponseEntity.ok(resultado);
    }


    //metodos para el circuit breaker
    private ResponseEntity<String> defaultt(@PathVariable int usuarioId){
        return new ResponseEntity("Usuario con id:" + usuarioId+"el microservicio a llamar está fallando", HttpStatus.OK);

    }

}
