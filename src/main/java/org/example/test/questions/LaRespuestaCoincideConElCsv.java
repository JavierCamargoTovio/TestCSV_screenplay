package org.example.test.questions;

import com.google.gson.Gson;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.example.test.models.CreateUserDTO;
import org.example.test.models.UsuarioResponse;
import org.example.test.tasks.ConsumeExecutePostUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


public class LaRespuestaCoincideConElCsv implements Question<Boolean> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LaRespuestaCoincideConElCsv.class);
    private final CreateUserDTO usuarioEsperado;
    private final String json;

    public LaRespuestaCoincideConElCsv(CreateUserDTO usuarioEsperado, String json) {
        this.usuarioEsperado = usuarioEsperado;
        this.json = json;
    }

    public static LaRespuestaCoincideConElCsv con(CreateUserDTO usuarioEsperado, String json) {
        return new LaRespuestaCoincideConElCsv(usuarioEsperado, json );
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        UsuarioResponse usuarioObtenido = new Gson().fromJson(json, UsuarioResponse.class);

        assertThat(usuarioObtenido.getId(), notNullValue());
        LOGGER.info("ID obtenido: {}", usuarioObtenido.getId());

        assertThat(usuarioObtenido.getName(), equalTo(usuarioEsperado.getName()));
        LOGGER.info("Nombres: nombre obtenido {} - nombre esperado {}", usuarioObtenido.getName(), usuarioEsperado.getName());

        assertThat(usuarioObtenido.getJob(), equalTo(usuarioEsperado.getJob()));
        LOGGER.info("Trabajo: trabajo obtenido {} - trabajo esperado {}", usuarioObtenido.getJob(), usuarioEsperado.getJob());

        assertThat(usuarioObtenido.getCreatedAt(), notNullValue());
        LOGGER.info("Fecha de creacion obtenida: {}", usuarioObtenido.getCreatedAt());
        return true;
    }
}