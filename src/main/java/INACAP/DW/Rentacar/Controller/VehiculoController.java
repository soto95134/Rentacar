/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.VehiculoModel;
import INACAP.DW.Rentacar.Repository.VehiculoRepository;
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
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoRepository a;

    @GetMapping()
    public Iterable<VehiculoModel> list() {
        return a.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoModel> get(@PathVariable String id) {
        Optional<VehiculoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            VehiculoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody VehiculoModel c) {
        Optional<VehiculoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            VehiculoModel aEncontrado = aOptional.get();

            c.setIdVehiculo(aEncontrado.getIdVehiculo());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VehiculoModel> post(@RequestBody VehiculoModel c) {
        c = a.save(c);

        Optional<VehiculoModel> aOptional = a.findById(c.getIdVehiculo());
        if (aOptional.isPresent()) {
            VehiculoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehiculoModel> delete(@PathVariable String id) {
        Optional<VehiculoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            VehiculoModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdVehiculo());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
