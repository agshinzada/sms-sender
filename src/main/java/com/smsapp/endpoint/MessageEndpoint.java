package com.smsapp.endpoint;

import com.smsapp.dbChecker.CheckerTask;
import com.smsapp.entity.Message;
import com.smsapp.model.ResponseMessage;
import com.smsapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/sms")
public class MessageEndpoint {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @PostConstruct
    public void startTask(){
        taskScheduler.scheduleWithFixedDelay(new CheckerTask(messageService),new Date(),1000);
        taskScheduler.scheduleWithFixedDelay(new CheckerTask(messageService),new Date(),3000);
        taskScheduler.scheduleWithFixedDelay(new CheckerTask(messageService),new Date(),5000);
        taskScheduler.scheduleWithFixedDelay(new CheckerTask(messageService),new Date(),7000);
        taskScheduler.scheduleWithFixedDelay(new CheckerTask(messageService),new Date(),9000);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody Message message) throws Exception {
        long result = validateMsisdn(message.getMsisdn());
        if(result!=-1) {message.setMsisdn(result);
        messageService.saveMessage(message);}
        else throw new Exception("msisdn not right format!");
    }



    @GetMapping("/send")
    public ResponseMessage getMessage(@RequestParam("msisdn") long msisdn, @RequestParam("sender") String sender, @RequestParam("text") String text) {

        boolean result = messageService.findByParam(msisdn, sender, text);
        if (result) {
            return new ResponseMessage(0, "OK");
        } else return new ResponseMessage(1, "FAIL");
    }

    @GetMapping("/test")
    public List<Message> messageList(){
        return messageService.findAll();
    }

    private long validateMsisdn(long number) {
        boolean result = Pattern.matches("^\\d{9}$",Long.toString(number));
        if(result) {
            String num = Long.toString(number);
            num = "994"+num;
            return Long.parseLong(num);
        }
        return -1;
    }
}
