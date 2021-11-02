package ar.com.ada.api.questionados.servicies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;

@Service
public class QuestionadosService {

    @Autowired
    PreguntaService preguntaService;

    public Pregunta traerPreguntaRandom() {

        // traer la lista de pregnta y guardarlo en la variable tipo List,
        // de esta manera evito llamar a la lista dos veces
        List<Pregunta> todasLasPreguntas = preguntaService.traerPreguntas();
        int min = 1;
        int max = todasLasPreguntas.size();
        int random = (int) (Math.random() * ((max - min) + 1)) + min;
        return todasLasPreguntas.get(random - 1);

    }

    public boolean verificarRespuesta(Integer preguntaId, Integer respuestaId) {

        Pregunta pregunta = preguntaService.buscarPregunta(preguntaId);

        for (Respuesta respuesta : pregunta.getOpciones()) { // recorrer listas foreach
            if (respuesta.getRespuestaId().equals(respuestaId)) {
                return respuesta.isEsCorrecta();
            }

        }
        return false;

    }

}
