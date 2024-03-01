package com.example.jatinlab2;


public class restaurant {
    private int serverid;
    private String servername;
    private String serverdish;
    private int serverrating;

    public restaurant(int serverid, String servername, String serverdish, int serverrating) {
        this.serverid = serverid;
        this.servername = servername;
        this.serverdish = serverdish;
        this.serverrating = serverrating;
    }

    public int getserverid() {
        return serverid;
    }

    public String getservername() {
        return servername;
    }

    public String getServerdish() {
        return serverdish;
    }

    public int getserverrating() {
        return serverrating;
    }
}
