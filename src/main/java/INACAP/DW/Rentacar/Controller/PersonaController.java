/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.PersonaModel;
import INACAP.DW.Rentacar.Repository.PersonaRepository;
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
@RequestMapping("/persona")
public class PersonaController {
    
    @Autowired
    private PersonaRepository a;
    
    @GetMapping()
    public Iterable<PersonaModel> list() {
        return a.findAll();
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<PersonaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            PersonaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody PersonaModel c) {
        Optional<PersonaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            PersonaModel aEncontrado = aOptional.get();

            c.setIdPersona(aEncontrado.getIdPersona());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<PersonaModel> post(@RequestBody PersonaModel c) {
       c = a.save(c);

        Optional<PersonaModel> aOptional = a.findById(c.getIdPersona());
        if (aOptional.isPresent()) {
            PersonaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaModel> delete(@PathVariable String id) {
        Optional<PersonaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            PersonaModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdPersona());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
    
}
