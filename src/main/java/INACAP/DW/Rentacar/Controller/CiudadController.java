/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.CiudadModel;
import INACAP.DW.Rentacar.Repository.CiudadRepository;
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
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudad;

    @GetMapping()
    public Iterable<CiudadModel> list() {
        return ciudad.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadModel> get(@PathVariable String id) {
        Optional<CiudadModel> aOptional = ciudad.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CiudadModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiudadModel> put(@PathVariable String id, @RequestBody CiudadModel c) {
        Optional<CiudadModel> aOptional = ciudad.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CiudadModel aEncontrado = aOptional.get();

            c.setIdCiudad(aEncontrado.getIdCiudad());

            ciudad.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CiudadModel> post(@RequestBody CiudadModel c) {
        c = ciudad.save(c);

        Optional<CiudadModel> aOptional = ciudad.findById(c.getIdCiudad());
        if (aOptional.isPresent()) {
            CiudadModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CiudadModel> delete(@PathVariable String id) {
        Optional<CiudadModel> aOptional = ciudad.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CiudadModel aEncontrado = aOptional.get();

            ciudad.deleteById(aEncontrado.getIdCiudad());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
