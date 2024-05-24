package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Odontologo;

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
    private static final String SQL_SAVE="INSERT INTO ODONTOLOGOS (ID,NOMBRE,APELLIDO,MATRICULA) VALUES (?,?,?,?)";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciando la operacion de guardado de un odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSave= connection.prepareStatement(SQL_SAVE);
            psSave.setInt(1,odontologo.getId());
            psSave.setString(2,odontologo.getNombre());
            psSave.setString(3,odontologo.getApellido());
            psSave.setInt(4,odontologo.getMatricula());
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
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

}
