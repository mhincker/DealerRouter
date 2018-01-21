package com.renault.dealer.router.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRouted  {

    private String uid; // DMS ID
    private String dealerId;
    private String correlationId;
    private String service;
    private String payload;

    @JsonCreator
    public MessageRouted(@JsonProperty(value = "uid",required = true) String uid,
                         @JsonProperty(value = "dealerId", required = false) String dealerId,
                         @JsonProperty(value = "correlationId", required = false) String correlationId,
                         @JsonProperty(value = "service", required = true) String service,
                         @JsonProperty(value = "payload", required = true) String payload)
    {
        this.uid = uid;
        this.dealerId = dealerId;
        this.correlationId = correlationId;
        this.service = service;
        this.payload = payload;
    }

    public MessageRouted() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.service = dealerId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPayload() { return payload; }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "{" +
                "\"uid\":\"" + uid + "\"" +
                ",\"service\":\"" + service + "\"" +
                ",\"dealerId\":\"" + dealerId + "\"" +
                ",\"correlationId\":\"" + correlationId + "\"" +
                ",\"payload\":\"" + payload + "\"" +
                "}";
    }
}
