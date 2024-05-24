package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.dao.OdontologoDAOH2;
import BackEndC3.ClinicaOdontologica.dao.OdontologoDAOList;
import BackEndC3.ClinicaOdontologica.dao.PacienteDAOH2;
import BackEndC3.ClinicaOdontologica.dao.iDao;
import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;
    private iDao<Odontologo> odontologoiDaoList;

    public OdontologoService() {
        odontologoiDao= new OdontologoDAOH2();
        odontologoiDaoList = new OdontologoDAOList();
    }
    //metodos manuales
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public List<Odontologo> buscarTodos(){
        return odontologoiDao.buscarTodos();
    }
    public Odontologo buscarOdontologoPorId(Integer id){
        return odontologoiDao.buscarPorId(id);
    }
    //public Odontologo guardarOdontologoEnLista(Odontologo odontologo){
       // return odontologoiDaoList.guardar(odontologo);
    //}
    //public List<Odontologo> buscarTodosEnLista(){return odontologoiDaoList.buscarTodos();
 //}
}
