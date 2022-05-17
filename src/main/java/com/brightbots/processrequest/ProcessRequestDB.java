package com.brightbots.processrequest;
import com.brightbots.db.WatsonAssistantRepository;
import com.brightbots.db.entity.WatsonAssistantChatEntity;
import com.brightbots.dto.RestfulResponse;
import com.brightbots.util.UtilRestful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProcessRequestDB {

    private static final Logger log = Logger.getLogger(ProcessRequestDB.class.getName());

    @Autowired
    private UtilRestful utilRestful;

    @Autowired
    private WatsonAssistantRepository watsonAssistantRepository;

    public ResponseEntity<RestfulResponse> saveUserChatWatsonAssistant(WatsonAssistantChatEntity watsonAssistantChatEntity){
        try {
            watsonAssistantChatEntity = watsonAssistantRepository.save(watsonAssistantChatEntity);
            if (watsonAssistantChatEntity.getContentChatId() == null){
                return new ResponseEntity<RestfulResponse>(utilRestful.getErrorResponse("Failed: Saving Watson Assitant Chat to the DB: " + watsonAssistantChatEntity.getContentChatId(), null, null, "Review DB info sended.", "Sorry service unavailable. Please contact support admin."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<RestfulResponse>(utilRestful.getOkResponse("Chat saved correctly." + "\n ID: " + watsonAssistantChatEntity.getContentChatId(), "none", ""), HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            log.severe(ex.getMessage());
            return new ResponseEntity<RestfulResponse>(utilRestful.getErrorResponse("Exception generated on the service", ex.toString(), ex.getMessage(), "Repeat or finished the process", "Sorry service unavailable. Please contact support admin."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
