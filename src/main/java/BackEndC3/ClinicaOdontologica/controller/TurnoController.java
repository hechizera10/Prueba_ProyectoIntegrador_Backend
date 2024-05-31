package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.model.Turno;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import BackEndC3.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService= new TurnoService();
        pacienteService= new PacienteService();
        odontologoService= new OdontologoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        Paciente pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if(pacienteBuscado!=null&&odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id){
        Turno turnoBuscado= turnoService.buscarPorId(id);
        if(turnoBuscado!=null){
            return ResponseEntity.ok(turnoBuscado);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        Turno turnoBuscado= turnoService.buscarPorId(id);
        if(turnoBuscado!=null){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    // --> Cuando se actualiza despliega null el paciente y el odontologo
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        //Paciente pacienteBuscado;
        //Odontologo odontologoBuscado;
        Turno turnoBuscado= turnoService.buscarPorId(turno.getId());
        if(turnoBuscado!=null){
            //pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
            //odontologoBuscado= odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado con exito");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
