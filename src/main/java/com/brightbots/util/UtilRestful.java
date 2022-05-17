package com.brightbots.util;

import com.brightbots.dto.ResponseInsights;
import com.brightbots.dto.RestfulResponse;
import org.springframework.stereotype.Component;

@Component
public class UtilRestful {
    public RestfulResponse getOkResponse(String message, String actions, String userMessage){
        return new RestfulResponse((short) 0,message,null,null,actions,userMessage);
    }

    public RestfulResponse getErrorResponse(String message, String exception, String messageException, String actions, String userMessage){
        return new RestfulResponse((short) 1,message,exception,messageException,actions,userMessage);
    }

    public ResponseInsights getOKInsightsResponse(ResponseInsights responseInsights){
        responseInsights.setResponseCode(0);
        responseInsights.setResponseMessage("Insights obtained correctly!!!.");
        return responseInsights;
    }

    public ResponseInsights getErrorInsightsResponse(ResponseInsights responseInsights, String exception, String messageException){
        responseInsights.setResponseCode(1);
        responseInsights.setResponseMessage("Error obtaining Insights. - " + exception + " - " + messageException);
        return responseInsights;
    }
}
