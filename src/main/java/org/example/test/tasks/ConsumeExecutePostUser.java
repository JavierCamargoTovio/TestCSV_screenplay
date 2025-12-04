package org.example.test.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.http.HttpStatus;
import org.example.test.exceptions.AssertionsServices;
import org.example.test.models.CreateUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.example.test.exceptions.AssertionsServices.EXCEPTION_ERROR_CONSUMPTION_SERVICE;
import static org.example.test.models.headers.GetHeaderModel.headersApiKey;

public class ConsumeExecutePostUser implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeExecutePostUser.class.getSimpleName());

    private final String endpointResource;
    private final CreateUserDTO usuario;

    public ConsumeExecutePostUser(String endpointResource, CreateUserDTO usuario) {
        this.endpointResource = endpointResource;
        this.usuario = usuario;
    }

    public static ConsumeExecutePostUser withInformationRequested(String endpointResource, CreateUserDTO usuario) {
        return instrumented(ConsumeExecutePostUser.class, endpointResource, usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        SerenityRest.reset();
        SerenityRest.useRelaxedHTTPSValidation();
        actor.attemptsTo(Post.to(endpointResource)
                .with(request -> request
                        .headers(headersApiKey())
                        .body(usuario)
                        .relaxedHTTPSValidation()
                        .log()
                        .all()
                )
        );
        if(SerenityRest.lastResponse().statusCode() != HttpStatus.SC_CREATED){
            throw new AssertionsServices(EXCEPTION_ERROR_CONSUMPTION_SERVICE);
        }
        lastResponse().peek();
    }
       

    }


