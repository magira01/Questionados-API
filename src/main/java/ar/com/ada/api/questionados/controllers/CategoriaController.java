package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.CategoriaModificada;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.servicies.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;

    
    // GET Categorias
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> traerCategorias() {

        return ResponseEntity.ok(service.traerCategorias());
    }

    // GET Categoria por id
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> traerCategoriaPorId(@PathVariable Integer id){

        return ResponseEntity.ok(service.buscarCategoria(id));
    }

    @PostMapping("/categorias")//crea una categoria
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria){

        GenericResponse respuesta = new GenericResponse();

        //if (!(service.existeCategoriaId(categoria.getCategoriaId()))){ no id sino verificar nombre
            service.crearCategoria(categoria);

            respuesta.isOk=true;
            respuesta.message="Categoria creada con éxito";

            return ResponseEntity.ok(respuesta);

        //}
        //else {
         //   respuesta.isOk=false;
        //    respuesta.message="Ya existe una categoria con este Id";
        //    return ResponseEntity.badRequest().body(respuesta);
        //}
        
    } 
    @DeleteMapping("/categorias/{id}")//elimina una categoria por id
    public ResponseEntity<GenericResponse> eliminarCategoria(@PathVariable Integer id){

        GenericResponse respuesta = new GenericResponse();

        if(service.existeCategoriaId(id)){
            service.eliminarCategoria(id);

            respuesta.isOk=true;
            respuesta.message="Categoría eliminada correctamente";

            return ResponseEntity.ok(respuesta);
        }
        else {
            respuesta.isOk=false;
            respuesta.message="No existe una categoria con este Id";
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

    @PutMapping("/categorias/{id}") // actualiza una categoria existente
    public ResponseEntity<GenericResponse> actualizar(@PathVariable Integer id,
            @RequestBody CategoriaModificada categoriaModificada) {

        service.modificarCategoria(id, categoriaModificada);
        GenericResponse respuesta = new GenericResponse();

        respuesta.isOk = true;
        respuesta.message = "Categoria actualizada";

        return ResponseEntity.ok(respuesta);

            }


    //GET /categorias/{id}/preguntas -> todas las preguntas de una categoria especifica
    //esta funcionando mal y me trae 3 veces la misma pregunta
    @GetMapping("/categorias/{id}/preguntas")
    public ResponseEntity <List<Pregunta>> obtenerPreguntasPorCategoria(@PathVariable Integer id){
        //if(service.existeCategoriaId(id)){
           return ResponseEntity.ok(service.obtenerPreguntasPorCategoriaV2(id));
        //}
        //else return null;
    }

    
}
