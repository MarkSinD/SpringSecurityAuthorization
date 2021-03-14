package com.sinakaev.SpringSecurityTest.model;

/**
 * DECRIPTION
 *
 * @author Mark Sinakaev
 * @version 1.0
 */
public enum Permission {

    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
