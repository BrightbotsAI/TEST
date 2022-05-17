package com.brightbots.teams;

import com.azure.identity.UsernamePasswordCredential;
import com.azure.identity.UsernamePasswordCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.ChatCollectionPage;
import com.microsoft.graph.requests.GraphServiceClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class MicrosoftGraphClient {

    private static final Logger log = Logger.getLogger(MicrosoftGraphClient.class.getName());

    @Autowired
    private Environment env;

    private TokenCredentialAuthProvider tokenCredentialAuthProvider;
    private UsernamePasswordCredential usernamePasswordCredential;
    private GraphServiceClient<Request> graphClient;

    private void initGraphClient(){
        System.out.println("HI");
        if(env != null && env.getProperty("teams.client.id") != null){
            System.out.println(env.getProperty("teams.client.id"));
            usernamePasswordCredential = new UsernamePasswordCredentialBuilder()
                    .clientId(env.getProperty("teams.client.id"))
                    .username(env.getProperty("teams.user.name"))
                    .password(env.getProperty("teams.user.pswd"))
                    .build();
            tokenCredentialAuthProvider = new TokenCredentialAuthProvider(usernamePasswordCredential);
            graphClient = GraphServiceClient.builder().authenticationProvider(tokenCredentialAuthProvider).buildClient();
        }
    }

    public GraphServiceClient<Request> getGraphClient() {
        return graphClient;
    }

    public User getInvitedUser(String userEmail){
        try {
            return graphClient.users(userEmail).buildRequest().get();
        }catch (Exception ex){
            log.severe(ex.getMessage() + " - " + ex.getLocalizedMessage());
            return null;
        }
    }

    public User getMainUser(){
        return graphClient.me().buildRequest().get();
    }

    public ChatCollectionPage getChatsFromUser(String userId){
        return graphClient.users(userId).chats().buildRequest().get();
    }
}
