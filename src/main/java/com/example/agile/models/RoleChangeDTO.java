package com.example.agile.models;

public class RoleChangeDTO {
    private String username;
    private String roleUpdate;

    public RoleChangeDTO(){
        super();
    }
    public RoleChangeDTO(String username, String roleUpdate){
        this.username = username;
        this.roleUpdate = roleUpdate;
    }

    public String getUsername(){
        return this.username;

    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getNewRole(){
        return this.roleUpdate;
    }

    public void setNewRole(String roleUpdate){
        this.roleUpdate = roleUpdate;
    }
}
