package activities;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity1 {
    public final String baseURI= "https://petstore.swagger.io/v2/pet";
    int petId=0;
    @Test(priority = 0)
    public void post(){

        Response response = given().contentType(ContentType.JSON).
                body("{\n" +
                        "  \"id\": 7,\n" +
                        "  \"name\" : \"BigBull\",\n" +
                        "  \"status\" : \"available\"\n" +
                        "}").
                when().post(baseURI);
        response.then().statusCode(200);
        response.then().body("id",equalTo(7));
        response.then().body("name",equalTo("BigBull"));
        response.then().body("status",equalTo("available"));
        System.out.println("post() Passed . ");
        petId = response.then().extract().path("id");


    }
    @Test(priority = 1)
    public void get(){
        Response response = given().contentType(ContentType.JSON).
                pathParam("petId",petId).
                when().get(baseURI+"/{petId}");
        response.then().statusCode(200);
        response.then().body("id",equalTo(petId));
        response.then().body("name",equalTo("BigBull"));
        response.then().body("status",equalTo("available"));
        System.out.println("get() Passed. ");
    }
    @Test(priority = 2)
    public void delete(){
        Response response = given().contentType(ContentType.JSON).
                pathParam("petId",petId).
                when().delete(baseURI+"/{petId}");
        response.then().statusCode(200);
        response.then().body("message",equalTo(String.valueOf(petId)));
        System.out.println("delete() Passed. ");

    }
}