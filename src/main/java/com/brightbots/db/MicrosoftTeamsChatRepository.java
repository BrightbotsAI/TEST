package com.brightbots.db;

import com.brightbots.db.entity.MicrosoftTeamsChats;
import com.brightbots.db.entity.WatsonAssistantChatEntity;
import org.springframework.data.repository.CrudRepository;

public interface MicrosoftTeamsChatRepository extends CrudRepository<MicrosoftTeamsChats, Integer> {

}

