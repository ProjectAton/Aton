package org.sunnycake.aton.exec;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.logging.log4j.LogManager;
import org.sunnycake.aton.dto.Equipo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejecucion {

	private static Logger logger = LogManager.getLogger(Ejecucion.class);

	public static String ejecutarComando(Equipo equipo, String comando) {
		Exec exec = new Exec(equipo);
		ExecBuffer buffer = new ExecBuffer(30);
		try {
			exec.connect().execute(comando, buffer);
			exec.disconnect();
			return buffer.retornarBuffer();
		} catch (Exception e) {
			return null;
		}
	}

	public static String ejecutarComandoSudo(Equipo equipo, String comando) {
		Exec exec = new Exec(equipo);
		ExecBuffer buffer = new ExecBuffer(30);
		try {
			exec.connect().executeSudo(sudo(comando), buffer);
			exec.disconnect();
			return buffer.retornarBuffer();
		} catch (Exception e) {
			return null;
		}
	}

	public static String obtenerMac(Equipo equipo) {
		return ejecutarComando(equipo, Function.ALT_MAC_ORDER);
	}

	public static String apagar(Equipo equipo) {
		return ejecutarComandoSudo(equipo, Function.SHUTDOWN_ORDER);
	}

	public static String sudo(String comando) {
		return "sudo -S -p '' " + comando;
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
}
