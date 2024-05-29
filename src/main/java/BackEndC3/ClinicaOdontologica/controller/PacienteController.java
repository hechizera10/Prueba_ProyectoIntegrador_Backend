package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //<-- es controller pq vamos a usar una tecnologia de vista
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public PacienteController() {
        pacienteService= new PacienteService();
    }

    @GetMapping("/{id}") //--> nos permite buscar un paciente por id (localhost:8080/pacientes?id=1
    public Paciente buscarPacientePorId(@PathVariable("id") Integer id){
        return pacienteService.buscarPorID(id);
    }

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){

        Paciente pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.actualizarPaciente(paciente);
            return "paciente actualizado con exito";
        }else{
            return "paciente no encontrado";
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPaciente(@PathVariable("id") Integer id){
        Paciente pacienteBuscado = pacienteService.buscarPorID(id);
        if(pacienteBuscado!=null){
            pacienteService.eliminarPaciente(id);
        }

    }

    @GetMapping
    public List<Paciente> buscarTodosLosPacientes(){
        return pacienteService.buscarTodosLosPacientes();
    }


    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
//    @GetMapping
//    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){
//
//        Paciente paciente= pacienteService.buscarPorEmail(email);
//        Integer matricula =paciente.getOdontologo().getMatricula();
//
//        model.addAttribute("nombre",paciente.getNombre());
//        model.addAttribute("apellido",paciente.getApellido());
//        model.addAttribute("matricula",matricula);
//
//        return "index";
//
//        //return pacienteService.buscarPorEmail(email);
//    }


}
