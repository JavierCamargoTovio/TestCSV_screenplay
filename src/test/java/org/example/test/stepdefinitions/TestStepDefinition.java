package org.example.test.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.test.models.CreateUserDTO;
import org.example.test.tasks.ConsumeExecutePostUser;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class TestStepDefinition {
    @Given("que cargo los usuarios desde el archivo {string}")
    public void queCargoLosUsuariosDesdeElArchivo(String string) {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Automatizador");
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at("https://reqres.in/"));
    }
    @When("envío cada usuario al servicio")
    public void envíoCadaUsuarioAlServicio() {
        CreateUserDTO usuario = new CreateUserDTO();
        usuario.setName("Test");
        usuario.setJob("job");
    theActorInTheSpotlight().attemptsTo(ConsumeExecutePostUser.withInformationRequested("api/users",usuario));
    }
    @Then("los usuarios son creados correctamente")
    public void losUsuariosSonCreadosCorrectamente() {

    }
}
