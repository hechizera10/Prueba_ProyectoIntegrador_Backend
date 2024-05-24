package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Domicilio;
import BackEndC3.ClinicaOdontologica.model.Odontologo;

import BackEndC3.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SAVE="INSERT INTO ODONTOLOGOS (NOMBRE,APELLIDO,MATRICULA) VALUES (?,?,?)";
    private static final String SQL_SELECT_BY_ID="SELECT * FROM ODONTOLOGOS WHERE ID=?";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciando la operacion de guardado de un odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSave= connection.prepareStatement(SQL_SAVE);
            psSave.setString(1,odontologo.getNombre());
            psSave.setString(2,odontologo.getApellido());
            psSave.setInt(3,odontologo.getMatricula());
            psSave.execute();
            logger.info("Odontologo guardado con éxito");


        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Buscando todos los odontologos");
        Connection connection= null;
        List<Odontologo> odontologos = new ArrayList<>();
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()){
                logger.info("ID: "+resultSet.getInt("ID")+" Nombre: "+resultSet.getString("NOMBRE")+" Apellido: "+resultSet.getString("APELLIDO")+" Matricula: "+resultSet.getInt("MATRICULA"));
                odontologos.add(new Odontologo(resultSet.getInt("ID"),resultSet.getString("NOMBRE"),resultSet.getString("APELLIDO"),resultSet.getInt("MATRICULA")));
            }
        }catch (Exception e){
            logger.error(e.getMessage());

        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        return null;
    }


    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("iniciando la busqueda por id: "+id);
        Connection connection=null;
        Odontologo odontologo= null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectID= connection.prepareStatement(SQL_SELECT_BY_ID);
            psSelectID.setInt(1,id);
            ResultSet rs= psSelectID.executeQuery();
            while(rs.next()){
                odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

}
