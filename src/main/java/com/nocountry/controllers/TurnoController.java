package com.nocountry.controllers;


import com.nocountry.models.Turno;
import com.nocountry.services.TurnoService;
import com.nocountry.servicesImpl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos/")
@CrossOrigin("*")
public class TurnoController {

    private final TurnoService service;

    @Autowired
    private  MailService mailService;

    public TurnoController(TurnoService service) {
        this.service = service;

    }

    @GetMapping
    public List<Turno> getAll(){
        return service.getAllTurnos();
    }

    @PutMapping
    public ResponseEntity<Turno> update (@RequestBody Turno turno ){
        return service.updateTurno(turno);
    }


    @PostMapping
    public Turno guardar(@RequestBody Turno turno){

        Turno newTurno = service.saveTurno(turno);
        if(newTurno != null){
            mailService.sendMail(newTurno.getEmail(),"Turno en Medicare",newTurno.toString());
        }
        return newTurno;
    }

    @DeleteMapping("{turnoId}")
    public void eliminar(@PathVariable("turnoId") Long turnoId){
        service.deleteTurno(turnoId);
    }

    @GetMapping("{id}")
    public Optional<Turno> turnoById(@PathVariable Long id) throws EntityNotFoundException {
        return service.searchById(id);
    }

}
