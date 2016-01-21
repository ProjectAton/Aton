package org.sunnycake.aton.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.service.EquipoService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

/**
 * Test de la clase {@link org.sunnycake.aton.controller.ControladorInicio}
 *
 * @author Camilo Sampedro
 *
 */
public class AppControllerTest {

    @Mock
    EquipoService equipoService;

    @Mock
    MessageSource message;

    @InjectMocks
    ControladorInicio appController;

    @Spy
    Set<Equipo> equipos = new HashSet<>();

    @Spy
    ModelMap model;

    @Mock
    BindingResult result;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        equipos = buscarTodosLosEquipos();
    }

    @Test
    public void listarEquipos() {
        when(equipoService.buscarTodosLosEquipos()).thenReturn(equipos);
        Assert.assertEquals("todoslosequipos", appController.listarEquipos(model));
        Assert.assertEquals(model.get("equipos"), equipos);
        verify(equipoService, atLeastOnce()).buscarTodosLosEquipos();
    }

    @Test
    public void nuevoEquipo() {
        Assert.assertEquals("registro", appController.nuevoEquipo(model));
        Assert.assertNotNull(model.get("equipo"));
        Assert.assertFalse((Boolean) model.get("edit"));
        Assert.assertEquals(((Equipo) model.get("equipo")).getMac(), null);
    }

    @Test
    public void guardarEquipoConValidacionDeErrores() {
        when(result.hasErrors()).thenReturn(true);
        doNothing().when(equipoService).guardarEquipo(any(Equipo.class));
        Iterator<Equipo> iter = equipos.iterator();
        Equipo equipo = iter.next();
        Assert.assertEquals("registro", appController.guardarEquipo(equipo, result, model));
    }

    @Test
    public void guardarEquipoConValidacionDeErrorMacNoUnica() {
        when(result.hasErrors()).thenReturn(false);
        when(equipoService.esIpUnica(anyString())).thenReturn(false);
        Iterator<Equipo> iter = equipos.iterator();
        Equipo equipo = iter.next();
        Assert.assertEquals("registro", appController.guardarEquipo(equipo, result, model));
    }

    @Test
    public void guardarEquipoConExito() {
        when(result.hasErrors()).thenReturn(false);
        when(equipoService.esIpUnica(anyString())).thenReturn(true);
        doNothing().when(equipoService).guardarEquipo(any(Equipo.class));
        Iterator<Equipo> iter = equipos.iterator();
        Equipo equipo = iter.next();
        Assert.assertEquals("redirect:/admin/editar-equipo-localhost", appController.guardarEquipo(equipo, result, model));
        Assert.assertEquals(model.get("equipo"), equipo);
    }

    @Test
    public void editarEquipo() {
        Iterator<Equipo> iter = equipos.iterator();
        Equipo equipo = iter.next();
        when(equipoService.buscarEquipoPorIp(anyString())).thenReturn(equipo);
        Assert.assertEquals("edicion", appController.editarEquipo(anyString(), model));
        Assert.assertNotNull(model.get("equipo"));
        Assert.assertTrue((Boolean) model.get("edit"));
        Assert.assertEquals("localhost", ((Equipo) model.get("equipo")).getIp());
    }

    @Test
    public void actualizarEquipoConValidacionDeError() {
        when(result.hasErrors()).thenReturn(true);
        doNothing().when(equipoService).actualizarEquipo(any(Equipo.class));
        Iterator<Equipo> iter = equipos.iterator();
        Equipo equipo = iter.next();
        Assert.assertEquals("edicion", appController.actualizarEquipo(equipo, result, model, ""));
    }

    @Test
    public void actualizarEquipoConValidacionDeErrorMacNoUnica() {
        when(result.hasErrors()).thenReturn(false);
        when(equipoService.esIpUnica(anyString())).thenReturn(false);
        Iterator<Equipo> iter = equipos.iterator();
        Equipo equipo = iter.next();
        Assert.assertEquals("redirect:/admin/", appController.actualizarEquipo(equipo, result, model, ""));
    }

    @Test
    public void actualizarEquipoConExito() {
        when(result.hasErrors()).thenReturn(false);
        when(equipoService.esIpUnica(anyString())).thenReturn(true);
        doNothing().when(equipoService).actualizarEquipo(any(Equipo.class));
        Iterator<Equipo> iter = equipos.iterator();
        Equipo equipo = iter.next();
        Assert.assertEquals("redirect:/admin/", appController.actualizarEquipo(equipo, result, model, ""));
    }

    @Test
    public void eliminarEquipo() {
        doNothing().when(equipoService).eliminarEquipoPorIp(anyString());
        Assert.assertEquals("redirect:/equipos", appController.deleteEmployee("123"));
    }

    public Set<Equipo> buscarTodosLosEquipos() {
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
