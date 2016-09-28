/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masscomm.common;

/**
 *
 * @author claencina
 */
public class Imagen implements java.io.Serializable {

    private Integer image_id;
    private byte[] image;

    public Imagen() {
    }

    public Imagen(byte[] imagen) {
        this.image = imagen;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }
   

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    
}
