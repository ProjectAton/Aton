/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sunnycake.aton.service.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.sunnycake.aton.dao.EquipoDAO;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.exception.ExcepcionConsulta;
import org.sunnycake.aton.service.EquipoService;
import org.testng.Assert;

/**
 *
 * @author camilo.sampedro
 */
public class EquipoServiceImplNGTest {

    @Mock
    EquipoDAO dao;

    @InjectMocks
    EquipoService service;

    @Spy
    List<Equipo> equipos = new ArrayList<>();

    @org.testng.annotations.BeforeClass
    public void setUpClass() throws Exception {
        MockitoAnnotations.initMocks(this);
        equipos = obtenerListaEquipos();
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of buscarEquipoPorIp method, of class EquipoServiceImpl.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    @org.testng.annotations.Test
    public void testBuscarEquipoPorIp() throws ExcepcionConsulta {
        Equipo equipo = equipos.get(0);
        when(dao.buscarEquipoPorIp(anyString())).thenReturn(equipo);
        Assert.assertEquals(service.buscarEquipoPorIp(equipo.getIp()), equipo);
    }

    /**
     * Test of guardarEquipo method, of class EquipoServiceImpl.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    @org.testng.annotations.Test
    public void testGuardarEquipo() throws ExcepcionConsulta {
        doNothing().when(dao).guardarEquipo(any(Equipo.class));
        service.guardarEquipo(any(Equipo.class));
        verify(dao, atLeastOnce()).guardarEquipo(any(Equipo.class));
    }

    /**
     * Test of actualizarEquipo method, of class EquipoServiceImpl.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    @org.testng.annotations.Test
    public void testActualizarEquipo() throws ExcepcionConsulta {
        Equipo equipo = equipos.get(0);
        when(dao.buscarEquipoPorIp(anyString())).thenReturn(equipo);
        service.actualizarEquipo(equipo);
        verify(dao, atLeastOnce()).buscarEquipoPorIp(anyString());
    }

    /**
     * Test of eliminarEquipoPorIp method, of class EquipoServiceImpl.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    @org.testng.annotations.Test
    public void testEliminarEquipoPorIp() throws ExcepcionConsulta {
        doNothing().when(dao).eliminarEquipoPorIp(anyString());
        service.eliminarEquipoPorIp(anyString());
        verify(dao, atLeastOnce()).eliminarEquipoPorIp(anyString());
    }

    /**
     * Test of buscarTodosLosEquipos method, of class EquipoServiceImpl.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    @org.testng.annotations.Test
    public void testBuscarTodosLosEquipos() throws ExcepcionConsulta {
        when(dao.obtenerTodos()).thenReturn(new HashSet<>(equipos));
        Assert.assertEquals(service.buscarTodosLosEquipos(), equipos);
    }

    /**
     * Test of buscarEquipoPorNombre method, of class EquipoServiceImpl.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    @org.testng.annotations.Test
    public void testBuscarEquipoPorNombre() throws ExcepcionConsulta {
        Equipo equipo = equipos.get(0);
        when(dao.buscarEquipoPorNombre(anyString())).thenReturn(equipo);
        Assert.assertEquals(service.buscarEquipoPorNombre(anyString()), equipo);
    }

    /**
     * Test of esIpUnica method, of class EquipoServiceImpl.
     *
     * @throws org.sunnycake.aton.exception.ExcepcionConsulta
     */
    @org.testng.annotations.Test
    public void testEsIpUnica() throws ExcepcionConsulta {
        Equipo equipo = equipos.get(0);
        when(dao.buscarEquipoPorIp(anyString())).thenReturn(equipo);
        Assert.assertEquals(service.esIpUnica(equipo.getIp()), true);
    }

    public List<Equipo> obtenerListaEquipos() {
        Laboratorio l1 = new Laboratorio();
        l1.setId(1);
        l1.setAdministracion("AdminLIS");
        l1.setUbicacion("LIS");

        Sala s1 = new Sala();
        s1.setId(1);
        s1.setEnseres("ADAD");
        s1.setMediosAudiovisuales("TV");
        s1.setLaboratorio(l1);

        Equipo e1 = new Equipo();
        e1.setIp("localhost");
        e1.setUsuario("camilo.sampedro");
        e1.setPassword("00000");
        e1.setSala(s1);

        Equipo e2 = new Equipo();
        e2.setIp("192.168.194.18");
        e2.setUsuario("camilo");
        e2.setPassword("00000");
        e2.setSala(s1);

        equipos.add(e1);
        equipos.add(e2);
        return equipos;
    }

}
