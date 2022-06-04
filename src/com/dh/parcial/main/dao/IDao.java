package com.dh.parcial.main.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T>{ //La T responde al tipo
    //Métodos CRUD que serán implementados en la clase OdontologoDaoH2
    public T guardar(T t);
    public T buscar(int id);
    public void eliminar(int id);
    public List<T> buscarTodos();
}
