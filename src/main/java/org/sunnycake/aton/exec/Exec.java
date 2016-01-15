package org.sunnycake.aton.exec;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sunnycake.aton.dto.Equipo;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Exec {
	private JSch jsch;
	private Channel channel;
	private Session session;
	private Equipo equipo;
	private boolean flag;
	private Logger logger = LogManager.getLogger(Exec.class);

	public Exec(Equipo equipo) {
		this.equipo = equipo;
		this.jsch = new JSch();
	}

	public Exec connect() throws Exception {
		logger.info("Conectando a " + equipo);
		session = jsch.getSession(equipo.getUsuario(), equipo.getIp(), 22);
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword(equipo.getPassword());
		session.connect(1000);

		channel = session.openChannel("exec");

		logger.info("Conectado a " + equipo);
		return this;
	}

	public void execute(String command, ExecBuffer execQueue) throws Exception {
		flag = true;
		logger.info("Ejecutando: " + command);
		InputStream stream = channel.getInputStream();
		((ChannelExec) channel).setCommand(command);
		channel.connect();

		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		while ((line = reader.readLine()) != null && flag) {
			if (channel.isClosed()) {
				break;
			}
			if (!Ejecucion.ping(equipo)) {
				break;
			}
			execQueue.push(line);
		}
		logger.info("execution continues");
	}

	public void executeSudo(String command, ExecBuffer execQueue) throws Exception {
		flag = true;
		logger.info("Ejecutando: " + command);
		InputStream stream = channel.getInputStream();
		OutputStream out = channel.getOutputStream();

		((ChannelExec) channel).setCommand(command);
		channel.connect();
		logger.debug("Escribiendo el password");
		out.write((equipo.getPassword() + "\n").getBytes());
		out.flush();
		try {
			Thread.sleep(1500);
		} catch (Exception ee) {
		}
		logger.debug("Interrumpiendo la orden");
		out.write("~.\n".getBytes());
		out.flush();
		logger.debug("Orden interrumpida");

		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		byte[] buffer = new byte[1024];
		while (true) {
			while (stream.available() > 0) {
				int i = stream.read(buffer, 0, 1024);
				if (i < 0) {
					break;
				}
				execQueue.push(new String(buffer, 0, i));
			}
			System.out.println("done");

			if (channel.isClosed()) {
				System.out.println("exit-status: " + channel.getExitStatus());
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ee) {
			}
		}
		logger.info("execution continues");
	}

	public Exec stop() {
		this.flag = false;
		logger.info("execution stopped");
		return this;
	}

	public Exec disconnect() throws Exception {
		int exitCode = channel.getExitStatus();
		logger.info(ExitStatus.getFor(exitCode).message());
		if (channel != null)
			channel.disconnect();
		if (session != null)
			session.disconnect();
		logger.info("disconnected from " + equipo);
		return this;
	}
}
