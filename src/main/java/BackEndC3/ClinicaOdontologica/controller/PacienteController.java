package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //<-- es controller pq vamos a usar una tecnologia de vista
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public PacienteController() {
        pacienteService= new PacienteService();
    }
    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
    @GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){

        Paciente paciente= pacienteService.buscarPorEmail(email);
        Integer matricula =paciente.getOdontologo().getMatricula();

        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        model.addAttribute("matricula",matricula);

        return "index";

        //return pacienteService.buscarPorEmail(email);
    }
}
