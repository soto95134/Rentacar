/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.TransmisionModel;
import INACAP.DW.Rentacar.Repository.TransmisionRepository;
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
@RequestMapping("/transmision")
public class TransmisionController {

    @Autowired
    private TransmisionRepository a;

    @GetMapping()
    public Iterable<TransmisionModel> list() {
        return a.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransmisionModel> get(@PathVariable String id) {
        Optional<TransmisionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TransmisionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransmisionModel> put(@PathVariable String id, @RequestBody TransmisionModel c) {
            Optional<TransmisionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TransmisionModel aEncontrado = aOptional.get();

            c.setIdTransmision(aEncontrado.getIdTransmision());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TransmisionModel> post(@RequestBody TransmisionModel c) {
        c = a.save(c);

        Optional<TransmisionModel> aOptional = a.findById(c.getIdTransmision());
        if (aOptional.isPresent()) {
            TransmisionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransmisionModel> delete(@PathVariable String id) {
        Optional<TransmisionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TransmisionModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdTransmision());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
