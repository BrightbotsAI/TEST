package com.brightbots.db;

import com.brightbots.dto.ResponseDynamicInsights;
import com.brightbots.db.entity.WatsonAssistantChatEntity;
import com.brightbots.dto.ResponseWatsonAssistantConversationalLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WatsonAssistantRepository extends CrudRepository<WatsonAssistantChatEntity, Integer> {

    @Query("SELECT NEW com.brightbots.dto.ResponseDynamicInsights(WAC.requestText, COUNT(WAC)) " +
            "FROM WatsonAssistantChatEntity WAC " +
            "WHERE WAC.contentChatId IN (SELECT WAR.watsonChatId.contentChatId FROM WatsonAssistantChatResponses WAR WHERE WAR.responseType = 'search') " +
            "AND WAC.requestTimeStamp BETWEEN :fromDate AND :toDate " +
            "GROUP BY WAC.requestText")
    List<ResponseDynamicInsights> obtainMostAskedDocument(@Param("fromDate") Date fromDate, @Param("toDate")Date toDate);

    @Query("SELECT NEW com.brightbots.dto.ResponseDynamicInsights(WAC.requestText, COUNT(WAC)) " +
            "FROM WatsonAssistantChatEntity WAC " +
            "WHERE WAC.requestText <> '' " +
            "AND WAC.requestTimeStamp BETWEEN :fromDate AND :toDate " +
            "GROUP BY WAC.requestText ")
    List<ResponseDynamicInsights> obtainMostAskedText(@Param("fromDate") Date fromDate, @Param("toDate")Date toDate);

    @Query("SELECT NEW com.brightbots.dto.ResponseWatsonAssistantConversationalLog(WAC.watsonChatId, WAC.requestText, WAC.requestTimeStamp, WACR.responseType, WACR.responseText, WAC.responseTimeStamp, WAC.userEmail) " +
            "FROM WatsonAssistantChatResponses WACR " +
            "JOIN WACR.watsonChatId WAC " +
            "WHERE (:responseType is null or WACR.responseType = :responseType)  " +
            "AND (:watsonChatId is null or WAC.watsonChatId = :watsonChatId) " +
            "AND WAC.requestTimeStamp BETWEEN :fromDate AND :toDate " +
            "ORDER BY WAC.requestText ")
    List<ResponseWatsonAssistantConversationalLog> obtainWatsonAssistantConversationalLog(@Param("fromDate") Date fromDate, @Param("toDate")Date toDate, @Param("responseType") String responseType, @Param("watsonChatId") String watsonChatId);
}

