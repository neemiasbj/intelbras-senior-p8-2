package br.com.domain.intelbras.model;

import java.time.LocalDateTime;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import br.com.domain.intelbras.model.enums.WebhookTypeMessageEnum;
import br.com.util.Utils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Model for generic webhook")
public class WebHookModel extends DeviceAbstract {
    
    @NotNull(message = "Error flag cannot be null")
    @Schema(description = "Indicates if there was an error", example = "false")
    private Boolean error = false;
    
    @Schema(description = "ID of the pendency", example = "12345")
    private String pendencyId;
    
    @NotBlank(message = "Message cannot be blank")
    @Schema(description = "Message associated with the webhook", example = "Some message")
    private String message;
    
    @NotBlank(message = "Date post cannot be blank")
    @Schema(description = "Date when the post was made", example = "2024-07-30T12:34:56")
    private String datePost = Utils.formatDateToWebhookPattern(LocalDateTime.now());
    
    @NotBlank(message = "Process start time cannot be blank")
    @Schema(description = "Start time of the process", example = "2024-07-30T12:00:00")
    private String processStart;
    
    @NotBlank(message = "Process end time cannot be blank")
    @Schema(description = "End time of the process", example = "2024-07-30T12:30:00")
    private String processEnd;
    
    @NotNull(message = "Pendency type cannot be null")
    @Schema(description = "Type of pendency", example = "SOME_TYPE")
    private WebhookTypeMessageEnum pendencyType;
    
    @Schema(description = "Additional data", example = "{}")
    private Object data;
    
    public void reset(WebHookModel pendencyType, String pendencyId) {
        this.processStart = Utils.formatDateToWebhookPattern(LocalDateTime.now());
        this.data = null;
        this.pendencyType = pendencyType.getPendencyType();
        this.pendencyId = pendencyId;
        this.message = null;
        this.error = false;
    }
    
    public void finishedSuccess() {
        LocalDateTime now = LocalDateTime.now();
        error = false;
        message = null;
        processEnd = Utils.formatDateToWebhookPattern(now);
        datePost = Utils.formatDateToWebhookPattern(now);
    }
    
    public void finishedError(String errorMessage) {
        LocalDateTime now = LocalDateTime.now();
        error = true;
        message = errorMessage;
        processEnd = Utils.formatDateToWebhookPattern(now);
        datePost = Utils.formatDateToWebhookPattern(now);
    }
    
    public WebHookModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress,
                        @NotNull(message = "Error flag cannot be null") Boolean error, String pendencyId,
                        @NotBlank(message = "Message cannot be blank") String message,
                        @NotBlank(message = "Date post cannot be blank") String datePost,
                        @NotBlank(message = "Process start time cannot be blank") String processStart,
                        @NotBlank(message = "Process end time cannot be blank") String processEnd,
                        @NotNull(message = "Pendency type cannot be null") WebhookTypeMessageEnum pendencyType, Object data) {
        super(deviceIpAddress);
        this.error = error;
        this.pendencyId = pendencyId;
        this.message = message;
        this.datePost = datePost;
        this.processStart = processStart;
        this.processEnd = processEnd;
        this.pendencyType = pendencyType;
        this.data = data;
    }
    
    public WebHookModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
        super(deviceIpAddress);
        // TODO Auto-generated constructor stub
    }
    
    public void setDatePostNow() {
        this.datePost = Utils.formatDateToWebhookPattern(LocalDateTime.now());
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    public Boolean getError() {
        return error;
    }
    
    public void setError(Boolean error) {
        this.error = error;
    }
    
    public String getPendencyId() {
        return pendencyId;
    }
    
    public void setPendencyId(String pendencyId) {
        this.pendencyId = pendencyId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getDatePost() {
        return datePost;
    }
    
    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }
    
    public String getProcessStart() {
        return processStart;
    }
    
    public void setProcessStart(String processStart) {
        this.processStart = processStart;
    }
    
    public String getProcessEnd() {
        return processEnd;
    }
    
    public void setProcessEnd(String processEnd) {
        this.processEnd = processEnd;
    }
    
    public WebhookTypeMessageEnum getPendencyType() {
        return pendencyType;
    }
    
    public void setPendencyType(WebhookTypeMessageEnum pendencyType) {
        this.pendencyType = pendencyType;
    }
    
    public Object getData() {
        return data;
    }
    
    @Override
    public String toString() {
        return "[ ipDeviceAddress= " + getDeviceIpAddress() + ", error=" + error + ", pendencyId=" + pendencyId + ", message=" + message
               + ", datePost=" + datePost + ", processStart=" + processStart + ", processEnd=" + processEnd + ", pendencyType=" + pendencyType
               + ", data=" + data + "]";
    }
    
}
