package com.htpt.coordinator.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
public class LogModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ipClient;
    private String ipServer;
    private String status;
    private String date;

    public LogModel() {}

    public LogModel(Integer id, String ipClient, String ipServer, String status, String date) {
        this.id = id;
        this.ipClient = ipClient;
        this.ipServer = ipServer;
        this.status = status;
        this.date = date;
    }

    public LogModel(String ipClient, String ipServer, String status, String date) {
        this.ipClient = ipClient;
        this.ipServer = ipServer;
        this.status = status;
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getIpServer() {
        return ipServer;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    
}
