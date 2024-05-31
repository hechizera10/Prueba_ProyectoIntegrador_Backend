package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;

import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller //<-- es controller pq vamos a usar una tecnologia de vista
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService= new OdontologoService();
    }

    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.

    @GetMapping("/{id}") //--> nos permite buscar un odontologo por id
    public Odontologo buscarOdontologoPorId(@PathVariable("id") Integer id){
        return odontologoService.buscarOdontologoPorId(id);
    }

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){

        Odontologo odontologoBuscado= odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoBuscado!=null){
            odontologoService.actualizarOdontologo(odontologo);
            return "odontologo actualizado con exito";
        }else{
            return "odontologo no encontrado";
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable("id") Integer id){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado!=null){
            odontologoService.eliminarOdontologo(id);
        }

    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosLosOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }


//    @GetMapping
//    public String buscarOdontologoPorId(Model model, @RequestParam("id") Integer id){
//
//        Odontologo odontologo= odontologoService.buscarOdontologoPorId(id);
//
//        model.addAttribute("matricula",odontologo.getMatricula());
//        return "index";
//    }
}
