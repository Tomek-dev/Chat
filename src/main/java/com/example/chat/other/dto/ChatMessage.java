package com.example.chat.other.dto;

import com.example.chat.other.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String sender;
    private String content;
    private Type type;
}
