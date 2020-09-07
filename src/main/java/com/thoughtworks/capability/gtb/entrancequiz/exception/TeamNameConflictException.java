package com.thoughtworks.capability.gtb.entrancequiz.exception;

public class TeamNameConflictException extends IllegalArgumentException{
    public TeamNameConflictException(String message) {
        super(message);
    }
}
