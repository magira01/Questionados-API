package ar.com.ada.api.questionados.models.response;

import java.util.*;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;

public class PreguntaAResolver {

    public Integer preguntaId;

    public Categoria categoria;

    public String enunciado;

    public List<OpcionPregunta> opciones = new ArrayList<>();


    public static PreguntaAResolver convertirDesde(Pregunta pregunta){//método de clase
        
        PreguntaAResolver preguntaAResolver= new PreguntaAResolver();
        
        preguntaAResolver.preguntaId= pregunta.getPreguntaId();
        preguntaAResolver.categoria=pregunta.getCategoria();
        preguntaAResolver.enunciado=pregunta.getEnunciado();

        

        for (Respuesta respuesta : pregunta.getOpciones()) {
            OpcionPregunta opcionPregunta = new OpcionPregunta();//si lo ponia afuera me ponia las misma pregunta 3  veces
            opcionPregunta.respuestaId=respuesta.getRespuestaId();
            opcionPregunta.texto=respuesta.getTexto();
            
            preguntaAResolver.opciones.add(opcionPregunta);
            
        }

        return preguntaAResolver;
    }

}
