package org.example.test.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.test.models.CreateUserDTO;
import org.example.test.questions.LaRespuestaCoincideConElCsv;
import org.example.test.tasks.ConsumeExecutePostUser;
import utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class TestStepDefinition {
    @Given("que cargo los usuarios desde el archivo {string}")
    public void queCargoLosUsuariosDesdeElArchivo(String archivo) {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Automatizador");
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at("https://reqres.in/"));

        List<CreateUserDTO> usuarios =
                CsvReader.leerUsuarios("src/test/resources/datadriven/" + archivo);

        theActorInTheSpotlight().remember("usuarios", usuarios);
    }
    @When("envío cada usuario al servicio")
    public void envíoCadaUsuarioAlServicio() {
        List<CreateUserDTO> usuarios =
                OnStage.theActorInTheSpotlight().recall("usuarios");

        List<String> respuestas = new ArrayList<>();

        usuarios.forEach(usuario -> {

            OnStage.theActorInTheSpotlight().attemptsTo(
                    ConsumeExecutePostUser.withInformationRequested("api/users", usuario)
            );

            // guardamos cada JSON en orden
            respuestas.add(SerenityRest.lastResponse().getBody().asString());
        });
        System.out.println("Repuestas: "+ respuestas);
        // guardamos todas las respuestas en memoria del actor
        OnStage.theActorInTheSpotlight().remember("respuestas", respuestas);
    }


    @Then("los usuarios son creados correctamente")
    public void losUsuariosSonCreadosCorrectamente() {
        List<CreateUserDTO> usuarios =
                OnStage.theActorInTheSpotlight().recall("usuarios");

        List<String> respuestas =
                OnStage.theActorInTheSpotlight().recall("respuestas");

        for (int i = 0; i < usuarios.size(); i++) {
            OnStage.theActorInTheSpotlight().should(
                    seeThat(LaRespuestaCoincideConElCsv.con(usuarios.get(i), respuestas.get(i)))
            );
        }

    }
}
