package com.inCounter.userEnd;

public class Configuration {
    private String host;
    private int port;
    private String userEnd;
    private String getId;
    private String posResponse;
    private String setName;
    private String setInitiative;
    private String changeHP;

    public Configuration(String host, int port, String userEnd, String getId, String posResponse, String setName, String setInitiative, String changeHP) {
        this.host = host;
        this.port = port;
        this.userEnd = userEnd;
        this.getId = getId;
        this.posResponse = posResponse;
        this.setName = setName;
        this.setInitiative = setInitiative;
        this.changeHP = changeHP;
    }

    public String getChangeHP() {
        return changeHP;
    }

    public void setChangeHP(String changeHP) {
        this.changeHP = changeHP;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetInitiative() {
        return setInitiative;
    }

    public void setSetInitiative(String setInitiative) {
        this.setInitiative = setInitiative;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserEnd() {
        return userEnd;
    }

    public void setUserEnd(String userEnd) {
        this.userEnd = userEnd;
    }

    public String getGetId() {
        return getId;
    }

    public void setGetId(String getId) {
        this.getId = getId;
    }

    public String getPosResponse() {
        return posResponse;
    }

    public void setPosResponse(String posResponse) {
        this.posResponse = posResponse;
    }
}
