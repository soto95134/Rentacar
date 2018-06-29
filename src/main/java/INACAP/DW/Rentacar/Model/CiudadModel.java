/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "ciudad")
public class CiudadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCiudad;

    private String nombreCiuadad;
    private String detalle;
    
    @ManyToOne
    @JoinColumn(name = "id_region")
    private RegionModel region;

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiuadad() {
        return nombreCiuadad;
    }

    public void setNombreCiuadad(String nombreCiuadad) {
        this.nombreCiuadad = nombreCiuadad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public RegionModel getRegion() {
        return region;
    }

    public void setRegion(RegionModel region) {
        this.region = region;
    }

    public CiudadModel() {
    }

    public CiudadModel(String nombreCiuadad, String detalle, RegionModel region) {
        this.nombreCiuadad = nombreCiuadad;
        this.detalle = detalle;
        this.region = region;
    }

    private CiudadModel(int idCiudad, String nombreCiuadad, String detalle, RegionModel region) {
        this.idCiudad = idCiudad;
        this.nombreCiuadad = nombreCiuadad;
        this.detalle = detalle;
        this.region = region;
    }

}
