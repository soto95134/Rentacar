/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.MedioPagoModel;
import INACAP.DW.Rentacar.Repository.MedioPagoRepository;
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
@RequestMapping("/mediopago")
public class MedioPagoController {
        @Autowired
    private MedioPagoRepository a;
    
    @GetMapping()
    public Iterable<MedioPagoModel> list() {
        return a.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MedioPagoModel> get(@PathVariable String id) {
        Optional<MedioPagoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            MedioPagoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MedioPagoModel> put(@PathVariable String id, @RequestBody MedioPagoModel c) {
         Optional<MedioPagoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            MedioPagoModel aEncontrado = aOptional.get();

            c.setIdMedioPago(aEncontrado.getIdMedioPago());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<MedioPagoModel> post(@RequestBody MedioPagoModel c) {
        c = a.save(c);

        Optional<MedioPagoModel> aOptional = a.findById(c.getIdMedioPago());
        if (aOptional.isPresent()) {
            MedioPagoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MedioPagoModel> delete(@PathVariable String id) {
        Optional<MedioPagoModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            MedioPagoModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdMedioPago());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
    
}
