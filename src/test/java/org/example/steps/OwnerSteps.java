
package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerSteps {

    private static final String BASE_URL =
            "http://localhost:9966/petclinic/api";

    private Response response;

    @Given("API PetClinic доступен")
    public void apiIsAvailable() {
        response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/owners");

        assertEquals(200, response.statusCode());
    }

    @When("я отправляю запрос на создание владельца с корректными данными")
    public void createOwner() {

        String requestBody = """
                {
                  "firstName": "George",
                  "lastName": "Franklin",
                  "address": "110 W. Liberty St.",
                  "city": "Madison",
                  "telephone": "6085551023"
                }
                """;

        response = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/owners");
    }

    @Then("код ответа должен быть {int}")
    public void checkStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @Then("владелец должен быть успешно создан")
    public void ownerShouldBeCreated() {
        assertNotNull(response.jsonPath().get("id"));
    }

    @When("я отправляю запрос на создание владельца без имени")
    public void createOwnerWithoutName() {

        String requestBody = """
                {
                  "lastName": "Smith",
                  "address": "123 Main Street",
                  "city": "Madison",
                  "telephone": "6085551234"
                }
                """;

        response = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/owners");
    }

    @Then("система должна вернуть ошибку")
    public void systemShouldReturnError() {
        assertTrue(response.statusCode() >= 400,
                "Ожидалась ошибка, но получен код: "
                        + response.statusCode());
    }

    @Given("владелец с ID {int} существует")
    public void ownerExists(int ownerId) {
        response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/owners/" + ownerId);

        assertEquals(200, response.statusCode());
    }

    @When("я запрашиваю информацию о владельце с ID {int}")
    public void getOwner(int ownerId) {
        response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/owners/" + ownerId);
    }

    @Then("информация о владельце должна быть возвращена")
    public void ownerInformationShouldBeReturned() {
        assertNotNull(response.jsonPath().get("firstName"));
        assertNotNull(response.jsonPath().get("lastName"));



    }


    @When("я создаю владельца {string} с фамилией {string}")
    public void createOwnerWithParameters(String firstName, String lastName) {

        String requestBody = """
            {
              "firstName": "%s",
              "lastName": "%s",
              "address": "123 Main Street",
              "city": "Madison",
              "telephone": "6085551234"
            }
            """.formatted(firstName, lastName);

        response = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/owners");
    }

}
