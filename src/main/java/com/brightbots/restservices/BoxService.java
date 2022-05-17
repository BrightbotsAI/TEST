package com.brightbots.restservices;

import com.brightbots.dto.RequestSharedLink;
import com.brightbots.dto.webhook.RequestWebwook;
import com.brightbots.dto.RestfulResponse;
import com.brightbots.processrequest.ProcessRequestBox;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("BoxService")
public class BoxService {

    @Autowired
    private ProcessRequestBox processRequestBox;

    /**
     * @param requestSharedLink This is the first parameter to addNum method
     * @return RestfulResponse This returns sum of numA and numB.
     */
    @GetMapping(value = "/getSharedLink")
    @Operation(summary = "Get Shared Link from box file.")
    public @ResponseBody
    ResponseEntity<RestfulResponse> getFileSharedLink(@RequestBody RequestSharedLink requestSharedLink) {
        return processRequestBox.generateSharedLinkExpiry(requestSharedLink.getItemId(), false);
    }

    @GetMapping(value = "/getSharedLinkExpiry")
    @Operation(summary = "Get Shared Link with Expiracy Date from box file.")
    public @ResponseBody
    ResponseEntity<RestfulResponse> getFileSharedLinkWithExpiry(@RequestBody RequestSharedLink requestSharedLink) {
        return processRequestBox.generateSharedLinkExpiry(requestSharedLink.getItemId(), true);
    }
    /**
     * @param requestWebwook This is the first parameter to addNum method
     * @return RestfulResponse This returns sum of numA and numB.
     */
    @PostMapping(value = "/webhookEndpoint")
    @Operation(summary = "Get Shared Link from box file coming from Webhook.")
    public @ResponseBody ResponseEntity<RestfulResponse> webhookEndpoint(@RequestBody RequestWebwook requestWebwook) {
        return processRequestBox.generateSharedLinkWebhook(requestWebwook);
    }
}
