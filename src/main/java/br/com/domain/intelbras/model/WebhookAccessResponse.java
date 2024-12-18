package br.com.domain.intelbras.model;

public class WebhookAccessResponse {
    private Boolean allowed;
    
    private String message;
    
    private String messageType;
    
    public WebhookAccessResponse() {
        super();
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WebhookAccessResponse{");
        sb.append("allowed=").append(allowed);
        sb.append(", message='").append(message).append('\'');
        sb.append(", messageType='").append(messageType).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
    public Boolean getAllowed() {
        return allowed;
    }
    
    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessageType() {
        return messageType;
    }
    
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    
}
