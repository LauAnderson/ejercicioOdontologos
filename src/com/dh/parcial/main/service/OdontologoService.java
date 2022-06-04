package com.dh.parcial.main.service;

import com.dh.parcial.main.dao.IDao;
import com.dh.parcial.main.entity.Odontologo;

import java.sql.SQLException;
import java.util.List;

public class OdontologoService {
    //Atributo
    private IDao<Odontologo> odontologoIDao;

    //Constructor
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    //Métodos que delegan las responsabilidades de las acciones al Dao
    public Odontologo guardarOdontologo (Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public Odontologo buscarOdontologo (int id){
        return  odontologoIDao.buscar(id);
    }

    public void eliminarOdontologo(int id){
        odontologoIDao.eliminar(id);
    }

    public List<Odontologo> buscarTodosLosOdontologos(){
        return odontologoIDao.buscarTodos();
    }

}
