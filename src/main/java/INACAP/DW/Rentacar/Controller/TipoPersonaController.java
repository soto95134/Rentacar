/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.TipoPersonaModel;
import INACAP.DW.Rentacar.Repository.MarcaRepository;
import INACAP.DW.Rentacar.Repository.TipoPersonaRepository;
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
@RequestMapping("/tipopersona")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaRepository a;

    @GetMapping()
    public Iterable<TipoPersonaModel> list() {
        return a.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPersonaModel> get(@PathVariable String id) {
        Optional<TipoPersonaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TipoPersonaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPersonaModel> put(@PathVariable String id, @RequestBody TipoPersonaModel c) {
        Optional<TipoPersonaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TipoPersonaModel aEncontrado = aOptional.get();

            c.setIdPersona(aEncontrado.getIdPersona());

            a.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TipoPersonaModel> post(@RequestBody TipoPersonaModel c) {
        c = a.save(c);

        Optional<TipoPersonaModel> aOptional = a.findById(c.getIdPersona());
        if (aOptional.isPresent()) {
            TipoPersonaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoPersonaModel> delete(@PathVariable String id) {
        Optional<TipoPersonaModel> aOptional = a.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            TipoPersonaModel aEncontrado = aOptional.get();

            a.deleteById(aEncontrado.getIdPersona());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}
