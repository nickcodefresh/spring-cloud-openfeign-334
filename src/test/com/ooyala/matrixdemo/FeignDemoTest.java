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

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/server/matrixParams;account=a;name=n"))
                                 .willReturn(aResponse().withStatus(200)));
    }


    @Test
    public void internalMatrixParamsMap() throws IOException {

        runTest("http://localhost:8080/client/matrixParamsMap");

    }


    @Test
    public void internalMatrixParams() throws IOException {

        runTest("http://localhost:8080/client/matrixParams");

    }


    @Test
    public void wiremockMatrixParamsMap() throws IOException {

        runTest("http://localhost:7000/client/matrixParamsMap");

    }


    @Test
    public void wiremockMatrixParams() throws IOException {

        runTest("http://localhost:7000/client/matrixParams");

    }


    private void runTest(String url) throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(url);

            client.execute(request, httpResponse -> {
                assertThat(httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value());
                return httpResponse;
            });

        }

    }

}