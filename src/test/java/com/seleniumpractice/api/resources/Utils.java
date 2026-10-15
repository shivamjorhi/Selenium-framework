package com.seleniumpractice.api.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class Utils {
   public static RequestSpecification req;


    public RequestSpecification requestSpecification() throws IOException {
        if(req==null) {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        req = new RequestSpecBuilder()
            .setBaseUri(getGlobalValue("baseUrl"))
                .addQueryParam("key", "qaclick123")
            .setContentType(io.restassured.http.ContentType.JSON)
            .addFilter(RequestLoggingFilter.logRequestTo(log))
            .addFilter(ResponseLoggingFilter.logResponseTo(log))
            .build();
        return req;
        }
        return req;
        }

        public static String getGlobalValue(String key) throws IOException {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("src/test/java/com/seleniumpractice/api/resources/global.properties");
            prop.load(fis);
            String baseUrl = prop.getProperty(key);
            return baseUrl;
        }
}
