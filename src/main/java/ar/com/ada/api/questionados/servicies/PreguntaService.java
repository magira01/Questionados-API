package ar.com.ada.api.questionados.servicies;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;
import ar.com.ada.api.questionados.repos.PreguntaRepository;

@Service
public class PreguntaService {
    @Autowired
    PreguntaRepository repo;

    @Autowired
    CategoriaService categoriaService;

    public List<Pregunta> traerPreguntas(){
        return repo.findAll();
    }

    public Pregunta buscarPregunta(Integer id) {
        Optional<Pregunta> resultado = repo.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        else return null;
    }
    
    //crea pregunta a partir de estos atributos
    public Pregunta crearPregunta(String enunciado, Integer categoriaId, List<Respuesta> opciones) {
        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado(enunciado);
        //necesito usar setCategoria para hacerlo bidireccional
        //pregunta.getCategoria().setCategoriaId(categoriaId);-->NO se puede
        Categoria categoria = new Categoria();
        categoria = categoriaService.buscarCategoria(categoriaId);
        pregunta.setCategoria(categoria);//hice la relacion bidireccional

        //usar setPregunta para relacion bidireccional
        //for(ar.com.ada.api.questionados.entities.Respuesta opcion: opciones){
        //    opcion.setPregunta(pregunta);
        //}
        repo.save(pregunta);
        return pregunta;
        
    }
}
