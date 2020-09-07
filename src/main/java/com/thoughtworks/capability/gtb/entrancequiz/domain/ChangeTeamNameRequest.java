package com.thoughtworks.capability.gtb.entrancequiz.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeTeamNameRequest {
    @JsonProperty(value = "oldName")
    private String oldName;

    @JsonProperty(value = "newName")
    private String newName;

    public ChangeTeamNameRequest() {
    }

    public ChangeTeamNameRequest(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
