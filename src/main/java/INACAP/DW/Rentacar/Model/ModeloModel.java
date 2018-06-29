/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Model;

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
@Table(name = "modelo")
public class ModeloModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMModel;
    
    private String nombreModelo;
    private String detalleModel;
    
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcaModel marca;

    public int getIdMModel() {
        return idMModel;
    }

    public void setIdMModel(int idMModel) {
        this.idMModel = idMModel;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public String getDetalleModel() {
        return detalleModel;
    }

    public void setDetalleModel(String detalleModel) {
        this.detalleModel = detalleModel;
    }

    public MarcaModel getMarca() {
        return marca;
    }

    public void setMarca(MarcaModel marca) {
        this.marca = marca;
    }

    public ModeloModel() {
    }

    public ModeloModel(String nombreModelo, String detalleModel, MarcaModel marca) {
        this.nombreModelo = nombreModelo;
        this.detalleModel = detalleModel;
        this.marca = marca;
    }

    private ModeloModel(int idMModel, String nombreModelo, String detalleModel, MarcaModel marca) {
        this.idMModel = idMModel;
        this.nombreModelo = nombreModelo;
        this.detalleModel = detalleModel;
        this.marca = marca;
    }

}
