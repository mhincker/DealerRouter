package com.renault.dealer.router.model;

import org.springframework.web.socket.WebSocketSession;

public class DealerSockectSession {

    private String uid;
    private String service;
    private WebSocketSession webSocketSession;

    public DealerSockectSession() {
    }

    public DealerSockectSession(String uid, String service, WebSocketSession webSocketSession) {
        this.uid = uid;
        this.service = service;
        this.webSocketSession = webSocketSession;
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

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    @Override
    public String toString() {
        return "MessageRouted{" +
                "uid='" + uid + '\'' +
                ", service='" + service + '\'' +
                ", webSocketSession=" + webSocketSession.toString() +
                '}';
    }
}
