package org.example.test.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/testcsv.feature"},
        tags = "@testcsv",
        glue = "org.example.test.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {
}
