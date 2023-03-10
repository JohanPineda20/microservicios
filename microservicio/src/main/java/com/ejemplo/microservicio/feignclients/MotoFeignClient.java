package com.ejemplo.microservicio.feignclients;

import com.ejemplo.microservicio.microservicios.Carro;
import com.ejemplo.microservicio.microservicios.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservicio-moto",  url = "http://localhost:8080/moto")
public interface MotoFeignClient {
    @PostMapping
    public Moto save (@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    public List<Moto> getMotos(@PathVariable int usuarioId);
}
