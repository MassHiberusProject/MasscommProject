/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Rol implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String rolname;
    private Set<Usuario> usuarios;

    public Rol() {
    }

    public Rol(String rolname) {
        this.rolname = rolname;
    }

    public Rol(String rolname, Set<Usuario> usuarios) {
        this.rolname = rolname;
        this.usuarios = usuarios;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rol other = (Rol) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.rolname.compareTo(other.rolname) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.rolname);
        return hash;
    }

}
