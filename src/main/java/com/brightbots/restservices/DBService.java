package com.brightbots.restservices;

import com.brightbots.db.entity.WatsonAssistantChatEntity;
import com.brightbots.dto.RestfulResponse;
import com.brightbots.processrequest.ProcessRequestDB;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("DBService")
public class DBService {

    private static final Logger log = Logger.getLogger(DBService.class.getName());

    @Autowired
    private ProcessRequestDB processRequestDB;

    /**
     * Method for sto}re User chat from Watson Assistant.
     * @param watsonAssistantChatEntity Object that contain Watson Assistant Chat to be stored on DB.
     * @return RestfulResponse This returns the result of store the Watson Assistant Chat on DB.
     */
    @PostMapping(value = "/saveUserChat")
    @Operation(summary = "Save user chat from Watson Assistant.")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<RestfulResponse> getSharedLink(@RequestBody WatsonAssistantChatEntity watsonAssistantChatEntity) {
        return processRequestDB.saveUserChatWatsonAssistant(watsonAssistantChatEntity);
    }
}
