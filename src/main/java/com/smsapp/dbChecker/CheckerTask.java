package com.smsapp.dbChecker;

import com.smsapp.entity.Message;
import com.smsapp.service.MessageService;

import java.util.List;

public class CheckerTask implements Runnable{

    private MessageService messageService;
    private List<Message> messageList;

    public CheckerTask(MessageService messageService) {
        this.messageService = messageService;
    }


    @Override
    public void run() {
        messageList = messageService.findAll();
        if(!messageList.isEmpty()){
            System.out.println(messageList);
            messageService.updateMessage(messageList);
        }
        System.out.println("SMS not found!");
    }
}
