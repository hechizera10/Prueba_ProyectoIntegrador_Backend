package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.dao.PacienteDAOH2;
import BackEndC3.ClinicaOdontologica.dao.iDao;
import BackEndC3.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {
private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    //metodos manuales
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        return pacienteiDao.buscarPorId(id);
    }
    public List<Paciente> buscarTodosLosPacientes(){return pacienteiDao.buscarTodos();}
    public Paciente buscarPorEmail(String email){
        return pacienteiDao.buscarPorString(email);
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }

    public Boolean eliminarPaciente(Integer id){
        return pacienteiDao.eliminar(id);
    }

}
