package org.sunnycake.aton.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Laboratorio;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.dto.Sala;
import org.sunnycake.aton.exec.Ejecucion;
import org.sunnycake.aton.exec.ExecBuffer;
import org.sunnycake.aton.exec.Tarea;
import org.sunnycake.aton.service.EquipoService;
import org.sunnycake.aton.service.LaboratorioService;
import org.sunnycake.aton.service.OrdenService;
import org.sunnycake.aton.service.SalaService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
// Controlador por defecto (Con el url raíz "/")
@RequestMapping("/")
public class AppController {

	@Autowired
	EquipoService equipoService;

	@Autowired
	SalaService salaService;

	@Autowired
	OrdenService ordenService;

	@Autowired
	LaboratorioService laboratorioService;

	@Autowired
	MessageSource messageSource;

	private Logger logger = LogManager.getLogger(AppController.class.getName());

	/*
	 * TODO: Cambiar para ATON: Generar todos los mapping para los dto Bajo los
	 * url "/" y "/list" se traerán todos los empleados
	 */
	@RequestMapping(value = { "/", "/equipos" }, method = RequestMethod.GET)
	public String listarEquipos(ModelMap model) {
		logger.debug("Obteniendo raíz");
		List<Equipo> equipos = equipoService.buscarTodosLosEquipos();
		model.addAttribute("equipos", equipos);
		model.addAttribute("user", obtenerUsuario());
		return "todoslosequipos";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", obtenerUsuario());
		List<Equipo> equipos = equipoService.buscarTodosLosEquipos();
		model.addAttribute("equipos", equipos);
		return "adminprincipal";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", obtenerUsuario());
		return "dba";
	}

	/*
	 * TODO: Cambiar para ATON: Generar todos los mapping para los dto Bajo los
	 * url "/" y "/list" se traerán todos los empleados
	 */
	@RequestMapping(value = { "/admin/salas" }, method = RequestMethod.GET)
	public String listarSalas(ModelMap model) {

		List<Sala> salas = salaService.buscarTodasLasSalas();
		model.addAttribute("salas", salas);
		return "todaslassalas";
	}

	/*
	 * TODO: Cambiar para ATON: Generar todos los mapping para los dto Bajo los
	 * url "/" y "/list" se traerán todos los empleados
	 */
	@RequestMapping(value = { "/admin/laboratorios" }, method = RequestMethod.GET)
	public String listarLaboratorios(ModelMap model) {

		List<Laboratorio> laboratorios = laboratorioService.buscarTodosLosLaboratorios();
		model.addAttribute("laboratorios", laboratorios);
		return "todosloslaboratorios";
	}

	/**
	 * Este es un método GET para nuevos equipos (Obtiene el formulario)
	 */
	@RequestMapping(value = { "/admin/nuevo" }, method = RequestMethod.GET)
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
	 * @param resultado
	 *            Resultado de la validación del empleado (Válido o no válido
	 *            con explicación)
	 * @param model
	 * @return JSP
	 */
	@RequestMapping(value = { "/admin/nuevo" }, method = RequestMethod.POST)
	public String guardarEquipo(@Valid Equipo equipo, BindingResult resultado, ModelMap model) {
		logger.debug("Ha entrado a guardarse el equipo: " + equipo);

		if (resultado.hasErrors()) {
			return "registro";
		}

		if (!equipoService.esIpUnica(equipo.getIp())) {
			FieldError errorDeIp = new FieldError("equipo", "ip",
					messageSource.getMessage("non.unique.ip", new String[] { equipo.getIp() }, Locale.getDefault()));
			resultado.addError(errorDeIp);
			logger.error("Ip no única " + equipo.getIp());
			return "registro";
		}

		logger.debug("Buscando la dirección MAC de " + equipo.getIp());
		Tarea tarea = Ejecucion.obtenerMac(equipo);
		// logger.debug(ordenService == null);
		// ordenService.guardarOrden(crearOrden(tarea));
		logger.debug(tarea.getExecQueue().retornarBuffer());

		if (tarea == null || tarea.getExecQueue() == null || "Error".equals(tarea.getExecQueue().retornarBuffer())
				|| "".equals(tarea.getExecQueue().retornarBuffer())) {
			FieldError errorDeIp = new FieldError("equipo", "ip",
					messageSource.getMessage("ip.not.found", new String[] { equipo.getIp() }, Locale.getDefault()));
			logger.error("IP no encontrada " + equipo.getIp());
			resultado.addError(errorDeIp);
			return "registro";
		}
		String mac = tarea.getExecQueue().retornarBuffer().trim();
		logger.debug("Mac encontrada: " + mac);
		equipo.setMac(mac);

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

		// equipo.setMac(equipo.getMac().replaceAll(",", ""));

		logger.debug("/admin/nuevo: Equipo a guardar: " + equipo);

		equipoService.guardarEquipo(equipo);

		logger.debug("Enviando al equipo " + equipo + " a edición adicional");
		return "redirect:/admin/editar-equipo-" + equipo.getIp();
	}

	/**
	 * Este es un método GET para nuevas salas (Obtiene el formulario)
	 */
	@RequestMapping(value = { "/admin/agregar-sala" }, method = RequestMethod.GET)
	public String nuevaSala(ModelMap model) {
		Sala sala = new Sala();
		model.addAttribute("sala", sala);
		model.addAttribute("edit", false);
		return "registrosala";
	}

	@RequestMapping(value = { "/admin/agregar-sala" }, method = RequestMethod.POST)
	public String guardarSala(@Valid Sala sala, BindingResult resultado, ModelMap model) {
		logger.debug("Ha entrado a guardarse la sala: " + sala);

		if (resultado.hasErrors()) {
			return "registrosala";
		}

		if (!salaService.esIdUnico(sala.getId())) {
			FieldError errorDeId = new FieldError("sala", "id", messageSource.getMessage("non.unique.id",
					new String[] { Long.toString(sala.getId()) }, Locale.getDefault()));
			resultado.addError(errorDeId);
			logger.error("Id no único " + sala.getId());
			return "registrosala";
		}

		logger.debug("/admin/agregar-sala: Sala a guardar: " + sala);

		salaService.guardarSala(sala);

		model.addAttribute("exito", "Sala guardada éxitosamente");
		return "todaslassalas";
	}

	/**
	 * Este es un método GET para nuevas salas (Obtiene el formulario)
	 */
	@RequestMapping(value = { "/admin/agregar-laboratorio" }, method = RequestMethod.GET)
	public String nuevoLaboratorio(ModelMap model) {
		Laboratorio laboratorio = new Laboratorio();
		model.addAttribute("laboratorio", laboratorio);
		model.addAttribute("edit", false);
		return "registrolaboratorio";
	}

	@RequestMapping(value = { "/admin/agregar-laboratorio" }, method = RequestMethod.POST)
	public String guardarLaboratorio(@Valid Laboratorio laboratorio, BindingResult resultado, ModelMap model) {
		logger.debug("Ha entrado a guardarse el laboratorio: " + laboratorio);

		if (resultado.hasErrors()) {
			return "registrolaboratorio";
		}

		if (!laboratorioService.esIdUnico(laboratorio.getId())) {
			FieldError errorDeId = new FieldError("sala", "id", messageSource.getMessage("non.unique.id",
					new String[] { Long.toString(laboratorio.getId()) }, Locale.getDefault()));
			resultado.addError(errorDeId);
			logger.error("Id no único " + laboratorio.getId());
			return "registrosala";
		}

		logger.debug("/admin/agregar-sala: Laboratorio a guardar: " + laboratorio);

		laboratorioService.guardarLaboratorio(laboratorio);

		model.addAttribute("exito", "Sala guardada éxitosamente");
		return "todosloslaboratorios";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	/**
	 * Actualiza la información de un equipo
	 * 
	 * @param ip
	 *            Identificador del equipo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/editar-equipo-{ip}" }, method = RequestMethod.GET)
	public String editarEquipo(@PathVariable String ip, ModelMap model) {
		Equipo equipo = equipoService.buscarEquipoPorIp(ip);
		model.addAttribute("equipo", equipo);
		model.addAttribute("edit", true);
		return "edicion";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/admin/editar-equipo-{ip}" }, method = RequestMethod.POST)
	public String actualizarEquipo(@Valid Equipo equipo, BindingResult result, ModelMap model,
			@PathVariable String ip) {

		if (result.hasErrors()) {
			return "edicion";
		}

		equipoService.actualizarEquipo(equipo);

		model.addAttribute("exito", equipo + " actualizado exitosamente");
		return "exito";
	}

	/**
	 * Elimina un equipo por su ip
	 */
	@RequestMapping(value = { "/admin/eliminar-equipo-{ip}" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ip) {
		logger.debug("Equipo :" + ip + " eliminado");
		equipoService.eliminarEquipoPorIp(ip);
		return "redirect:/equipos";
	}
	
	/**
	 * Elimina una sala por su id
	 */
	@RequestMapping(value = { "/admin/eliminar-sala-{id}" }, method = RequestMethod.GET)
	public String eliminarSala(@PathVariable Integer id) {
		salaService.eliminarSalaPorId(id);
		logger.debug("Equipo :" + id + " eliminado");
		return "redirect:/equipos";
	}

	/**
	 * Actualiza la información de un empleado
	 * 
	 * @param ip
	 *            Identificador del empleado
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/enviar-orden-{ip}" }, method = RequestMethod.GET)
	public String enviarOrden(@PathVariable String ip, ModelMap model) {
		Equipo equipo = equipoService.buscarEquipoPorIp(ip);
		model.addAttribute("equipo", equipo);
		model.addAttribute("edit", true);
		return "centroordenes";
	}

	/**
	 * Apaga un equipo
	 * 
	 * @param ip
	 *            Ip del equipo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/apagar-{ip}" }, method = RequestMethod.GET)
	public String apagar(@PathVariable String ip, ModelMap model) {
		Equipo equipo = equipoService.buscarEquipoPorIp(ip);
		Ejecucion.apagar(equipo);
		model.addAttribute("equipo", equipo);
		model.addAttribute("edit", true);
		return "centroordenes";
	}

	/**
	 * Reinicia un equipo
	 * 
	 * @param ip
	 *            Ip del equipo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/reiniciar-{ip}" }, method = RequestMethod.GET)
	public String reiniciar(@PathVariable String ip, ModelMap model) {
		Equipo equipo = equipoService.buscarEquipoPorIp(ip);
		Ejecucion.reiniciar(equipo);
		model.addAttribute("equipo", equipo);
		model.addAttribute("edit", true);
		return "centroordenes";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", obtenerUsuario());
		return "accessDenied";
	}

	/**
	 * 
	 */
	@ModelAttribute("salas")
	public List<Sala> inicializarSalas() {
		List<Sala> salas = salaService.buscarTodasLasSalas();
		logger.debug("Se han buscado todas las salas(" + salas.size() + "), enviando a \"salas\"");
		return salas;
	}

	/**
	 * 
	 */
	@ModelAttribute("laboratorios")
	public List<Laboratorio> inicializarLaboratorios() {
		logger.debug("Se han buscado todos los laboratorios, enviando a \"laboratorios\"");
		return laboratorioService.buscarTodosLosLaboratorios();
	}

	private String obtenerUsuario() {
		String nombreDeUsuario = null;
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return null;
		}
		Object actual = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (actual instanceof UserDetails) {
			nombreDeUsuario = ((UserDetails) actual).getUsername();
		} else {
			nombreDeUsuario = null;
		}
		return nombreDeUsuario;
	}

	private Orden crearOrden(Tarea tarea) {
		Orden orden = new Orden();
		orden.setCodigoSalida(tarea.getCodigoDeSalida());
		orden.setPkEquipo(tarea.getEquipo());
		orden.setPkFecha(tarea.getFechaInicio());
		ExecBuffer buffer = tarea.getExecQueue();
		if (buffer != null) {
			orden.setResultado(buffer.retornarBuffer());
		}
		orden.setSesion(tarea.getSesion());
		orden.setUsuarioWeb(tarea.getUsuarioweb());
		return (orden);
	}

}