/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.ModeloModel;
import INACAP.DW.Rentacar.Repository.ModeloRepository;
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
@RequestMapping("/modelo")
public class ModeloController {
    
    @Autowired
    private ModeloRepository a;
    
    @GetMapping()
    public Iterable<ModeloModel> list() {
        return a.findAll();
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<ModeloModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            ModeloModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ModeloModel> put(@PathVariable String id, @RequestBody ModeloModel c) {
        Optional<ModeloModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            ModeloModel aEncontrado = aOptional.get();

            c.setIdMModel(aEncontrado.getIdMModel());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<ModeloModel> post(@RequestBody ModeloModel c) {
        c = a.save(c);

        Optional<ModeloModel> aOptional = a.findById(c.getIdMModel());
        if (aOptional.isPresent()) {
            ModeloModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ModeloModel> delete(@PathVariable String id) {
        Optional<ModeloModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            ModeloModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdMModel());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
    
}
