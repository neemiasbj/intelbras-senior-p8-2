package br.com.domain.intelbras.model;

import br.com.domain.intelbras.model.enums.AccessDirectionEnum;
import br.com.domain.intelbras.model.enums.AccessTypeEnum;
import br.com.domain.intelbras.model.enums.OnOffStatusEnum;

public class WebhookEndAccessModel {
    
    public enum ApiAccessEndEnum {
                                  ACCOMPLISHED,
                                  DENIED,
                                  DESISTENCE
    }
    
    private String registry;
    
    private String date;
    
    private AccessDirectionEnum direction;
    
    private OnOffStatusEnum status;
    
    private AccessTypeEnum accessType;
    
    public String getRegistry() {
        return registry;
    }
    
    public void setRegistry(String registry) {
        this.registry = registry;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public AccessDirectionEnum getDirection() {
        return direction;
    }
    
    public void setDirection(AccessDirectionEnum direction) {
        this.direction = direction;
    }
    
    public OnOffStatusEnum getStatus() {
        return status;
    }
    
    public void setStatus(OnOffStatusEnum status) {
        this.status = status;
    }
    
    public AccessTypeEnum getAccessType() {
        return accessType;
    }
    
    public void setAccessType(AccessTypeEnum accessType) {
        this.accessType = accessType;
    }
    
    @Override
    public String toString() {
        return "WebhookEndAccess [registry=" + registry + ", date=" + date + ", direction=" + direction + ", status=" + status + ", accessType="
               + accessType + "]";
    }
    
}
