package httpclient;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.RequestLoggingFilter.logRequestTo;
import static io.restassured.filter.log.ResponseLoggingFilter.logResponseTo;

import logger.MainLogger;
import org.apache.commons.io.output.WriterOutputStream;
import variables.Variable;

public class Requests {

    public Requests(){
        RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));
    }

    public Response get(String url) {
        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer,"UTF-8"),true);
        Response res =   RestAssured.given().filter(logResponseTo(captor)).and().filter(logRequestTo(captor)).when().get(url).then().extract().response();
        String logs = writer.toString();
        MainLogger.logger().debug(logs);
        Variable.getScenario().write(logs);
        return res;
    }

    public Object postRequestWithJsonBody(String endpoint, Object jsonBody) {
        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer,"UTF-8"),true);
        Object obj = RestAssured.given().filter(logResponseTo(captor)).and().filter(logRequestTo(captor)).
                accept("application/json")
                .contentType("application/json")
                .body(jsonBody)
                .expect()
                .statusCode(200)
                .log().all()
                .when().post(endpoint).then().extract().body().as(Object.class);
        String logs = writer.toString();
        MainLogger.logger().debug(logs);
        Variable.getScenario().write(logs);
        return obj;
    }

    public ResponseBodyExtractionOptions postRequestWithJsonBody(String endpoint, Map<String, String> headers) {
        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer,"UTF-8"),true);
        ResponseBodyExtractionOptions res =  RestAssured.given()
                .filter(logResponseTo(captor)).and().filter(logRequestTo(captor))
                .contentType("application/json")
                .headers(headers)
                .expect()
                .statusCode(200)
                .when().post(endpoint).then().extract().body();
        String logs = writer.toString();
        MainLogger.logger().debug(logs);
        Variable.getScenario().write(logs);
        return res;
    }

    public int update(String endPoint, Map<String, String> headers, Object body) {
        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer,"UTF-8"),true);
        int status =  RestAssured.given()
                .filter(logResponseTo(captor)).and().filter(logRequestTo(captor))
                .contentType("application/json")
                .headers(headers)
                .body(body)
                .expect()
                .log()
                .all()
                .when().put(endPoint).then().extract().response().statusCode();
        String logs = writer.toString();
        MainLogger.logger().debug(logs);
        Variable.getScenario().write(logs);
        return status;
    }

    public int delete(String endpoint, Map<String, String> headers) {
        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer,"UTF-8"),true);
        int status =  RestAssured.given()
                .filter(logResponseTo(captor)).and().filter(logRequestTo(captor))
                .contentType("application/json")
                .headers(headers)
                .expect()
                .when().delete(endpoint).then().extract().response().statusCode();
        String logs = writer.toString();
        MainLogger.logger().debug(logs);
        Variable.getScenario().write(logs);
        return status;
    }
}
