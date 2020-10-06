package com.smsapp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseMessage {

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;
}
