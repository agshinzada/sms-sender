package com.smsapp.dao;

import com.smsapp.entity.Message;

import java.util.List;

public interface MessageDAO {
    void saveMessage(Message message);

    List<Message> findByParam(long msisdn, String sender, String text);

    void updateMessage(List<Message> messages);

    List<Message> findAll();
}
