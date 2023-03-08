package com.ejemplo.microservicio.feignclients;

import com.ejemplo.microservicio.microservicios.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservicio-carro", url = "http://localhost:8080/carro")
public interface CarroFeignClient {
    @PostMapping
    public Carro save (@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable int usuarioId);
}
