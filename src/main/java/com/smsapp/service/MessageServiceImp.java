package com.smsapp.service;

import com.smsapp.dao.MessageDAO;
import com.smsapp.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImp implements MessageService{

    @Autowired
    private MessageDAO messageDAO;


    @Override
    @Transactional
    public void saveMessage(Message message) {
        messageDAO.saveMessage(message);
    }

    @Override
    @Transactional
    public boolean findByParam(long msisdn, String sender, String text) {
        List<Message> messages = messageDAO.findByParam(msisdn,sender,text);
        if (messages.isEmpty()) return false;
        for (Message m:messages){
            if(m.getIsSent()>0) return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void updateMessage(List<Message> messages) {
        messageDAO.updateMessage(messages);
    }

    @Override
    public List<Message> findAll() {
        return messageDAO.findAll();
    }
}
