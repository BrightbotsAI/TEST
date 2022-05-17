package com.brightbots.processrequest;

import com.brightbots.dto.AdaptiveCard;
import com.brightbots.dto.RestfulResponse;
import com.brightbots.util.UtilRestful;
import com.brightbots.util.UtillJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class ProcessRequestTeams {

    private static final Logger log = Logger.getLogger(ProcessRequestTeams.class.getName());

    @Autowired
    private UtilRestful utilRestful;

    @Autowired
    private UtillJSON utiLlJSON;

    @Autowired
    private Environment env;

    private HttpURLConnection httpURLConnection;

    public ResponseEntity<RestfulResponse> sendWebhookRequest(AdaptiveCard adaptiveCard) {
        try {
            URL webhookURL = new URL(Objects.requireNonNull(env.getProperty("teams.webhook.url")));//your url i.e fetch data from .
            httpURLConnection = (HttpURLConnection) webhookURL.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            String adaptiveCardString = utiLlJSON.createAdaptiveCard(adaptiveCard).toString();
            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] inputByteArray = adaptiveCardString.getBytes(StandardCharsets.UTF_8);
                os.write(inputByteArray, 0, inputByteArray.length);
            }
            if (httpURLConnection.getResponseCode() != 200) {
                return new ResponseEntity<RestfulResponse>(utilRestful.getErrorResponse("Failed: Webhook HTTP error code: " + httpURLConnection.getResponseCode(), null, null, "Review Webhook Response", "Sorry service unavailable. Please contact support admin."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            log.info(utiLlJSON.createAdaptiveCard(adaptiveCard).toString());
            return new ResponseEntity<RestfulResponse>(utilRestful.getOkResponse("https://teams.microsoft.com/_#/conversations/?ctx=chat", "none", "The Agent will be in touch in a few minutes."), HttpStatus.OK);
        } catch (Exception ex) {
            log.severe(ex.getMessage());
            return new ResponseEntity<RestfulResponse>(utilRestful.getErrorResponse("Exception generated on the service", ex.toString(), ex.getMessage(), "Repeat or finished the process", "Sorry service unavailable. Please contact support admin."), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            httpURLConnection.disconnect();
        }
    }
}
