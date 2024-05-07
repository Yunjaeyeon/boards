package com.example.boards.dto;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.Message;

@Getter
@Setter
public class MessageDto {
    private String message;

    public MessageDto(String message){
        this.message = message;
    }


}
