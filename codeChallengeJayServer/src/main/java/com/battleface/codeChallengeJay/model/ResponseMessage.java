package com.battleface.codeChallengeJay.model;


import lombok.*;

@Getter
@Setter
@ToString
public class ResponseMessage {
    private int statusCode;
    private String message;

    public ResponseMessage(int statusCode, String message) {
        this.message=message;
        this.statusCode=statusCode;
    }
}
