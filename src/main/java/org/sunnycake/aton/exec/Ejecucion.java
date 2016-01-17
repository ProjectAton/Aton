package org.sunnycake.aton.exec;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.service.OrdenService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class Ejecucion {

	@Autowired
	private OrdenService ordenService;

	private static ConcurrentLinkedQueue<Orden> pilaDeOrdenes = new ConcurrentLinkedQueue<>();

	private static Logger logger = LogManager.getLogger(Ejecucion.class);

	public static Tarea ejecutarComando(Equipo equipo, String comando) {
		Tarea tarea = new Tarea(equipo, comando);
		ejecutar(tarea);
		return tarea;
	}

	private static void ejecutar(Tarea tarea) {
		try {
			tarea.connect().ejecutar();
		} catch (com.jcraft.jsch.JSchException e) {
			if (e.getCause() instanceof java.net.NoRouteToHostException) {
				logger.error("No se puede conectar al host: " + tarea.getEquipo().getIp());
				tarea = null;
			}
			logger.error("Error ejecutando la tarea equipo(" + tarea.getEquipo() + "),com=(" + tarea.getComando() + ")",
					e);
		} catch (Exception e) {
			logger.error("Error ejecutando la tarea equipo(" + tarea.getEquipo() + "),com=(" + tarea.getComando() + ")",
					e);
		}
	}

	public static Tarea ejecutarComando(Equipo equipo, String comando, boolean sudo) {
		Tarea tarea = new Tarea(equipo, comando, sudo);
		try {
			tarea.connect().ejecutar();
		} catch (Exception e) {
			logger.error("Error ejecutando la tarea equipo(" + equipo + "),com=(" + comando + "),sudo(" + sudo + ")",
					e);
		}
		return tarea;
	}

	public static Tarea ejecutarComando(Equipo equipo, String comando, boolean sudo, boolean interrumpir) {
		Tarea tarea = new Tarea(equipo, comando, sudo, interrumpir);
		try {
			tarea.connect().ejecutar();
		} catch (Exception e) {
			logger.error("Error ejecutando la tarea equipo(" + equipo + "),com=(" + comando + "),sudo(" + sudo + ")",
					e);
		}
		return tarea;
	}

	public static Tarea obtenerMac(Equipo equipo) {
		return ejecutarComando(equipo, Function.ALT_MAC_ORDER);

	}

	public static Tarea apagar(Equipo equipo) {
		return ejecutarComando(equipo, Function.SHUTDOWN_ORDER, true, true);
	}

	public static Tarea reiniciar(Equipo equipo) {
		return ejecutarComando(equipo, Function.REBOOT_ORDER, true, true);
	}

	public static boolean ping(Equipo equipo) {
		InetAddress ping;
		String ip = equipo.getIp(); // Ip de la máquina remota
		try {
			ping = InetAddress.getByName(ip);
			if (ping.isReachable(5000)) {
				logger.debug(equipo + " responde a ping");
				return true;
			} else {
				logger.debug(equipo + " no responde a ping");
				return false;
			}
		} catch (IOException ex) {
			logger.error("No se pudo hacer ping", ex);
			return false;
		}
	}

	private synchronized void guardar(Orden orden) {
		ordenService.guardarOrden(orden);
	}
}
