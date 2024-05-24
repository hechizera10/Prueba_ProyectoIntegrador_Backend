package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class OdontologoDAOList implements iDao<Odontologo>{
    private List<Odontologo> odontologos = new ArrayList<>();
    private static final Logger logger= Logger.getLogger(OdontologoDAOList.class);
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologos.add(odontologo);
        logger.info("Odontologo guardado con éxito");
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
       logger.info("Buscando todos los odontologos");
        for (Odontologo odontologo : odontologos) {
            logger.info("ID: " + odontologo.getId() + " Nombre: " + odontologo.getNombre() + " Apellido: " + odontologo.getApellido() + " Matrícula: " + odontologo.getMatricula());
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        return null;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }


}
