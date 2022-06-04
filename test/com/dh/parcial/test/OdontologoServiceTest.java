package com.dh.parcial.test;

import com.dh.parcial.main.dao.impl.OdontologoDaoH2;
import com.dh.parcial.main.entity.Odontologo;
import com.dh.parcial.main.service.OdontologoService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)

public class OdontologoServiceTest {
    private static OdontologoService odontologoService =new OdontologoService(new OdontologoDaoH2());

    @BeforeClass
    //Lo usamos porque necesitamos que las pruebas compartan el mismo código.
    //Se ejecuta como inicializador estático, por lo tanto, se ejecutará antes de que se cree la instancia de clase de su dispositivo de prueba.
    public static void cargarOdontologos() {
        //Instanciamos odontologos
        Odontologo odontologo1 = new Odontologo(123,"Pablo", "Perez");
        Odontologo odontologo2 = new Odontologo(234, "Mariana", "Ramos");
        Odontologo odontologo3 = new Odontologo(456, "Roque", "Fernandez");

        //Los guardamos en odontologoService
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.guardarOdontologo(odontologo3);

    }

    @Test
    public void listarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodosLosOdontologos();
//        int resultadoEsperado = 3;

        //Negamos que la lista este vacia, o sea se quiere comprobar que tiene algo
        Assert.assertTrue(!odontologos.isEmpty());
        //Evaluamos que el largo de la lista sea mayor a 0
        Assert.assertTrue(odontologos.size()>0);
        //Evaluamos que el largo del listado sea igual al resultado esperado
//        Assert.assertEquals(odontologos.size(), resultadoEsperado);

    }

}
