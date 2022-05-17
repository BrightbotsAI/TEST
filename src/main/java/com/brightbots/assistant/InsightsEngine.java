package com.brightbots.assistant;

import com.brightbots.db.WatsonAssistantRepository;
import com.brightbots.db.WatsonAssistantResponseRepository;
import com.brightbots.dto.RequestInsights;
import com.brightbots.dto.ResponseDynamicInsights;
import com.brightbots.dto.InsightsDTO;
import com.brightbots.dto.ResponseWatsonAssistantConversationalLog;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(InsightsEngine.N)
@Component
public class InsightsEngine {

    public static final int N = 10000;

    private final Logger log = Logger.getLogger(InsightsEngine.class.getName());

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMdd'T'hh:mm");

    @Autowired
    private WatsonAssistantRepository watsonAssistantRepository;

    @Autowired
    private WatsonAssistantResponseRepository watsonAssistantResponseRepository;

    protected InsightsDTO insightsDTO;

    private List<ResponseDynamicInsights> listDynamicResponse;

    private final List<String> frustrationLevelQuestions = Arrays.asList("Could you please try to rephrase your question?"
            , "Ok, let's try one more time. Could you please try to use different words?"
            , "Oh, no. I am sorry you feel that way. How can I do better?"
            , "Ouch. I'm sorry about that! Could you please rephrase your question?");

    @Benchmark
    public InsightsDTO obtainMostSearchedDocumentInfo(RequestInsights requestInsights) throws ParseException {
        log.info("Starting obtainMostSearchedDocumentInfo");
        listDynamicResponse = watsonAssistantRepository.obtainMostAskedDocument(formatter.parse(requestInsights.getFromDate()), formatter.parse(requestInsights.getToDate()));
        return fullDynamicInsights(listDynamicResponse);
    }

    @Benchmark
    public InsightsDTO obtainMostSearchedText(RequestInsights requestInsights) throws ParseException {
        log.info("Starting obtainMostSearchedText");
        listDynamicResponse = watsonAssistantRepository.obtainMostAskedText(formatter.parse(requestInsights.getFromDate()), formatter.parse(requestInsights.getToDate()));
        return fullDynamicInsights(listDynamicResponse);
    }

    @Benchmark
    public InsightsDTO obtainMostAnsweredResponse(RequestInsights requestInsights) throws ParseException {
        log.info("Starting obtainMostAnsweredResponse");
        listDynamicResponse = watsonAssistantResponseRepository.obtainMostAnsweredText(formatter.parse(requestInsights.getFromDate()), formatter.parse(requestInsights.getToDate()));
        listDynamicResponse.removeAll(listDynamicResponse.subList(0,4));
        listDynamicResponse.parallelStream().forEach(responseInd -> {
            responseInd.setSearchedText(responseInd.getSearchedText().substring(0, (Math.min(responseInd.getSearchedText().length(), 50))));
        });
        return fullDynamicInsights(listDynamicResponse);
    }

    @Benchmark
    public InsightsDTO obtainFrustrationLevel(RequestInsights requestInsights) throws ParseException {
        log.info("Starting obtainFrustrationLevel");
        listDynamicResponse = watsonAssistantResponseRepository.obtainFrustrationLevel(formatter.parse(requestInsights.getFromDate()), formatter.parse(requestInsights.getToDate()));
        frustrationLevelQuestions.forEach(questionInd -> {
            if(!listDynamicResponse.stream().map(ResponseDynamicInsights::getSearchedText).collect(Collectors.toList()).contains(questionInd)){
                ResponseDynamicInsights responseDynamicInsights = new ResponseDynamicInsights(questionInd, 0L);
                listDynamicResponse.add(responseDynamicInsights);
            }
        });
        return fullDynamicInsights(listDynamicResponse);
    }

    @Benchmark
    public InsightsDTO obtainWatsonAssistantConversationalLog(RequestInsights requestInsights) throws ParseException {
        log.info("Starting obtainWatsonAssistantConversationalLog");
        return basicDynamicInsights(watsonAssistantRepository.obtainWatsonAssistantConversationalLog(formatter.parse(requestInsights.getFromDate()), formatter.parse(requestInsights.getToDate()),
                requestInsights.getTypeResponse(), requestInsights.getWatsonChatId()));
    }

    @Benchmark
    private InsightsDTO fullDynamicInsights(List<ResponseDynamicInsights> dynamicInsightsList){
        insightsDTO = new InsightsDTO();
        if(dynamicInsightsList.size() > 0){
            dynamicInsightsList.sort(Comparator.comparing(ResponseDynamicInsights::getCountRecords).reversed());
            insightsDTO.setFinalHashMap(dynamicInsightsList.stream().sorted(Comparator.comparingLong(ResponseDynamicInsights::getCountRecords).reversed()).limit(20)
                    .collect(Collectors.toMap(ResponseDynamicInsights::getSearchedText, ResponseDynamicInsights::getCountRecords, (oldValue, newValue) -> oldValue, LinkedHashMap::new)));
            log.info("Obtaining MAX Values from MAP dynamicInsightsList");
            insightsDTO.setValue(dynamicInsightsList.stream().mapToLong(ResponseDynamicInsights::getCountRecords).max().getAsLong());
            insightsDTO.setName(dynamicInsightsList.stream().filter(v -> Objects.equals(v.getCountRecords(), insightsDTO.getValue())).findFirst().get().getSearchedText());
        } else {
            insightsDTO.setFinalHashMap(new HashMap<>());
            insightsDTO.setName("");
            insightsDTO.setValue(0L);
        }
        log.info("Finishing fullDynamicInsights");
        return insightsDTO;
    }

    @Benchmark
    private InsightsDTO basicDynamicInsights(List<ResponseWatsonAssistantConversationalLog> dynamicInsightsList){
        insightsDTO = new InsightsDTO();
        if(dynamicInsightsList.size() > 0){
            insightsDTO.setValue(null);
            insightsDTO.setName(null);
            insightsDTO.setFinalHashMap(null);
            insightsDTO.setFinalList(dynamicInsightsList);
        } else {
            insightsDTO.setValue(null);
            insightsDTO.setName(null);
            insightsDTO.setFinalHashMap(null);
            insightsDTO.setFinalList(new ArrayList<ResponseWatsonAssistantConversationalLog>());
        }
        log.info("Finishing fullDynamicInsights");
        return insightsDTO;
    }
}
