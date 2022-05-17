package com.brightbots;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Info {
	@Value("${server.port}")
	int port;

	@EventListener(ApplicationReadyEvent.class)
	public void contextRefreshedEvent() {
		System.out.println("The following endpoints are available by default :-");
		System.out.println("  Health        : http://localhost:" + port + "/health");
		System.out.println("  Box Get Shared Link        : http://localhost:" + port + "/BoxService/getSharedLink/{fileId}");
		System.out.println("  Teams Send Notification to Agent       : http://localhost:" + port + "/TeamsService/sendNotificationToAgent");
		System.out.println("  DB save Chat from Watson Assistant       : http://localhost:" + port + "/DBService/saveUserChat");
		System.out.println("  Generate Insights       : http://localhost:" + port + "/InsightsService/generateInsights");
	}
}
