package com.nocountry.controllers;

import com.nocountry.models.Clinica;
import com.nocountry.models.Medico;
import com.nocountry.services.ClinicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clinica/")
@CrossOrigin("*")
public class ClinicaController {

    private final ClinicaService service;

    public ClinicaController(ClinicaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Clinica>getAllClinicas(){
        return service.getAllClinicas();
    }
    @PutMapping
    public ResponseEntity<Clinica> update(@RequestBody Clinica clinica ){
        return service.updateClinica(clinica);
    }


    @PostMapping
    public Clinica guardar(@RequestBody Clinica clinica){
        return service.saveClinica(clinica);
    }

    @GetMapping("nombre/{nombre}")
    public Clinica getClinicaNombre(@PathVariable("nombre")String nombre){
        return service.getNombreClinica(nombre);
    }

    @DeleteMapping("{id}")
    public void eliminar(@PathVariable("id") Long id){
        service.deleteClinica(id);
    }

    @GetMapping("{id}")
    public Optional<Clinica> buscarById(@PathVariable Long id) throws EntityNotFoundException {
        return service.buscarClinicaId(id);
    }
}
