package com.esiho.world.scenarii;

import java.util.ArrayList;

public class Conversation {
    private Boolean isRead;
    private ArrayList<String> conversationIfNotRead;
    private ArrayList<String> conversationIfRead;

    public Conversation(ArrayList<String> conversationIfNotRead, ArrayList<String> conversationIfRead){
        isRead=false;
        this.conversationIfNotRead=conversationIfNotRead;
        this.conversationIfRead=conversationIfRead;
    }

    public String getPhrase(int index){
        if (isRead){
            return conversationIfRead.get(index);
        }else{
            return conversationIfNotRead.get(index);
        }
    }

    public ArrayList<String> getConversation(){
        if (isRead){
            return conversationIfRead;
        }else{
            return conversationIfNotRead;
        }
    }

    public ArrayList<String> getConversationIfNotRead() {
        return conversationIfNotRead;
    }

    public String getPhraseIfNotRead(int index) {
        return conversationIfNotRead.get(index);
    }

    public ArrayList<String> getConversationIfRead() {
        return conversationIfRead;
    }

    public String getPhraseIfRead(int index) {
        return conversationIfRead.get(index);
    }

    public void setRead() {
        isRead = true;
    }

    public Boolean isRead(){
        return isRead;
    }
}
