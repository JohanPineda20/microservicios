package com.ejemplo.microservicio.service;

import com.ejemplo.microservicio.entities.Usuario;
import com.ejemplo.microservicio.feignclients.CarroFeignClient;
import com.ejemplo.microservicio.feignclients.MotoFeignClient;
import com.ejemplo.microservicio.microservicios.Carro;
import com.ejemplo.microservicio.microservicios.Moto;
import com.ejemplo.microservicio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeignClient carroFeignClient;
    @Autowired
    private MotoFeignClient motoFeignClient;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario save(Usuario usuario){ return usuarioRepository.save(usuario); }


//RestTemplate
    public List<Carro> getCarros(int usuarioId){
        return restTemplate.getForObject("http://microservicio-carro/carro/usuario/" + usuarioId, List.class);
    }
    public List<Moto> getMotos(int usuarioId){
        return restTemplate.getForObject("http://microservicio-moto/moto/usuario/" + usuarioId, List.class);
    }

    
//FeignClient
    public Carro saveCarro(Carro carro, int usuarioId){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }
    public Moto saveMoto(Moto moto, int usuarioId){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }
    public Map<String, Object> getMotosyCarros(int usuarioId){
        Map<String,Object> resultado =new HashMap<>() ;
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null );
        if(usuario== null) {
            resultado.put("Usuario", "el usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", usuario);
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()){
            resultado.put ("Carros", "El usuario no tiene carros");
        }
        else{
            resultado.put ("Carros", carros );
        }
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()) {
            resultado.put("Motos", "El usuario no tiene motos");
        }
        else {
            resultado.put("Motos", motos);
        }
        return resultado;
    }
}