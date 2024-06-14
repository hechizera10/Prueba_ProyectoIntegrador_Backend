package BackEndC3.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer matricula;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("odontologo")
    private Set<Paciente> pacientes = new HashSet<>();


    public Odontologo(Long id, Integer matricula, String nombre, String apellido, Set<Paciente> pacientes) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pacientes = pacientes;
    }

    public Odontologo() {
    }


}
