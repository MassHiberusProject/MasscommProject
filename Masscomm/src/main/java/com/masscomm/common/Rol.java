/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author pmayor
 */
public class Rol implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String rolname;
    private Set<Usuario> usuarios = new HashSet<Usuario>();

    public Rol() {
    }

    public Rol(String rolname) {
        this.rolname = rolname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolname() {
        return rolname;
    }

    public void setRolname(String rolname) {
        this.rolname = rolname;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
