package org.sunnycake.aton.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.exec.ExitStatus;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Tarea implements Runnable {
	private JSch jsch;
	private Channel channel;
	private Session sessionSSH;
	private UsuarioWeb usuarioweb;
	private Equipo equipo;
	private boolean noDetenido;
	private String comando;
	private ExecBuffer execQueue = new ExecBuffer(30);
	private boolean sudo;
	private boolean interrumpir;
	private Thread hilo;
	private int codigoDeSalida;
	private Date fechaInicio;
	private boolean termino;

	private Logger logger = LogManager.getLogger(Tarea.class);

	public Tarea(Equipo equipo, String comando) {
		this.fechaInicio = new Date();
		this.codigoDeSalida = 1;
		this.noDetenido = true;
		this.sudo = false;
		this.interrumpir = false;
		this.equipo = equipo;
		this.jsch = new JSch();
		this.comando = comando;
		this.termino = false;
		logger.debug("Tarea para " + equipo + ", con comando \"" + comando + "\" creada.");
	}

	public Tarea(Equipo equipo, String comando, boolean sudo) {
		this.fechaInicio = new Date();
		this.codigoDeSalida = 1;
		this.noDetenido = true;
		this.equipo = equipo;
		this.interrumpir = false;
		this.sudo = sudo;
		this.jsch = new JSch();
		if (sudo) {
			this.comando = sudo(comando);
		} else {
			this.comando = comando;
		}
		this.termino = false;
		logger.debug("Tarea para " + equipo + ", con comando \"" + comando + "\" (sudo) creada.");
	}

	public Tarea(Equipo equipo, String comando, boolean sudo, boolean interrumpir) {
		this.fechaInicio = new Date();
		this.codigoDeSalida = 1;
		this.noDetenido = true;
		this.equipo = equipo;
		this.sudo = sudo;
		this.interrumpir = interrumpir;
		this.jsch = new JSch();
		if (sudo) {
			this.comando = sudo(comando);
		} else {
			this.comando = comando;
		}

		this.termino = false;
		logger.debug("Tarea para " + equipo + ", con comando \"" + comando + "\" (sudo, interrumpir) creada.");
	}

	public Tarea connect() throws Exception {
		logger.info("Conectando a " + equipo);
		sessionSSH = jsch.getSession(equipo.getUsuario(), equipo.getIp(), 22);
		sessionSSH.setConfig("StrictHostKeyChecking", "no");
		sessionSSH.setPassword(equipo.getPassword());
		sessionSSH.connect();

		channel = sessionSSH.openChannel("exec");

		logger.info("Conectado a " + equipo);
		return this;
	}

	public Tarea stop() {
		this.noDetenido = false;
		logger.info("Ejecución detenida");
		return this;
	}

	public Tarea disconnect() throws Exception {
		codigoDeSalida = channel.getExitStatus();
		logger.info(ExitStatus.getFor(codigoDeSalida).message() + ":" + codigoDeSalida);
		if (channel != null)
			channel.disconnect();
		if (sessionSSH != null)
			sessionSSH.disconnect();
		logger.info("Desconectado de " + equipo);
		return this;
	}

	public void ejecutar() {
		// logger.debug("Creando el hilo " + equipo + "+" + comando);
		// if (hilo == null) {
		// hilo = new Thread(this, equipo + comando);
		// hilo.start();
		// }

		noDetenido = true;
		logger.info("Ejecutando: " + comando);
		InputStream stream;
		OutputStream out = null;
		try {
			stream = channel.getInputStream();
			logger.debug("Canal de entrada creado");
			if (sudo) {
				out = channel.getOutputStream();
				logger.debug("Canal de salida creado");
			}
		} catch (IOException e) {
			logger.error("Error obteniendo un canal de salida hacia " + equipo, e);
			execQueue.push("Error");
			setTermino(true);

			return;
		}
		((ChannelExec) channel).setCommand(comando);
		try {
			channel.connect();
			logger.debug("Conectado");
		} catch (JSchException e) {
			logger.error("Error conectando a " + equipo, e);
			setTermino(true);

			return;
		}

		if (sudo) {
			try {
				logger.debug("Escribiendo el password");
				out.write((equipo.getPassword() + "\n").getBytes());
				out.flush();

			} catch (IOException e) {
				logger.error("Error escribiendo la contraseña en " + equipo, e);
				execQueue.push("Error");
				try {
					disconnect();
				} catch (Exception e1) {
					logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e1);
				}
				setTermino(true);

				return;
			}
		}

		if (interrumpir) {
			try {
				Thread.sleep(1000);
			} catch (Exception ee) {
				logger.error(ee);
			}
			logger.debug("Interrumpiendo la orden");
			try {
				out.write("~.\n".getBytes());
				out.flush();
			} catch (IOException e) {
				logger.error("Error interrumpiendo en " + equipo, e);
				try {
					disconnect();
					setTermino(true);

					return;
				} catch (Exception e1) {
					logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e1);
					execQueue.push("Error");
				}
				setTermino(true);

				return;
			}
			logger.debug("Orden interrumpida");
			logger.info("Terminó la ejecución de " + comando);

			try {
				this.disconnect();
				setTermino(true);

			} catch (Exception e) {
				logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e);
				setTermino(true);

				return;
			}
		} else {

			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			try {
				line = reader.readLine();
				logger.debug("Linea leída");
				while (line != null && noDetenido) {
					String salida = line;
					logger.debug(salida);
					logger.debug("Agregando a buffer");
					execQueue.push(salida);
					logger.debug("Agregado a buffer");
					if (channel.isClosed()) {
						logger.debug("Canal cerrado");
						break;
					}
					logger.debug("Leyendo línea");
					line = reader.readLine();
					logger.debug("Linea leída");
				}
				logger.debug("Se leyó toda la salida");
			} catch (IOException ex) {
				logger.error("Error obteniendo la salida de " + equipo, ex);
				try {
					disconnect();
					setTermino(true);

					return;
				} catch (Exception e1) {
					logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e1);
				}
				setTermino(true);

				return;
			}
			logger.info("Terminó la ejecución de " + comando);

			try {
				this.disconnect();
				setTermino(true);

			} catch (Exception e) {
				logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e);
				setTermino(true);

				return;
			}
		}
	}

	public static String sudo(String comando) {
		return "sudo -S -p '' " + comando;
	}

	@Override
	public void run() {
		noDetenido = true;
		logger.info("Ejecutando: " + comando);
		InputStream stream;
		OutputStream out = null;
		try {
			stream = channel.getInputStream();
			logger.debug("Canal de entrada creado");
			if (sudo) {
				out = channel.getOutputStream();
				logger.debug("Canal de salida creado");
			}
		} catch (IOException e) {
			logger.error("Error obteniendo un canal de salida hacia " + equipo, e);
			execQueue.push("Error");
			setTermino(true);

			return;
		}
		((ChannelExec) channel).setCommand(comando);
		try {
			channel.connect();
			logger.debug("Conectado");
		} catch (JSchException e) {
			logger.error("Error conectando a " + equipo, e);
			setTermino(true);

			return;
		}

		if (sudo) {
			try {
				logger.debug("Escribiendo el password");
				out.write((equipo.getPassword() + "\n").getBytes());
				out.flush();

			} catch (IOException e) {
				logger.error("Error escribiendo la contraseña en " + equipo, e);
				execQueue.push("Error");
				try {
					disconnect();
				} catch (Exception e1) {
					logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e1);
				}
				setTermino(true);

				return;
			}
		}

		if (interrumpir) {
			try {
				Thread.sleep(5000);
			} catch (Exception ee) {
				logger.error(ee);
			}
			logger.debug("Interrumpiendo la orden");
			try {
				out.write("~.\n".getBytes());
				out.flush();
			} catch (IOException e) {
				logger.error("Error interrumpiendo en " + equipo, e);
				try {
					disconnect();
					setTermino(true);

					return;
				} catch (Exception e1) {
					logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e1);
					execQueue.push("Error");
				}
				setTermino(true);

				return;
			}
			logger.debug("Orden interrumpida");
			logger.info("Terminó la ejecución de " + comando);

			try {
				this.disconnect();
				setTermino(true);

			} catch (Exception e) {
				logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e);
				setTermino(true);

				return;
			}
		} else {

			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			try {
				line = reader.readLine();
				logger.debug("Linea leída");
				while (line != null && noDetenido) {
					String salida = line;
					logger.debug(salida);
					logger.debug("Agregando a buffer");
					execQueue.push(salida);
					logger.debug("Agregado a buffer");
					if (channel.isClosed()) {
						logger.debug("Canal cerrado");
						break;
					}
					logger.debug("Leyendo línea");
					line = reader.readLine();
					logger.debug("Linea leída");
				}
				logger.debug("Se leyó toda la salida");
			} catch (IOException ex) {
				logger.error("Error obteniendo la salida de " + equipo, ex);
				try {
					disconnect();
					setTermino(true);

					return;
				} catch (Exception e1) {
					logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e1);
				}
				setTermino(true);

				return;
			}
			logger.info("Terminó la ejecución de " + comando);

			try {
				this.disconnect();
				setTermino(true);

			} catch (Exception e) {
				logger.error("No se pudo desconectar de " + equipo + " \"" + comando + "\"", e);
				setTermino(true);

				return;
			}
		}

	}

	/**
	 * @return el channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @return el equipo
	 */
	public Equipo getEquipo() {
		return equipo;
	}

	/**
	 * @return el detenido
	 */
	public boolean isDetenido() {
		return noDetenido;
	}

	/**
	 * @return el comando
	 */
	public String getComando() {
		return comando;
	}

	/**
	 * @return el execQueue
	 */
	public ExecBuffer getExecQueue() {
		return execQueue;
	}

	/**
	 * @return el sudo
	 */
	public boolean isSudo() {
		return sudo;
	}

	/**
	 * @return el interrumpir
	 */
	public boolean isInterrumpir() {
		return interrumpir;
	}

	/**
	 * @return el hilo
	 */
	public Thread getHilo() {
		return hilo;
	}

	/**
	 * @return el codigoDeSalida
	 */
	public int getCodigoDeSalida() {
		return codigoDeSalida;
	}

	/**
	 * @param fechaInicio
	 *            el/la fechaInicio a ser asignado
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @return el usuarioweb
	 */
	public UsuarioWeb getUsuarioweb() {
		return usuarioweb;
	}

	/**
	 * @return el termino
	 */
	public synchronized boolean isTermino() {
		return termino;
	}

	public synchronized void setTermino(boolean termino) {
		this.termino = termino;
	}

}
