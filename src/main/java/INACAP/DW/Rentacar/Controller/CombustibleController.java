/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.CiudadModel;
import INACAP.DW.Rentacar.Model.CombustibleModel;
import INACAP.DW.Rentacar.Repository.CombustibleRepository;
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
@RequestMapping("/combustible")
public class CombustibleController {

    @Autowired
    private CombustibleRepository comb;

    @GetMapping()

    public Iterable<CombustibleModel> list() {
        return comb.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CombustibleModel> get(@PathVariable String id) {
        Optional<CombustibleModel> aOptional = comb.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CombustibleModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CombustibleModel> put(@PathVariable String id, @RequestBody CombustibleModel c) {
        Optional<CombustibleModel> aOptional = comb.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CombustibleModel aEncontrado = aOptional.get();

            c.setIdCombustible(aEncontrado.getIdCombustible());

            comb.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CombustibleModel> post(@RequestBody CombustibleModel c) {
        c = comb.save(c);

        Optional<CombustibleModel> aOptional = comb.findById(c.getIdCombustible());
        if (aOptional.isPresent()) {
            CombustibleModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<CombustibleModel> aOptional = comb.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CombustibleModel aEncontrado = aOptional.get();

            comb.deleteById(aEncontrado.getIdCombustible());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
