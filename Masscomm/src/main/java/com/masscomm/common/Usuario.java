/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

/**
 *
 * @author pmayor
 */
public class Usuario implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String user;
    private String password;
    private String email;
    private String rol;

    public Usuario(String user, String password, String email, String rol) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public Usuario() {
        this.user="";
        this.password="";
        this.email = "";
        this.rol = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
