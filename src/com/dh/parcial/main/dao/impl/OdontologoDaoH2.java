package com.dh.parcial.main.dao.impl;

import com.dh.parcial.main.dao.IDao;
import com.dh.parcial.main.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    //Logger
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);


    //Conexión. Estático porque no vamos a modificarlo
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el create.sql
    private final static String DB_URL = "jdbc:h2:~/db_odontologos;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    //Constructor vacio
    public OdontologoDaoH2() {
    }

    //Sobreescribiendo los métodos de la interfaz
    @Override
    public Odontologo guardar(Odontologo odontologo){
        //Para poder guardar en una base de datos

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1. Levantar driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2.Crear una sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos (matricula, nombre, apellido) VALUES(?,?,?)");
            // El campo id no esta, porque es autoincremental
            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());


            //3.Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            //4. Status mostrado con los logger
            logger.info("Guardando odontologx");

            //Cerramos el preparedStatement
            preparedStatement.close();


        //5.Atrapar las excepciones y loggear los errores
        } catch (Exception e) {
            logger.error("No se pudo realizar la operación: " + e.getMessage());
        } finally {
            try {
                connection.close();
            }catch (SQLException throwables){
                logger.error("No se pudo realizar la operación: " + throwables.getMessage());
            }
        }
        return odontologo;
    }


    @Override
    public Odontologo buscar(int id){
        //Para buscar en una base de datos

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;

        try {
            //1.Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2.Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos where id = ?");
            preparedStatement.setInt(1,id);

            //3.Ejecutar una sentencia SQL
            ResultSet rs = preparedStatement.executeQuery();

            //4.Obtener resultados
            while (rs.next()) {
                int idOdontologo = rs.getInt("id");
                int matricula = rs.getInt("matricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                odontologo = new Odontologo(idOdontologo,matricula,nombre,apellido);
            }

            //5. Status mostrado con los logger
            logger.debug("Buscando odontologx");
            logger.info(odontologo.toString());
            logger.debug("Odontologx buscadx");

            //Cerramos el preparedStatement
            preparedStatement.close();

        //6.Atrapar las excepciones y loggear los errores
        } catch (Exception e) {
            logger.error("No se pudo realizar la operación: " + e.getMessage());
        } finally {
            try {
                connection.close();
            }catch (SQLException throwables){
                logger.error("No se pudo realizar la operación: " + throwables.getMessage());
            }
        }
        return odontologo;

    }

    @Override
    public void eliminar(int id){
        //Para eliminar de una base de datos
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1.Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2.Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setInt(1,id);

            //3.Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();


            //4. Status mostrado con los logger
            logger.debug("Eliminando odontologx");
            logger.info("Odontologx eliminadx");

            //Cerramos el preparedStatement
            preparedStatement.close();


        //5.Atrapar las excepciones y loggear errores
        } catch (Exception e) {
            logger.error("No se pudo realizar la operación: " + e.getMessage());
        } finally {
            try {
                connection.close();
            }catch (SQLException throwables){
                logger.error("No se pudo realizar la operación: " + throwables.getMessage());
            }
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        //Para listar todos los odontologos en una base de datos
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            //1.Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2.Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");

            //3.Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4.Obtener resultados
            while (result.next()) {

                int idOdontologo = result.getInt("id");
                int matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                Odontologo odontologo = new Odontologo(idOdontologo,matricula,nombre, apellido);


                //4. Status mostrado con los logger
                logger.info("Agregando  odontologx a la lista");
                odontologos.add(odontologo);
                logger.info(odontologo.toString());

            }
            //Cerramos el preparedStatement
            preparedStatement.close();

        //6.Atrapar las excepciones y loggear los errores
        } catch (Exception e) {
            logger.error("No se pudo realizar la operación: " + e.getMessage());
        } finally {
            try {
                connection.close();
            }catch (SQLException throwables){
                logger.error("No se pudo realizar la operación: " + throwables.getMessage());
            }
        }

        return odontologos;
    }
}
