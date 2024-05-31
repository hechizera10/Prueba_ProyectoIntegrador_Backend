package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TurnoDAOLISTA implements iDao<Turno>{
    private Logger logger= Logger.getLogger(TurnoDAOLISTA.class);
    private List<Turno> turnos= new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        logger.info("Iniciando las operaciones de guardado de un turno");
        PacienteDAOH2 pacienteDAOH2= new PacienteDAOH2();
        OdontologoDAOH2 odontologoDAOH2= new OdontologoDAOH2();

        turno.setPaciente(pacienteDAOH2.buscarPorId(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDAOH2.buscarPorId(turno.getOdontologo().getId()));
        turnos.add(turno);
        logger.info("Turno guardado con exito");
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        for (Turno turno : turnos) {
            if(turno.getId().equals(id)){
                return turno;
            }else{
                System.out.println("turno no encontrado");
            }

        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        for (Turno turno : turnos) {
            if (turno.getId().equals(id)){
                turnos.remove(turno);
                break;
            }
        }
    }

    @Override
    public void actualizar(Turno turno) {
        for (Turno turno1 : turnos) {
            if(turno1.getId().equals(turno.getId())){
                turnos.remove(turno1);
                turnos.add(turno);
                break;
            }
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        logger.info("iniciando la operacion de mostrar todos los turnos");
        return turnos;
    }

    @Override
    public Turno buscarPorString(String string) {
        return null;
    }
}
