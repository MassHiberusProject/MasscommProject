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
public class RolesUsuarios {

    private static final long serialVersionUID = 1L;

    private Usuario username;
    private String rol;

    public RolesUsuarios() {
    }

    public RolesUsuarios(Usuario username, String rol) {
        this.username = username;
        this.rol = rol;
    }

    public Usuario getUsername() {
        return username;
    }

    public void setUsername(Usuario username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
