package com.example.nodepos.model;

public class loginModel {
    private String status;
    private String responseMessage;
    private String responseCode;

    // Constructor
    public loginModel
    (
            String status,
            String reseponseMessage,
            String reseponseCode

    ) {
                this.status = status;
                this.responseMessage = reseponseMessage;
                this.responseCode = reseponseCode;





    }
    public String getStatus() { return status; }
    public String getReseponseMessage() { return responseMessage; }
    public String getReseponseCode() { return responseMessage; }



    public void setStatus(String status) {
        this.status = status;
    }

    public void setReseponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

}
