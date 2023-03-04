package com.nocountry.servicesImpl;

import com.nocountry.models.Clinica;
import com.nocountry.repository.ClinicaReposity;
import com.nocountry.services.ClinicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaServiceImpl implements ClinicaService {

    private final ClinicaReposity reposity;

    public ClinicaServiceImpl(ClinicaReposity reposity) {
        this.reposity = reposity;
    }

    @Override
    public List<Clinica> getAllClinicas() {
        return reposity.findAll();
    }

    @Override
    public Clinica saveClinica(Clinica clinica) {
        return reposity.save(clinica);
    }

    @Override
    public Clinica getNombreClinica(String nombre) {
        return reposity.findByNombre(nombre);
    }

    @Override
    public ResponseEntity<Clinica> updateClinica(Clinica clinica) {
        Clinica c = new Clinica();
        return new ResponseEntity<Clinica>(c, HttpStatus.OK);
    }

    @Override
    public Optional<Clinica> buscarClinicaId(Long id){
        Optional<Clinica> clinica = reposity.findById(id);
        if(!clinica.isPresent()){
            String mensaje= "La clinica con id= "+ id.toString()+" no existe (searchById)";
            System.out.println(mensaje);
        }
        return clinica;
    }

    @Override
    public void deleteClinica(Long id) {
    reposity.deleteById(id);
    }
}
