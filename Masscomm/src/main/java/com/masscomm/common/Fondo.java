/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

public class Fondo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private String tipo;

    public Fondo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    public Fondo(){        
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
