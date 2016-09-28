/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

import java.util.Date;

/**
 *
 * @author claencina
 */
public class Cumpleanios implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private String apellidos;
    private String empresa;
    private Imagen image_id;
    private Date fecha;

    public Cumpleanios(String nombre, String apellidos, String empresa, Imagen image_id, Date fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.empresa = empresa;
        this.image_id = image_id;
        this.fecha = fecha;
    }

    public Cumpleanios() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Imagen getImage_id() {
        return image_id;
    }

    public void setImage_id(Imagen image_id) {
        this.image_id = image_id;
    }
    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
