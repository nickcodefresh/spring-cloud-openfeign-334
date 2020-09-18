package com.ooyala.matrixdemo;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;


public class FeignDemoTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(7000);


    @Before
    public void setUp() {

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/server;account=a;name=n"))
                                 .willReturn(aResponse().withStatus(200)));
    }


    @Test
    public void matrixParamsMap() throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("http://localhost:8080/api/matrixParamsMap");

            // This REST call will fail because Spring Cloud is encoding the matrix parameters so that the request is]
            // /api/server%3Bname%3D%5Bn%5D%3Baccount%3D%5Ba%5D rather than /api/server;account=a;name=n
            client.execute(request, httpResponse -> {
                assertThat(httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value());
                return httpResponse;
            });

        }

    }


    @Test
    public void matrixParams() throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("http://localhost:8080/api/matrixParams");

            client.execute(request, httpResponse -> {
                assertThat(httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value());
                return httpResponse;
            });

        }

    }


    @Test
    public void wireMockMatrixParamsMap() throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("http://localhost:8080/api/matrixParamsMap");

            // This REST call will fail because Spring Cloud is encoding the matrix parameters so that the request is]
            // /api/server%3Bname%3D%5Bn%5D%3Baccount%3D%5Ba%5D rather than /api/server;account=a;name=n
            client.execute(request, httpResponse -> {
                assertThat(httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value());
                return httpResponse;
            });

        }

    }


    @Test
    public void wireMockMatrixParams() throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("http://localhost:8080/api/matrixParams");

            client.execute(request, httpResponse -> {
                assertThat(httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value());
                return httpResponse;
            });

        }

    }

}