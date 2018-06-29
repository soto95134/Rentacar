/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INACAP.DW.Rentacar.Controller;

import INACAP.DW.Rentacar.Model.CarroceriaModel;
import INACAP.DW.Rentacar.Repository.CarroceriaRepository;
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
@RequestMapping("/carroceria")
public class CarroceriaController {
    
    @Autowired
    private CarroceriaRepository carroceria;
    
    @GetMapping()
    public Iterable<CarroceriaModel> list() {
        return carroceria.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CarroceriaModel> get(@PathVariable String id) {
        Optional<CarroceriaModel> aOptional = carroceria.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CarroceriaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CarroceriaModel> put(@PathVariable String id, @RequestBody CarroceriaModel c) {
            Optional<CarroceriaModel> aOptional = carroceria.findById(Integer.parseInt(id));
        if (aOptional.isPresent()) {
            CarroceriaModel aEncontrado = aOptional.get();

            c.setIdCarroceria(aEncontrado.getIdCarroceria());

            carroceria.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<CarroceriaModel> post(@RequestBody CarroceriaModel c) {
        c = carroceria.save(c);

        Optional<CarroceriaModel> aOptional = carroceria.findById(c.getIdCarroceria());
        if (aOptional.isPresent()) {
            CarroceriaModel aEncontrado = aOptional.get();
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<CarroceriaModel> delete(@PathVariable String id) {
        Optional<CarroceriaModel> aOptional = carroceria.findById(Integer.parseInt(id));      
        if (aOptional.isPresent()) {            
            CarroceriaModel aEncontrado = aOptional.get();
            
            carroceria.deleteById(aEncontrado.getIdCarroceria());
            return new ResponseEntity<>(aEncontrado, HttpStatus.OK);
        }else{ 
            
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        }
    }
    
}
