package org.sunnycake.aton.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import java.util.ArrayList;
import java.util.List;

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
 * Test de la clase {@link org.sunnycake.aton.controller.AppController}
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
	AppController appController;

	@Spy
	List<Equipo> equipos = new ArrayList<Equipo>();

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
	public void listarEmpleados() {
		when(equipoService.buscarTodosLosEquipos()).thenReturn(equipos);
		Assert.assertEquals(appController.listarEquipos(model), "todoslosequipos");
		Assert.assertEquals(model.get("equipos"), equipos);
		verify(equipoService, atLeastOnce()).buscarTodosLosEquipos();
	}

	@Test
	public void nuevoEquipo() {
		Assert.assertEquals(appController.nuevoEquipo(model), "registro");
		Assert.assertNotNull(model.get("equipo"));
		Assert.assertFalse((Boolean) model.get("edit"));
		Assert.assertEquals(((Equipo) model.get("equipo")).getMac(), null);
	}

	@Test
	public void guardarEquipoConValidacionDeErrores() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(equipoService).guardarEquipo(any(Equipo.class));
		Assert.assertEquals(appController.guardarEquipo(equipos.get(0), result, model), "registro");
	}

	@Test
	public void guardarEquipoConValidacionDeErrorMacNoUnica() {
		when(result.hasErrors()).thenReturn(false);
		when(equipoService.esMacUnica(anyString())).thenReturn(false);
		Assert.assertEquals(appController.guardarEquipo(equipos.get(0), result, model), "registro");
	}

	@Test
	public void guardarEmpleadoConExito() {
		when(result.hasErrors()).thenReturn(false);
		when(equipoService.esMacUnica(anyString())).thenReturn(true);
		doNothing().when(equipoService).guardarEquipo(any(Equipo.class));
		Assert.assertEquals(appController.guardarEquipo(equipos.get(0), result, model), "exito");
		Assert.assertEquals(model.get("exito"), "Equipo [mac=1, ip=1.1.1.1, sala=Sala 1, Laboratorio 1, descripcion=Muy bueno], guardado exitosamente");
	}

	@Test
	public void editarEquipo() {
		Equipo equipo = equipos.get(0);
		when(equipoService.buscarEquipoPorMac(anyString())).thenReturn(equipo);
		Assert.assertEquals(appController.editarEquipo(anyString(), model), "registro");
		Assert.assertNotNull(model.get("equipo"));
		Assert.assertTrue((Boolean) model.get("edit"));
		Assert.assertEquals(((Equipo) model.get("equipo")).getMac(), "1");
	}

	@Test
	public void actualizarEquipoConValidacionDeError() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(equipoService).actualizarEquipo(any(Equipo.class));
		Assert.assertEquals(appController.actualizarEquipo(equipos.get(0), result, model, ""), "registro");
	}
	
	@Test
    public void actualizarEquipoConValidacionDeErrorMacNoUnica(){
        when(result.hasErrors()).thenReturn(false);
        when(equipoService.esMacUnica(anyString())).thenReturn(false);
        Assert.assertEquals(appController.actualizarEquipo(equipos.get(0), result, model,""), "registro");
    }
	
	@Test
    public void actualizarEquipoConExito(){
        when(result.hasErrors()).thenReturn(false);
        when(equipoService.esMacUnica(anyString())).thenReturn(true);
        doNothing().when(equipoService).actualizarEquipo(any(Equipo.class));
        Assert.assertEquals(appController.actualizarEquipo(equipos.get(0), result, model, ""), "exito");
        Assert.assertEquals(model.get("exito"), "Equipo 1 actualizado exitosamente");
    }
	
	@Test
    public void eliminarEquipo(){
        doNothing().when(equipoService).eliminarEquipoPorMac(anyString());
        Assert.assertEquals(appController.deleteEmployee("123"), "redirect:/equipos");
    }
	
	public List<Equipo> buscarTodosLosEquipos(){
		Laboratorio l1 = new Laboratorio();
		l1.setId(1);
		l1.setAdministracion("Lis");
		l1.setUbicacion("LIS");
		
		Sala s1 = new Sala();
		s1.setId(1);
		s1.setEnseres("ADAD");
		s1.setMediosAudiovisuales("TV");
		s1.setLaboratorio(l1);
		
		Equipo e1 = new Equipo();
        e1.setMac("1");
        e1.setIp("1.1.1.1");
        e1.setDescripcion("Muy bueno");
        e1.setSala(s1);
         
        Equipo e2 = new Equipo();
        e2.setMac("2");
        e2.setIp("2.2.2.2");
        e2.setDescripcion("3.3.3.3");
        e2.setSala(s1);
         
        equipos.add(e1);
        equipos.add(e2);
        return equipos;
    }
}
