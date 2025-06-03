package com.dating.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
@Document(collection = "conversations")
@CompoundIndex(name = "participant_idx", def = "{'participantIds': 1}")
public class Conversation
{
    @Id
    private String id;
    @Field("participant_ids")
    private String [] participantIds;
    private LocalDateTime lastMessageTime;
    private String lastMessagePreview;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(String[] participantIds) {
        this.participantIds = participantIds;
    }

    public LocalDateTime getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(LocalDateTime lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public String getLastMessagePreview() {
        return lastMessagePreview;
    }

    public void setLastMessagePreview(String lastMessagePreview) {
        this.lastMessagePreview = lastMessagePreview;
    }
}
