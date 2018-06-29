/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.RegionModel;
import INACAP.DW.Rentacar.Repository.RegionRepository;
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
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionRepository a;

    @GetMapping()
    public Iterable<RegionModel> list() {
       return a.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionModel> get(@PathVariable String id) {
            Optional<RegionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            RegionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionModel> put(@PathVariable String id, @RequestBody RegionModel c) {
        Optional<RegionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            RegionModel aEncontrado = aOptional.get();

            c.setIdRegion(aEncontrado.getIdRegion());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RegionModel> post(@RequestBody RegionModel c) {
        c = a.save(c);

        Optional<RegionModel> aOptional = a.findById(c.getIdRegion());
        if (aOptional.isPresent()) {
            RegionModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegionModel> delete(@PathVariable String id) {
        Optional<RegionModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            RegionModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdRegion());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
