package com.brightbots.processrequest;

import com.brightbots.assistant.InsightsEngine;
import com.brightbots.dto.RequestInsights;
import com.brightbots.dto.ResponseInsights;
import com.brightbots.util.UtilRestful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProcessRequestInsights {

    private static final Logger log = Logger.getLogger(ProcessRequestDB.class.getName());

    @Autowired
    private UtilRestful utilRestful;

    @Autowired
    private InsightsEngine insightsEngine;

    protected ResponseInsights responseInsights;

    public ResponseEntity<ResponseInsights> getMostSearchedDocumentInfo(RequestInsights requestInsights) {
        try {
            responseInsights = new ResponseInsights();
            responseInsights.setDynamicInsight(insightsEngine.obtainMostSearchedDocumentInfo(requestInsights));
            return new ResponseEntity<ResponseInsights>(utilRestful.getOKInsightsResponse(responseInsights), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.severe(ex.getMessage());
            return new ResponseEntity<ResponseInsights>(utilRestful.getErrorInsightsResponse(new ResponseInsights(), ex.toString(), ex.getMessage() + " - " + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseInsights> getMostSearchedText(RequestInsights requestInsights) {
        try {
            responseInsights = new ResponseInsights();
            responseInsights.setDynamicInsight(insightsEngine.obtainMostSearchedText(requestInsights));
            return new ResponseEntity<ResponseInsights>(utilRestful.getOKInsightsResponse(responseInsights), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.severe(ex.getMessage());
            return new ResponseEntity<ResponseInsights>(utilRestful.getErrorInsightsResponse(new ResponseInsights(), ex.toString(), ex.getMessage() + " - " + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseInsights> getMostReturnedAnswers(RequestInsights requestInsights) {
        try {
            responseInsights = new ResponseInsights();
            responseInsights.setDynamicInsight(insightsEngine.obtainMostAnsweredResponse(requestInsights));
            return new ResponseEntity<ResponseInsights>(utilRestful.getOKInsightsResponse(responseInsights), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.severe(ex.getMessage());
            return new ResponseEntity<ResponseInsights>(utilRestful.getErrorInsightsResponse(new ResponseInsights(), ex.toString(), ex.getMessage() + " - " + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseInsights> getFrustrationLevel(RequestInsights requestInsights) {
        try {
            responseInsights = new ResponseInsights();
            responseInsights.setDynamicInsight(insightsEngine.obtainFrustrationLevel(requestInsights));
            return new ResponseEntity<ResponseInsights>(utilRestful.getOKInsightsResponse(responseInsights), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.severe(ex.getMessage());
            return new ResponseEntity<ResponseInsights>(utilRestful.getErrorInsightsResponse(new ResponseInsights(), ex.toString(), ex.getMessage() + " - " + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseInsights> getConversationalLogs(RequestInsights requestInsights) {
        try {
            responseInsights = new ResponseInsights();
            responseInsights.setDynamicInsight(insightsEngine.obtainWatsonAssistantConversationalLog(requestInsights));
            return new ResponseEntity<ResponseInsights>(utilRestful.getOKInsightsResponse(responseInsights), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.severe(ex.getMessage());
            return new ResponseEntity<ResponseInsights>(utilRestful.getErrorInsightsResponse(new ResponseInsights(), ex.toString(), ex.getMessage() + " - " + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
