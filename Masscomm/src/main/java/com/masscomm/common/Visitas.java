/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

import java.util.Date;

public class Visitas implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private String apellidos;
    private String empresa;
    private String cargo;
    private String logo;
    private String foto;
    private Date fecha;

    public Visitas() {
    }

    public Visitas(String nombre, String apellidos, String empresa, String cargo, String logo, String foto, Date fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.empresa = empresa;
        this.cargo = cargo;
        this.logo = logo;
        this.foto = foto;
        this.fecha = fecha;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
