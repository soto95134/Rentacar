/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.VersionModel;
import INACAP.DW.Rentacar.Repository.VersionRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/version")
public class VersionController {
    
        @Autowired
    private VersionRepository a;
    
    @GetMapping()
    public Iterable<VersionModel> list() {
         return a.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VersionModel> get(@PathVariable String id) {
        Optional<VersionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            VersionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VersionModel> put(@PathVariable String id, @RequestBody VersionModel c) {
        Optional<VersionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            VersionModel aEncontrado = aOptional.get();

            c.setIdVersion(aEncontrado.getIdVersion());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<VersionModel> post(@RequestBody VersionModel c) {
        c = a.save(c);

        Optional<VersionModel> aOptional = a.findById(c.getIdVersion());
        if (aOptional.isPresent()) {
            VersionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<VersionModel> delete(@PathVariable String id) {
        Optional<VersionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            VersionModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdVersion());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
    
}
