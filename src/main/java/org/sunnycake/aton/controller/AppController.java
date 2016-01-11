package org.sunnycake.aton.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.service.EquipoService;
import org.sunnycake.aton.service.SalaService;

@Controller
// Controlador por defecto (Con el url raíz "/")
@RequestMapping("/")
public class AppController {

	@Autowired
	EquipoService equipoService;

	@Autowired
	SalaService salaService;

	@Autowired
	MessageSource messageSource;

	private Logger logger = LogManager.getLogger(AppController.class.getName());

	/*
	 * TODO: Cambiar para ATON: Generar todos los mapping para los dto Bajo los
	 * url "/" y "/list" se traerán todos los empleados
	 */
	@RequestMapping(value = { "/", "/equipos" }, method = RequestMethod.GET)
	public String listarEquipos(ModelMap model) {

		List<Equipo> equipos = equipoService.buscarTodosLosEquipos();
		model.addAttribute("equipos", equipos);
		return "todoslosequipos";
	}

	/*
	 * Este es un método GET para nuevos empleados (Obtiene el formulario)
	 */
	@RequestMapping(value = { "/nuevo" }, method = RequestMethod.GET)
	public String nuevoEquipo(ModelMap model) {
		Equipo equipo = new Equipo();
		model.addAttribute("equipo", equipo);
		model.addAttribute("edit", false);
		return "registro";
	}

	/**
	 * Cuando se envía el formulario se envía a través de POST la solicitud.
	 * También se hace validación de la entrada \@Valid valida que el equipo sea
	 * válido desde Spring
	 * 
	 * @param equipo
	 *            Empleado a validar y guardar
	 * @param result
	 *            Resultado de la validación del empleado (Válido o no válido
	 *            con explicación)
	 * @param model
	 * @return JSP
	 */
	@RequestMapping(value = { "/nuevo" }, method = RequestMethod.POST)
	public String guardarEquipo(@Valid Equipo equipo, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registro";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be
		 * implementing custom @Unique annotation and applying it on field [ssn]
		 * of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 * 
		 */
		// Cómo verificar que es único el empleado de forma personalizada
		if (!equipoService.esMacUnica(equipo.getMac())) {
			FieldError errorDeMac = new FieldError("equipo", "mac",
					messageSource.getMessage("non.unique.mac", new String[] { equipo.getMac()}, Locale.getDefault()));
			result.addError(errorDeMac);
			return "registro";
		}
		
		//equipo.setMac(equipo.getMac().replaceAll(",", ""));

		logger.debug("/nuevo: Equipo a guardar: " + equipo);

		equipoService.guardarEquipo(equipo);

		model.addAttribute("exito", equipo + ", guardado exitosamente");
		return "exito";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	/**
	 * Actualiza la información de un empleado
	 * 
	 * @param ip
	 *            Identificador del empleado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/editar-equipo-{mac}" }, method = RequestMethod.GET)
	public String editarEquipo(@PathVariable String mac, ModelMap model) {
		Equipo equipo = equipoService.buscarEquipoPorMac(mac);
		model.addAttribute("equipo", equipo);
		model.addAttribute("edit", true);
		return "registro";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/actualizar-equipo-{mac}" }, method = RequestMethod.POST)
	public String actualizarEquipo(@Valid Equipo equipo, BindingResult result, ModelMap model,
			@PathVariable String ip) {

		if (result.hasErrors()) {
			return "registro";
		}

		if (!equipoService.esMacUnica(ip)) {
			FieldError errorIp = new FieldError("equipo", "ip",
					messageSource.getMessage("non.unique.ip", new String[] { equipo.getIp() }, Locale.getDefault()));
			result.addError(errorIp);
			return "registro";
		}

		equipoService.actualizarEquipo(equipo);

		model.addAttribute("exito", "Equipo " + equipo.getMac() + " actualizado exitosamente");
		return "exito";
	}

	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/eliminar-equipo-{mac}" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String mac) {
		equipoService.eliminarEquipoPorMac(mac);
		return "redirect:/equipos";
	}

	/**
	 * 
	 */
	@ModelAttribute("salas")
	public List<Sala> inicializarSalas() {
		return salaService.buscarTodasLasSalas();
	}

}