package com.brightbots.db.triggers;


import com.brightbots.db.MicrosoftTeamsChatRepository;
import com.brightbots.db.WatsonAssistantRepository;
import com.brightbots.db.WatsonAssistantResponseRepository;
import com.brightbots.dto.watsonchat.WatsonAssistantChat;
import com.brightbots.processrequest.ProcessRequestBox;
import com.brightbots.teams.MicrosoftGraphClient;
import com.brightbots.util.UtilDTO;
import com.brightbots.util.UtilHTTP;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * This class create a Schedule for obtain Logs and Chats coming from Watson Assistant API and Microsoft Teams
 * (via Microsoft Graph API) to save it in the DB2 Database
 *
 * <p>
 * By Brightbots Inc. All rights reserved.
 * <p>
 *
 * @since   2022-02-15
 * @version 1.0
 * @author  Cristian Perez
 *
 */
@Component
public class SaveChatSchedule {

    /**
     * Logger Object of the Class
     */
    private final Logger log = Logger.getLogger(SaveChatSchedule.class.getName());

    /**
     * Repository Object for handle information in the table watson_assistant_chat
     */
    @Autowired
    private WatsonAssistantRepository watsonAssistantRepository;

    /**
     * Repository Object for handle information in the table watson_assistant_chat_responses
     */
    @Autowired
    private WatsonAssistantResponseRepository watsonAssistantResponseRepository;

    /**
     * Repository Object for handle information in the table microsoft_teams_chat
     */
    @Autowired
    private MicrosoftTeamsChatRepository microsoftTeamsChatRepository;

    /**
     * Environment Spring Object
     */
    @Autowired
    private Environment env;

    /**
     * Object for handle connection and extract of Chats coming from Microsoft Teams
     */
    @Autowired
    private MicrosoftGraphClient microsoftGraphClient;

    /**
     * Util Object for handle HTTP Connections
     */
    @Autowired
    private UtilHTTP utilHTTP;

    /**
     * Util Object for handle DTO processing
     */
    @Autowired
    private UtilDTO utilDTO;

    @Autowired
    private ProcessRequestBox processRequestBox;

    /**
     * Reusable String for create BackUp CSV file for the table  watson_assistant_chat
     */
    private String csvBackupFileStringWAC = "contentChatId" + ",watsonChatId" + ",userName" + ",userEmail" + ",requestTimeStamp" + ",responseTimeStamp" + ",requestText" + "\n";

    /**
     * Reusable String for create BackUp CSV file for the table  watson_assistant_chat_responses
     */
    private String csvBackupFileStringWACR = "genericId" + ",watsonChatId" + ",responseType" + ",responseText" + "\n";

    /**
     * String with the Page Limit for consume Watson Assistant API
     */
    protected final String pageLimit = "&pageLimit=2000";

    /**
     * String for obtain JsonResponses from Watson Assistant API
     */
    protected String jsonResponse;

    /**
     * String for obtain filter the information coming from Watson Assistant API
     */
    protected String queryFilter;

    /**
     * String with the Main Part of the URL to consume Watson Assistant API.
     */
    protected String urlMain;

    /**
     * Reusable String with the Final Part of the URL to consume Watson Assistant API.
     */
    protected String urlFinal;


    /**
     * Integer for obtain the quantity of Logs obtained from Watson Assistant API.
     */
    private int countLogs = 0;

    /**
     * Integer for obtain the quantity of Generics(Internal Json Objects) obtained from Watson Assistant API.
     */
    private int countGenerics = 0;

    /**
     * Boolean to identify if the request haves more records or not.
     */
    protected boolean areMoreRecords;

    /**
     * Map of the additional properties to be sent to the Watson Assistant API (Like ApiKey).
     */
    private final Map<String, String> additionalProperties = new HashMap<>();

    /**
     * ObjectMapper to convert Json File in JAVA Object
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * DTO with the Json Structure obtained from Watson Assistant API.
     */
    protected WatsonAssistantChat watsonAssistantChat;


    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");


    /**
     * Runnable Object with the main process of this class. This one, will validate if the current time is Midnight or
     * not, if not, it will use the intervalSearchMidnight property to wait that quantity of time to verify again. If is
     * Midnight, the code will start the process. It will verify properties for BackUps to create CSV files and use or not
     * filters of time. The code will obtain the Logs from the Watson Assistant API and the Chats from Microsoft Teams
     * (Via Microsoft Graph API) and save this data in the DB2 DB. This process will verify only the last 24 hours of the
     * data, and this will run at Midnight every day. All of this for avoiding losing information, conversations or
     * relationships between them. The CSV files will be stored on a relative path, and then, saved in a Specific folder
     * in BOX.
     */
    private final Runnable runnable = () -> {
        log.info("Starting Midnight Verification");
        additionalProperties.put("Authorization", env.getProperty("ibm.watsonassistant.apikey"));
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            while (LocalDateTime.now().getHour() != 0) {
                log.info("Its : " + LocalDateTime.now().toLocalTime() + " - Waiting for Midnight 30 Minutes more!!!");
                Thread.sleep(Long.parseLong(Objects.requireNonNull(env.getProperty("save.chat.schedule.intervalSearchMidnight"))));
            }
            while (true) {
                areMoreRecords = true;
                log.info("Midnight!!! - Its time to start!!!");
                if (Boolean.parseBoolean(env.getProperty("save.chat.schedule.fullBackup"))) {
                    queryFilter = "";
                } else {
                    queryFilter = "&filter=(request_timestamp>" + LocalDateTime.now().minusDays(1) + "Z" + ")";
                }
                urlMain = env.getProperty("ibm.watsonassistant.url.main");
                urlFinal = env.getProperty("ibm.watsonassistant.url.final");
                while (areMoreRecords) {
                    jsonResponse = utilHTTP.httpConnectionGET(urlMain + urlFinal + queryFilter + pageLimit, additionalProperties);
                    if (jsonResponse != null) {
                        watsonAssistantChat = objectMapper.readValue(jsonResponse, WatsonAssistantChat.class);
                        log.info("Records Founded: " + watsonAssistantChat.getLogs().size());
                        countGenerics = 0;
                        watsonAssistantChat.getLogs().forEach(logInd -> {
                            countLogs = countLogs + 1;
                            if (Boolean.parseBoolean(env.getProperty("save.chat.schedule.backup"))) {
                                csvBackupFileStringWAC = csvBackupFileStringWAC + utilDTO.createWatsonAssistantChatEntity(logInd).toString();
                            }
                            //watsonAssistantRepository.save(utilDTO.createWatsonAssistantChatEntity(logInd));
                            countGenerics = 0;
                            logInd.getResponse().getOutput().getGeneric().forEach(indGeneric -> {
                                countGenerics = countGenerics + 1;
                                if (Boolean.parseBoolean(env.getProperty("save.chat.schedule.backup"))) {
                                    csvBackupFileStringWACR = csvBackupFileStringWACR + utilDTO.createWatsonAssistantChatResponses(indGeneric).toString();
                                }
                                //watsonAssistantResponseRepository.save(utilDTO.createWatsonAssistantChatResponses(indGeneric));
                                log.info("Cantidad de Interacciones Guardados en DB para un LOG: " + countGenerics);
                            });
                            log.info("Cantidad de LOGS Guardados en DB: " + countLogs);
                        });
                    }
                    if (watsonAssistantChat.getPagination().next_url != null) {
                        urlFinal = watsonAssistantChat.getPagination().next_url;
                    } else {
                        areMoreRecords = false;
                    }
                }
/*                // MICROSOFT TEAMS PROCESS
                ChatCollectionPage chats = microsoftGraphClient.getChatsFromUser(microsoftGraphClient.getMainUser().id);
                chats.getCurrentPage().forEach(chatInd -> {
                    if (chatInd.id != null) {
                        ChatMessageCollectionPage messages = microsoftGraphClient.getGraphClient().chats(chatInd.id).messages()
                                .buildRequest()
                                .get();
                        if (messages != null) {
                            AtomicReference<String> watsonChatId = new AtomicReference<>("");
                            messages.getCurrentPage().stream().sorted(Comparator.comparing(pageInd -> pageInd.createdDateTime, Comparator.nullsLast(Comparator.naturalOrder()))).forEach(pageInd -> {
                                if (pageInd.body != null && pageInd.body.content != null) {
                                    if (pageInd.body.content.contains("[") && pageInd.body.content.contains("]")) {
                                        if (Objects.equals(watsonChatId.get(), "")) {
                                            watsonChatId.set(pageInd.body.content.substring(pageInd.body.content.indexOf("["), pageInd.body.content.indexOf("]")));
                                        } else {
                                            if (!Objects.equals(watsonChatId.get(), pageInd.body.content.substring(pageInd.body.content.indexOf("["), pageInd.body.content.indexOf("]")))) {
                                                watsonChatId.set(pageInd.body.content.substring(pageInd.body.content.indexOf("["), pageInd.body.content.indexOf("]")));
                                            }
                                        }
                                        System.out.println(utilDTO.createMicrosoftTeamsChats(chatInd.id, watsonChatId, pageInd, chatInd.webUrl).toString());
                                        microsoftTeamsChatRepository.save(utilDTO.createMicrosoftTeamsChats(chatInd.id, watsonChatId, pageInd, chatInd.webUrl));
                                    } else {
                                        if (!Objects.equals(watsonChatId.get(), "")) {
                                            System.out.println(utilDTO.createMicrosoftTeamsChats(chatInd.id, watsonChatId, pageInd, chatInd.webUrl).toString());
                                            microsoftTeamsChatRepository.save(utilDTO.createMicrosoftTeamsChats(chatInd.id, watsonChatId, pageInd, chatInd.webUrl));
                                        } else {
                                            System.out.println("Date Created: " +
                                                    (pageInd.createdDateTime != null ? pageInd.createdDateTime : "No DateCreated") + " - Text: "
                                                    + (pageInd.body != null && pageInd.body.content != null ? pageInd.body.content : "No Body Content") + " - From: "
                                                    + (pageInd.from != null && pageInd.from.user != null && pageInd.from.user.displayName != null ? pageInd.from.user.displayName : "No User Name"));
                                        }
                                    }
                                } else {
                                    log.severe("pageInd.body OR pageInd.body.content = null - The chat will not saved on DB");
                                }
                            });
                        } else {
                            log.severe("Messages = null - The chat will not saved on DB");
                        }
                    } else {
                        log.severe("ChatId = null - The chat will not saved on DB");
                    }
                });*/

                if (Boolean.parseBoolean(env.getProperty("save.chat.schedule.backup"))) {
                    String dateCode = dateFormat.format(LocalDateTime.now());
                    String fileName = "TRM Watson Assistant Backup (WAC) " + dateCode + ".csv";
                    String finalPath = "BackupFiles\\" + fileName;
                    Files.write(Paths.get(finalPath), csvBackupFileStringWAC.getBytes());
                    processRequestBox.saveBoxFile(new FileInputStream(finalPath), fileName, "159010776156");
                    fileName = "TRM Watson Assistant Backup (WACR) " + dateCode + ".csv";
                    finalPath = "BackupFiles\\" + fileName;
                    Files.write(Paths.get(finalPath), csvBackupFileStringWACR.getBytes());
                    processRequestBox.saveBoxFile(new FileInputStream(finalPath), fileName, "159010776156");
                }
                Thread.sleep(Long.parseLong(Objects.requireNonNull(env.getProperty("save.chat.schedule.intervalSchedule"))));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    };

    /**
     * Thread for create the Schedule with the Runnable Object.
     */
    private final Thread verifyHour = new Thread(runnable);

    @PostConstruct
    private void createSchedule() {
        try {
            log.info("Creating new Thread TRMWA-Save-Chat");
            verifyHour.setName("TRMWA-Save-Chat");
            verifyHour.start();
            log.info("New Thread TRMWA-Save-Chat created and RUNNING!!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
