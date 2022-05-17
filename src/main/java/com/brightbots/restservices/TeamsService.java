package com.brightbots.restservices;

import com.brightbots.dto.AdaptiveCard;
import com.brightbots.dto.RestfulResponse;
import com.brightbots.processrequest.ProcessRequestTeams;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("TeamsService")
public class TeamsService {

    private static final Logger log = Logger.getLogger(TeamsService.class.getName());

    @Autowired
    private ProcessRequestTeams processRequestTeams;

    @PostMapping(value = "/sendNotificationToAgent")
    @Operation(summary = "Send Adaptive Cards to Live Agent.")
    public @ResponseBody
    ResponseEntity<RestfulResponse> sendNotificationToAgent2(@RequestBody AdaptiveCard adaptiveCard) {
        return processRequestTeams.sendWebhookRequest(adaptiveCard);
    }
}
