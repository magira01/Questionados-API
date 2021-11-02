package ar.com.ada.api.questionados.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.InfoPreguntaNueva;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.servicies.PreguntaService;

@RestController
public class PreguntaController {
    
    @Autowired
    private PreguntaService service;

    @GetMapping("/preguntas")
    public ResponseEntity<List<Pregunta>> traerPreguntas(){//trae la lista de TODAS las preguntas        
        return ResponseEntity.ok(service.traerPreguntas());
    }

    @GetMapping("/preguntas/{id}")
    public ResponseEntity<Pregunta> traerPorPreguntaId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPregunta(id));
    }

    @PostMapping ("/preguntas")
    public ResponseEntity<?> crearPregunta(@RequestBody InfoPreguntaNueva preguntaNueva){
        //hay datos que NO se deben enviar por eso InfoPreguntaNueva 
        //preguntar porque hacemos InfopreguntaNueva
        GenericResponse respuesta= new GenericResponse();
        Pregunta pregunta = service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.categoriaId, preguntaNueva.opciones);

        respuesta.isOk=true;
        respuesta.id=pregunta.getPreguntaId();
        respuesta.message="Pregunta creada con éxito";

        return ResponseEntity.ok(respuesta);


    }
}
