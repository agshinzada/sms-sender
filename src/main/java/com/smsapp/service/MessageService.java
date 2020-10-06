package com.smsapp.service;

import com.smsapp.entity.Message;

import java.util.List;

public interface MessageService {
    void saveMessage(Message message);

    boolean findByParam(long msisdn, String sender, String text);

    void updateMessage(List<Message> messages);

    List<Message> findAll();
}
