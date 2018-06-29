/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.DevolucionModel;
import INACAP.DW.Rentacar.Repository.DevolucionRepository;
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
@RequestMapping("/devolucion")
public class DevolucionController {

    @Autowired
    private DevolucionRepository dev;

    @GetMapping()
    public Iterable<DevolucionModel> list() {
        return dev.findAll();
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<DevolucionModel> aOptional = dev.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            DevolucionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevolucionModel> put(@PathVariable String id, @RequestBody DevolucionModel c) {
        Optional<DevolucionModel> aOptional = dev.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            DevolucionModel aEncontrado = aOptional.get();

            c.setIdDevolucion(aEncontrado.getIdDevolucion());

            dev.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DevolucionModel> post(@RequestBody DevolucionModel c) {
        c = dev.save(c);

        Optional<DevolucionModel> aOptional = dev.findById(c.getIdDevolucion());
        if (aOptional.isPresent()) {
            DevolucionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DevolucionModel> delete(@PathVariable String id) {
        Optional<DevolucionModel> aOptional = dev.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            DevolucionModel aEncontrado = aOptional.get();

            dev.deleteById(aEncontrado.getIdDevolucion());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
}


