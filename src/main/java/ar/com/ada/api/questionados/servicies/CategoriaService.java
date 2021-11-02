package ar.com.ada.api.questionados.servicies;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.CategoriaModificada;
import ar.com.ada.api.questionados.repos.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repo;

    @Autowired
    PreguntaService preguntaService;

    public boolean existeCategoriaId(Integer categoriaId){
        if ((buscarCategoria(categoriaId)!=null)){
            return true;
        }
        else return false;
    }

    public void crearCategoria(Categoria categoria) {
        repo.save(categoria);

    }

    public List<Categoria> traerCategorias() {
        return repo.findAll();
    }

    public Categoria buscarCategoria(Integer id) {
        Optional <Categoria> resultado = repo.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        else return null;    
    }

    public void eliminarCategoria(Integer id){
        Categoria categoria= buscarCategoria(id);
        repo.delete(categoria);
    }

    
    
    public void modificarCategoria(Integer Id, CategoriaModificada categoriaModificada) {

        Categoria categoria = buscarCategoria(Id);
        categoria.setNombre(categoriaModificada.nombre);
        categoria.setDescripcion(categoriaModificada.descripcion);
        repo.save(categoria);

    }
    public List<Pregunta> obtenerPreguntasPorCategoria(Integer categoriaId){
        Categoria categoria= buscarCategoria(categoriaId);
              
        return categoria.getPreguntas();


    }

    //De esta forma sirve con LAZY y con EAGER
    public List<Pregunta> obtenerPreguntasPorCategoriaV2(Integer categoriaId){

        List<Pregunta> preguntas = preguntaService.traerPreguntas();
        List<Pregunta> preguntasCategoria= new ArrayList<>();

        for (Pregunta pregunta : preguntas) {
            if(pregunta.getCategoria().getCategoriaId().equals(categoriaId))
                preguntasCategoria.add(pregunta);
        }

        return preguntasCategoria;
       
    }
    
}
