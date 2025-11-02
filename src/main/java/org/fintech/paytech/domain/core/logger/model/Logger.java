package org.fintech.paytech.domain.core.logger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "logs")
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String traceId;

    private String userId;

    private String className;

    private String methodName;

    private String method;

    private String endpoint;

    @Column(length = 10000)
    //@Column(columnDefinition = "jsonb", length = 10485760)
    private String requestHeader;

    @Column(length = 10000)
    private String requestBody;

    @Column(length = 10000)
    private String responseHeader;

    @Column(length = 10000)
    private String responseBody;

    private String result;

    private int status;

    private long duration;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.updated = this.created;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }

    public Logger() {
    }

    public Logger(String traceId, String userId, String className, String methodName, String method, String endpoint, String requestHeader, String requestBody, String responseHeader, String responseBody, String result, int status, long duration, Date created, Date updated) {
        this.traceId = traceId;
        this.userId = userId;
        this.className = className;
        this.methodName = methodName;
        this.method = method;
        this.endpoint = endpoint;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
        this.responseHeader = responseHeader;
        this.responseBody = responseBody;
        this.result = result;
        this.status = status;
        this.duration = duration;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Logger logger = (Logger) o;
        return status == logger.status && duration == logger.duration && Objects.equals(id, logger.id) && Objects.equals(traceId, logger.traceId) && Objects.equals(userId, logger.userId) && Objects.equals(className, logger.className) && Objects.equals(methodName, logger.methodName) && Objects.equals(method, logger.method) && Objects.equals(endpoint, logger.endpoint) && Objects.equals(requestHeader, logger.requestHeader) && Objects.equals(requestBody, logger.requestBody) && Objects.equals(responseHeader, logger.responseHeader) && Objects.equals(responseBody, logger.responseBody) && Objects.equals(result, logger.result) && Objects.equals(created, logger.created) && Objects.equals(updated, logger.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, traceId, userId, className, methodName, method, endpoint, requestHeader, requestBody, responseHeader, responseBody, result, status, duration, created, updated);
    }

    @Override
    public String toString() {
        return "Logger{" +
                "id=" + id +
                ", traceId='" + traceId + '\'' +
                ", userId='" + userId + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", method='" + method + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", requestHeader='" + requestHeader + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", responseHeader='" + responseHeader + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", result='" + result + '\'' +
                ", status=" + status +
                ", duration=" + duration +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
