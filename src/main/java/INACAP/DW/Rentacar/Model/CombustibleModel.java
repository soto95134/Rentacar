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
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "combustible")
public class CombustibleModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int idCombustible;

String tipoCombustible;
String detalle;


    public void setIdCombustible(int idCombustible) {
        this.idCombustible = idCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getIdCombustible() {
        return idCombustible;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public String getDetalle() {
        return detalle;
    }

    public CombustibleModel() {
    }

    public CombustibleModel(String tipoCombustible, String detalle) {
        this.tipoCombustible = tipoCombustible;
        this.detalle = detalle;
    }

    private CombustibleModel(int idCombustible, String tipoCombustible, String detalle) {
        this.idCombustible = idCombustible;
        this.tipoCombustible = tipoCombustible;
        this.detalle = detalle;
    }
    
}
