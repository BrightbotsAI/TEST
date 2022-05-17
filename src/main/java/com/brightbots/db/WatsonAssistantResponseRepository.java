package com.brightbots.db;

import com.brightbots.dto.ResponseDynamicInsights;
import com.brightbots.db.entity.WatsonAssistantChatResponses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WatsonAssistantResponseRepository extends CrudRepository<WatsonAssistantChatResponses, Integer> {

    @Query("SELECT NEW com.brightbots.dto.ResponseDynamicInsights(WACR.responseText, COUNT(WACR)) " +
            "FROM WatsonAssistantChatResponses WACR " +
            "JOIN WACR.watsonChatId WAC " +
            "WHERE WACR.responseType <> 'pause' " +
            "AND WAC.responseTimeStamp BETWEEN :fromDate AND :toDate " +
            "GROUP BY WACR.responseText " +
            "ORDER BY COUNT(WACR) DESC ")
    List<ResponseDynamicInsights> obtainMostAnsweredText(@Param("fromDate") Date fromDate, @Param("toDate")Date toDate);



    @Query("SELECT NEW com.brightbots.dto.ResponseDynamicInsights(WACR.responseText, COUNT(WACR)) " +
            "FROM WatsonAssistantChatResponses WACR " +
            "JOIN WACR.watsonChatId WAC " +
            "WHERE WACR.responseText IN ('Could you please try to rephrase your question?' " +
            ",'Ok, let''s try one more time. Could you please try to use different words?' " +
            ",'Oh, no. I am sorry you feel that way. How can I do better?'\n" +
            ",'Ouch. I''m sorry about that! Could you please rephrase your question?' ) " +
            "AND WAC.responseTimeStamp BETWEEN :fromDate AND :toDate " +
            "GROUP BY WACR.responseText " +
            "ORDER BY COUNT(WACR) DESC")
    List<ResponseDynamicInsights> obtainFrustrationLevel(@Param("fromDate") Date fromDate, @Param("toDate")Date toDate);
}
