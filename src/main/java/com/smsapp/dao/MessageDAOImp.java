package com.smsapp.dao;

import com.smsapp.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MessageDAOImp implements MessageDAO {

    @Autowired
    private EntityManager entityManager;

    private boolean status = true;
    private List<Message> messageList;

    @Override
    public void saveMessage(Message message) {
        entityManager.merge(message);
    }

    @Override
    public List<Message> findByParam(long msisdn, String sender, String text) {
        Query query = entityManager.createQuery("select m from Message m where m.msisdn=:number AND m.senderName=:sender AND m.messageBody=:body");
        query.setParameter("number", msisdn);
        query.setParameter("sender", sender);
        query.setParameter("body", text);
        return query.getResultList();
    }

    @Override
    public synchronized void updateMessage(List<Message> messages) {
        Query query = entityManager.createQuery("update Message m set m.isSent=1, m.doneDate=SYSDATE() where m.msisdn=:number AND m.senderName=:sender AND m.messageBody=:body");
        for (Message m : messages) {
            query.setParameter("number", m.getMsisdn());
            query.setParameter("sender", m.getSenderName());
            query.setParameter("body", m.getMessageBody());
            query.executeUpdate();
        }
    }

    @Override
    public synchronized List<Message> findAll() {
        Query query = entityManager.createQuery("select m from Message m where m.isSent=0").setMaxResults(10);
        messageList = query.getResultList();
        return messageList;
    }

}
