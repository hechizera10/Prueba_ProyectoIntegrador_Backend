package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Domicilio;
import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements iDao<Paciente> {
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_INSERT="INSERT INTO PACIENTES(NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL, ODONTOLOGO_ID) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=?";


    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("inciando las operaciones de guardado");
        Connection connection= null;
        DomicilioDAOH2 daoAux= new DomicilioDAOH2();
        Domicilio domicilio=  daoAux.guardar(paciente.getDomicilio());
        try{
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf((paciente.getFechaIngreso())));
        psInsert.setInt(5,domicilio.getId());
        psInsert.setString(6, paciente.getEmail());
        psInsert.execute();
        ResultSet clave= psInsert.getGeneratedKeys();
        while (clave.next()){
            paciente.setId(clave.getInt(1));
        }
        logger.info("paciente guardado");


        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        logger.info("iniciando la operacion de buscado de un paciente con id : "+id);
        Connection connection= null;
        Paciente paciente= null;
        Domicilio domicilio= null;
        Odontologo odontologo = null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSElectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSElectOne.setInt(1,id);
            ResultSet rs= psSElectOne.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            OdontologoDAOH2 daoOdontologo= new OdontologoDAOH2();
            while(rs.next()){
                domicilio= daoAux.buscarPorId(rs.getInt(6));
                odontologo= daoOdontologo.buscarPorId(rs.getInt(8));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7),odontologo);
            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }


        return paciente;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.info("iniciando las operaciones de busqueda:");
        Connection connection=null;
        List<Paciente> pacientes= new ArrayList<>();
        Paciente paciente= null;
        Domicilio domicilio=null;
        Odontologo odontologo= null;
        DomicilioDAOH2 daoAux= new DomicilioDAOH2();
        OdontologoDAOH2 daoOdontologo= new OdontologoDAOH2();
        try{
            connection=BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs= statement.executeQuery(SQL_SELECT_ALL);
            while(rs.next()){
                domicilio= daoAux.buscarPorId(rs.getInt(6));
                odontologo= daoOdontologo.buscarPorId(rs.getInt(8));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7), odontologo);
                pacientes.add(paciente);
            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return pacientes;
    }

    @Override
    public Paciente buscarPorString(String string) {
        logger.info("iniciando la busqueda por email: "+string);
        Connection connection=null;
        Paciente paciente= null;
        Domicilio domicilio= null;
        Odontologo odontologo= null;
        DomicilioDAOH2 daoAux= new DomicilioDAOH2();
        OdontologoDAOH2 daoOdontologo= new OdontologoDAOH2();
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectE= connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectE.setString(1,string);
            ResultSet rs= psSelectE.executeQuery();
            while(rs.next()){
                domicilio= daoAux.buscarPorId(rs.getInt(6));
                odontologo= daoOdontologo.buscarPorId(rs.getInt(8));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7), odontologo);

            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return paciente;
    }
        /*private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private LocalDate fechaIngreso;
    private Domicilio domicilio;*/
}
