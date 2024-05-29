package BackEndC3.ClinicaOdontologica.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Odontologo {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public Odontologo(Integer id, String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;

    }
    public Odontologo(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Odontologo() {
    }


}
