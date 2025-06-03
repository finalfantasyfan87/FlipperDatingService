package com.dating.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author jenst
 */
@Document(collection="user")
public class User{
    @Id
    private String id = UUID.randomUUID().toString();
    private String email;
    private String userName;
    private String profilePictureUrl;
    private String password;
    private String gender;
    private List<String> interests;
    private String zodiacSign;
    @Field("conversation_ids")
    private List<String> conversationIds = new ArrayList<>();

    public List<String> getConversationIds() {
        return conversationIds;
    }

    public void setConversationIds(List<String> conversationIds) {
        this.conversationIds = conversationIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", userName=" + userName + ", profilePictureUrl=" + profilePictureUrl + ", password=" + password + ", gender=" + gender + ",interests=" + interests + ", zodiacSign=" + zodiacSign + '}';
    }
    
    
    
} 
