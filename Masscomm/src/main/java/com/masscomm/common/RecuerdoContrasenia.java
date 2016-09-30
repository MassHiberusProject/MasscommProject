/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

import java.sql.Timestamp;

/**
 *
 * @author pmayor
 */
public class RecuerdoContrasenia implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Usuario userid;
    private String codigo;
    private Timestamp fecha;

    public RecuerdoContrasenia(String codigo) {
        this.codigo = codigo;
    }

    public RecuerdoContrasenia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUserid() {
        return userid;
    }

    public void setUserid(Usuario userid) {
        this.userid = userid;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

}
