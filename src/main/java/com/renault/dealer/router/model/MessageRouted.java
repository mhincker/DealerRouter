package com.renault.dealer.router.model;

import java.io.Serializable;

public class MessageRouted implements Serializable {

    private String uid;
    private String service;
    private Object message;

    public MessageRouted() {
    }

    public MessageRouted(String uid, String service, Object message) {
        this.uid = uid;
        this.service = service;
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRouted{" +
                "uid='" + uid + '\'' +
                ", service='" + service + '\'' +
                ", message=" + message.toString() +
                '}';
    }
}
