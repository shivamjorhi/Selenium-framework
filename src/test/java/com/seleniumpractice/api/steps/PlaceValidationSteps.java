package com.seleniumpractice.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.Then;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.seleniumpractice.api.resources.APIResources;
import com.seleniumpractice.api.resources.TestDataBuild;
import com.seleniumpractice.api.resources.Utils;

public class PlaceValidationSteps extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();

    @Given("Add PLace Payload with {string} {string} {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {
        resspec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

        res = given()
            .spec(requestSpecification())
            .body(data.addPlacePayload(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void userCallsApiWithHttpRequest(String resourceName, String method) {
        APIResources resourceAPI = APIResources.valueOf(resourceName);
        var request = res.when();

        switch (method.toUpperCase()) {
            case "POST":
                response = request.post(resourceAPI.getResource());
                break;
            case "GET":
                response = request.get(resourceAPI.getResource());
                break;
            default:
                fail("Unsupported HTTP method: " + method);
        }

        response = response.then()
                .spec(resspec)
                .extract()
                .response();

    }

    @Then("the API call is success with status code {int}")
    public void apiCallIsSuccessWithStatusCode(int statusCode) {
        assertNotNull("The API response was not created", response);
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then( "{string} in response body is {string}")
    public void responseBodyFieldIs(String key, String value) {
        assertNotNull("The API response was not created", response);
        String resp=response.asString();
        System.out.println(resp);
        JsonPath js = new JsonPath(resp);
        assertEquals(value, js.getString(key));
    }

    @Then("verify place_Id created maps to {string} using {string} API")
    public void verify_place_id_created_maps_to_using_api(String expectedName, String resource) throws IOException {
        String placeId = response.jsonPath().getString("place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", placeId);
        userCallsApiWithHttpRequest(resource, "GET");
        assertEquals(expectedName, response.jsonPath().getString("name"));
    }
}
