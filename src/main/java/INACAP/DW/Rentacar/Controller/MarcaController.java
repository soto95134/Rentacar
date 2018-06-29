/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.MarcaModel;
import INACAP.DW.Rentacar.Repository.MarcaRepository;
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
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository a;

    @GetMapping()
    public Iterable<MarcaModel> list() {
        return a.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaModel> get(@PathVariable String id) {
        Optional<MarcaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            MarcaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaModel> put(@PathVariable String id, @RequestBody MarcaModel c) {
        Optional<MarcaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            MarcaModel aEncontrado = aOptional.get();

            c.setIdMarca(aEncontrado.getIdMarca());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MarcaModel> post(@RequestBody MarcaModel c) {
        c = a.save(c);

        Optional<MarcaModel> aOptional = a.findById(c.getIdMarca());
        if (aOptional.isPresent()) {
            MarcaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MarcaModel> delete(@PathVariable String id) {
        Optional<MarcaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            MarcaModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdMarca());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
