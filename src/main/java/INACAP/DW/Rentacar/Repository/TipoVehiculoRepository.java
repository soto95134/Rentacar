/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Repository;

import INACAP.DW.Rentacar.Model.TipoVehiculoModel;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author PC
 */
public interface TipoVehiculoRepository extends CrudRepository<TipoVehiculoModel, Integer> {
    
}
