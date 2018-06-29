/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.TipoVehiculoModel;
import INACAP.DW.Rentacar.Repository.TipoVehiculoRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/tipovehiculo")
public class TipoVehiculoController {
    
    @Autowired
    private TipoVehiculoRepository a;
    
    @GetMapping()
    public Iterable<TipoVehiculoModel> list() {
        return a.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculoModel> get(@PathVariable String id) {
        Optional<TipoVehiculoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TipoVehiculoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoVehiculoModel> put(@PathVariable String id, @RequestBody TipoVehiculoModel c) {
        Optional<TipoVehiculoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TipoVehiculoModel aEncontrado = aOptional.get();

            c.setIdTipoVehiculo(aEncontrado.getIdTipoVehiculo());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<TipoVehiculoModel> post(@RequestBody TipoVehiculoModel c) {
        c = a.save(c);

        Optional<TipoVehiculoModel> aOptional = a.findById(c.getIdTipoVehiculo());
        if (aOptional.isPresent()) {
            TipoVehiculoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
       Optional<TipoVehiculoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TipoVehiculoModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdTipoVehiculo());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
    
}
