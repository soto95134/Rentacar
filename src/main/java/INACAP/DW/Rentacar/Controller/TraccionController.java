/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.TraccionModel;
import INACAP.DW.Rentacar.Repository.TraccionRepository;
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
@RequestMapping("/traccion")
public class TraccionController {

    @Autowired
    private TraccionRepository a;

    @GetMapping()
    public Iterable<TraccionModel> list() {
        return a.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraccionModel> get(@PathVariable String id) {
        Optional<TraccionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TraccionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TraccionModel> put(@PathVariable String id, @RequestBody TraccionModel c) {
        Optional<TraccionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TraccionModel aEncontrado = aOptional.get();

            c.setIdTraccion(aEncontrado.getIdTraccion());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TraccionModel> post(@RequestBody TraccionModel c) {
        c = a.save(c);

        Optional<TraccionModel> aOptional = a.findById(c.getIdTraccion());
        if (aOptional.isPresent()) {
            TraccionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TraccionModel> delete(@PathVariable String id) {
        Optional<TraccionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TraccionModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdTraccion());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
