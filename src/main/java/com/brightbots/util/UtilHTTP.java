package com.brightbots.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class UtilHTTP {

    private final Logger log = Logger.getLogger(UtilHTTP.class.getName());

    private final String USER_AGENT = "JAVA/8.1.161";

    private String jsonResponse;

    private int httpResponseCode;

    private HttpURLConnection httpURLConnection;

    public String httpConnectionGET(String URL, Map<String, String> additionalProperties) throws IOException {
        try {
            URL webhookURL = new URL(Objects.requireNonNull(URL));//your url i.e fetch data from .
            httpURLConnection = (HttpURLConnection) webhookURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
            additionalProperties.forEach((key, value) -> {
                httpURLConnection.setRequestProperty(key, value);
            });
            httpResponseCode = httpURLConnection.getResponseCode();
            jsonResponse = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            if (httpResponseCode != 200) {
                log.severe("Response code different from 200 " + httpResponseCode + "\n JSON Response: " + jsonResponse);
                return null;
            } else {
                return jsonResponse;
            }
        } catch (Exception ex) {
            log.severe("Generated Exception on UtilHTTP: \n"
                    + "Message: " + ex.getMessage() + "\n"
                    + "Cause: " + ex.getCause() + "\n"
                    + "Localized Message: " + ex.getLocalizedMessage() + "\n"
                    + "Fill in StackTrace: " + ex.fillInStackTrace() + "\n");
            throw ex;
        } finally {
            httpURLConnection.disconnect();
        }
    }
}
