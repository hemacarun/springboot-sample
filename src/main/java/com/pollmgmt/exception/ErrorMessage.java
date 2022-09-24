package com.pollmgmt.exception;

import java.util.Date;

public class ErrorMessage {

    private  int errorId;
    private String errorMessage;
    private Date timeStamp;

    public ErrorMessage(int errorId, String errorMessage, Date timeStamp) {

        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }



    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }
}
