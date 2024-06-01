package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;

import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable("id") Integer id){
        if (odontologoService.buscarOdontologoPorId(id)==null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(odontologoService.buscarOdontologoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){

        Odontologo odontologoBuscado= odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoBuscado!=null){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("odontologo actualizado con exito");
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarOdontologo(@PathVariable Integer id){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        ResponseEntity response = null;

        if (odontologoService.eliminarOdontologo(id)) {
            response = ResponseEntity.status(HttpStatus.OK).build();
        }else {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
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
