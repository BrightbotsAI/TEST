package com.brightbots.mvc.controller;

import com.brightbots.assistant.InsightsEngine;
import com.brightbots.dto.InsightsDTO;
import com.brightbots.dto.RequestInsights;
import com.brightbots.dto.ResponseWatsonAssistantConversationalLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private InsightsEngine insightsEngine;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("requestMostSearchedDocumentInfo", new RequestInsights());
        model.addAttribute("requestMostSearchedText", new RequestInsights());
        model.addAttribute("requestMostAnsweredResponse", new RequestInsights());
        model.addAttribute("requestFrustrationLevel", new RequestInsights());
        model.addAttribute("requestWatsonAssistantConversationalLog", new RequestInsights());
        model.addAttribute("insightsEngine", insightsEngine);
        return "index";
    }
}
