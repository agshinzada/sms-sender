package com.smsapp.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "msisdn", nullable = false)
    private long msisdn;

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "message_body", nullable = false)
    private String messageBody;

    @Column(name = "insert_date")
    @CreationTimestamp
    private Date insertDate;

    @Column(name = "done_date")
    private Date doneDate = null;

    @Column(name = "is_sent")
    private int isSent = 0;

    @Column(name = "status")
    private int status = 1;

    public Message() {
    }

    public Message(int msisdn, String senderName, String messageBody) {
        this.msisdn = msisdn;
        this.senderName = senderName;
        this.messageBody = messageBody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public void setIsSent(int isSent) {
        this.isSent = isSent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(long msisdn) {
        this.msisdn = msisdn;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public int getIsSent() {
        return isSent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msisdn=" + msisdn +
                ", senderName='" + senderName + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", insertDate=" + insertDate +
                ", doneDate=" + doneDate +
                ", isSent=" + isSent +
                ", status=" + status +
                '}';
    }
}
