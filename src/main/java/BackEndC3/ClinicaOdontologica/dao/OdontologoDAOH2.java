package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Domicilio;
import BackEndC3.ClinicaOdontologica.model.Odontologo;

import BackEndC3.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SAVE="INSERT INTO ODONTOLOGOS (NOMBRE,APELLIDO,MATRICULA) VALUES (?,?,?)";
    private static final String SQL_SELECT_BY_ID="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=? WHERE ID=?";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciando la operacion de guardado de un odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSave= connection.prepareStatement(SQL_SAVE,Statement.RETURN_GENERATED_KEYS);
            psSave.setString(1,odontologo.getNombre());
            psSave.setString(2,odontologo.getApellido());
            psSave.setInt(3,odontologo.getMatricula());
            psSave.execute();
            logger.info("Odontologo guardado con éxito");

            // --> Hasta aca ibamos y el profe agrego la siguiente linea

            ResultSet clave = psSave.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }


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
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()){
                logger.info("Buscando odontologos");

                odontologo=new Odontologo(resultSet.getInt("ID"),resultSet.getString("NOMBRE"),resultSet.getString(
                        "APELLIDO"),resultSet.getInt("MATRICULA"));
                odontologos.add(odontologo);
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
        logger.info("Iniciando las operaciones de eliminacion de un odontologo con id : "+id);
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psDelete= connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);
            psDelete.execute();
            logger.info("Odontologo eliminado con exito");

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.warn("Iniciando las operaciones de actualizacion de un paciente con id : "+odontologo.getId());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);

            psUpdate.setString(1, odontologo.getNombre());
            psUpdate.setString(2, odontologo.getApellido());
            psUpdate.setInt(3, odontologo.getMatricula());
            psUpdate.setInt(4, odontologo.getId());
            psUpdate.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
