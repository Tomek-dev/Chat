package com.example.chat.other.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Invite")
public class InviteNotFoundException extends RuntimeException{
    public InviteNotFoundException() {
        super("Invite not found.");
    }

    public InviteNotFoundException(String message) {
        super(message);
    }

    public InviteNotFoundException(Throwable cause) {
        super(cause);
    }
}
