/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.ArriendoModel;
import INACAP.DW.Rentacar.Repository.ArriendoRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/arriendo")
public class ArriendoController {

    @Autowired
    private ArriendoRepository arriendo;

    @GetMapping()
    public Iterable<ArriendoModel> list() {
        return arriendo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArriendoModel> get(@PathVariable String id) {
        Optional<ArriendoModel> aOptional = arriendo.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            ArriendoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArriendoModel> put(@PathVariable String id, @RequestBody ArriendoModel arriendoM) {
        Optional<ArriendoModel> aOptional = arriendo.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            ArriendoModel aEncontrado = aOptional.get();

            arriendoM.setIdArriendo(aEncontrado.getIdArriendo());

            arriendo.save(arriendoM);
            return new ResponseEntity<>(arriendoM, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ArriendoModel arriendoM) {
        arriendoM = arriendo.save(arriendoM);

        Optional<ArriendoModel> aOptional = arriendo.findById(arriendoM.getIdArriendo());
        if (aOptional.isPresent()) {
            ArriendoModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<ArriendoModel> aOptional = arriendo.findById(Integer.parseInt(id));      
        if (aOptional.isPresent()) {            
            ArriendoModel aEncontrado = aOptional.get();
            
            arriendo.deleteById(aEncontrado.getIdArriendo());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        }else{ 
            
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }

}
