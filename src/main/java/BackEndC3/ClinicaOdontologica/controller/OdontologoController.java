package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;

import BackEndC3.ClinicaOdontologica.service.OdontologoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //<-- es controller pq vamos a usar una tecnologia de vista
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService= new OdontologoService();
    }
    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
    @GetMapping
    public String buscarOdontologoPorId(Model model, @RequestParam("id") Integer id){

        Odontologo odontologo= odontologoService.buscarOdontologoPorId(id);

        model.addAttribute("matricula",odontologo.getMatricula());
        return "index";


    }
}
