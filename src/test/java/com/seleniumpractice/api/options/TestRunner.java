package com.seleniumpractice.api.options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@SuppressWarnings("deprecation")
@CucumberOptions(
    features = "src/test/resources/com/seleniumpractice/api/features/placeValidations.feature",
    glue = "com.seleniumpractice.api.steps"
)
public class TestRunner {
}