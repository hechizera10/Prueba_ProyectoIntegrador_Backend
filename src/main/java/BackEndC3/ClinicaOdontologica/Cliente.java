package BackEndC3.ClinicaOdontologica;

import BackEndC3.ClinicaOdontologica.dao.BD;
import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;

import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Cliente.class);
        BD.crearTablas();

        logger.info("Guardando un odontologo en H2");
        Odontologo odontologo1 = new Odontologo( 4,"Juan", "Perez", 12345678);
        OdontologoService odontologoService = new OdontologoService();
        odontologoService.guardarOdontologo(odontologo1);


        logger.info("Iniciando Buscar Todos en H2");
        odontologoService.buscarTodos();

        logger.info("Iniciando Buscar Paciente por email");
        PacienteService pacienteService = new PacienteService();
        pacienteService.buscarPorEmail("german@german.com");

    }
}