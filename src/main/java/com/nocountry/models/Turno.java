package com.nocountry.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //cambio relacion
   // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
   // @JoinColumn(name = "paciente_id",nullable = false)
   // private Paciente paciente;

    //nueva relacion
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    @Override
    public String toString() {
        DateTimeFormatter turnoNew =
                DateTimeFormatter
                        .ofPattern("EEEE, dd 'de' MMMM 'de' yyyy 'a las' hh:mm 'horas.'")
                        .withLocale(new Locale("es", "ES"));

        return "Turno solicitado por:" +
                "  Nombre:" + usuario.getNombre()+" "+
                              usuario.getApellido() +
                ". Email:" + email  +
                ". Medico:" + medico.getNombre() +" "+
                              medico.getApellido()+
                ". Clínica:" + medico.getClinica().getNombre() +
                ". Fecha:" + fecha.format(turnoNew) +
                '.';
    }
}