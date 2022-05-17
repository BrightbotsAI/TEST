package com.brightbots.restservices;

import com.brightbots.dto.RequestInsights;
import com.brightbots.dto.ResponseInsights;
import com.brightbots.processrequest.ProcessRequestInsights;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("InsightsService")
public class InsightsService {

    @Autowired
    private ProcessRequestInsights processRequestInsights;

    /**
     * Method for generate Insights from Watson Assistant.
     *
     * @param requestInsights Object with filters to be applied to the Insights
     * @return RestfulResponse This returns the whole results of the Insights including data for charts.
     */
    @PostMapping(value = "/getMostSearchedDocumentInfo")
    @Operation(summary = "Generate Insights from Watson Assistant.")
    public @ResponseBody
    ResponseEntity<ResponseInsights> getMostSearchedDocumentInfo(@RequestBody RequestInsights requestInsights) {
        return processRequestInsights.getMostSearchedDocumentInfo(requestInsights);
    }

    /**
     * Method for generate Insights from Watson Assistant.
     *
     * @param requestInsights Object with filters to be applied to the Insights
     * @return RestfulResponse This returns the whole results of the Insights including data for charts.
     */
    @PostMapping(value = "/getMostSearchedText")
    @Operation(summary = "Generate Insights from Watson Assistant.")
    public @ResponseBody
    ResponseEntity<ResponseInsights> getMostSearchedText(@RequestBody RequestInsights requestInsights) {
        return processRequestInsights.getMostSearchedText(requestInsights);
    }

    /**
     * Method for generate Insights from Watson Assistant.
     *
     * @param requestInsights Object with filters to be applied to the Insights
     * @return RestfulResponse This returns the whole results of the Insights including data for charts.
     */
    @PostMapping(value = "/getMostReturnedAnswers")
    @Operation(summary = "Generate Insights from Watson Assistant.")
    public @ResponseBody
    ResponseEntity<ResponseInsights> getMostReturnedAnswers(@RequestBody RequestInsights requestInsights) {
        return processRequestInsights.getMostReturnedAnswers(requestInsights);
    }

    /**
     * Method for generate Insights from Watson Assistant.
     *
     * @param requestInsights Object with filters to be applied to the Insights
     * @return RestfulResponse This returns the whole results of the Insights including data for charts.
     */
    @PostMapping(value = "/getFrustrationLevel")
    @Operation(summary = "Generate Insights from Watson Assistant.")
    public @ResponseBody
    ResponseEntity<ResponseInsights> getFrustrationLevel(@RequestBody RequestInsights requestInsights) {
        return processRequestInsights.getFrustrationLevel(requestInsights);
    }

    /**
     * Method for generate Insights from Watson Assistant.
     *
     * @param requestInsights Object with filters to be applied to the Insights
     * @return RestfulResponse This returns the whole results of the Insights including data for charts.
     */
    @PostMapping(value = "/getConversationalLogs")
    @Operation(summary = "Generate Insights from Watson Assistant.")
    public @ResponseBody
    ResponseEntity<ResponseInsights> getConversationalLogs(@RequestBody RequestInsights requestInsights) {
        return processRequestInsights.getConversationalLogs(requestInsights);
    }
}
