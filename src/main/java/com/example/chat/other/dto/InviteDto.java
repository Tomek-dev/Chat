package com.example.chat.other.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteDto {

    private Long invitedId;
    private Long invitingId;
    private String invitedUsername;
    private String invitingUsername;
}
