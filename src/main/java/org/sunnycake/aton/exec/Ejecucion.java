package org.sunnycake.aton.exec;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.sunnycake.aton.dto.Equipo;
import org.sunnycake.aton.dto.Orden;
import org.sunnycake.aton.service.OrdenService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Se encarga de realizar diversas ejecuciones tipo SSH
 *
 * @author camilo
 */
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

    /**
     * Obtener mac del equipo correspondiente.
     *
     * @param equipo Equipo, con información de usuario, contraseña y dirección
     * IP (Para conectar por SSH)
     * @return Tarea con la información de la ejecución
     */
    public static Tarea obtenerMac(Equipo equipo) {
        logger.debug("Intentando obtener mac con orden en español 1");
        Tarea tarea = ejecutarComando(equipo, Function.ES_MAC_ORDER, true);
        if ("Error".equals(tarea.getExecQueue().retornarBuffer())) {
            logger.debug("No se pudo obtener usando la orden en español. Intentando obtener mac con orden en inglés");
            tarea = ejecutarComando(equipo, Function.EN_MAC_ORDER, true);
            if ("Error".equals(tarea.getExecQueue().retornarBuffer())) {
                logger.debug("Reintentando en español con nombre de interfaz enp3s0");
                tarea = ejecutarComando(equipo, Function.ES_MAC_ORDER2, true);
                if ("Error".equals(tarea.getExecQueue().retornarBuffer())) {
                    logger.debug("Reintentando en inglés con nombre de interfaz enp3s0");
                    tarea = ejecutarComando(equipo, Function.EN_MAC_ORDER2, true);
                }
            }
        }
        return tarea;

    }

    public static Tarea apagar(Equipo equipo) {
        return ejecutarComando(equipo, Function.SHUTDOWN_ORDER, true, true);
    }

    public static Tarea reiniciar(Equipo equipo) {
        return ejecutarComando(equipo, Function.REBOOT_ORDER, true, true);
    }

    public static void enviarMensaje(Equipo equipo, String mensaje) {
        logger.debug("Enviando mensaje \"" + mensaje + "\", a " + equipo);
        Set<String> usuarios = obtenerUsuarios(equipo);
        // No hay usuarios conectados
        if (usuarios != null) {
            for (String usuario : usuarios) {
                ejecutarComando(equipo, Function.NOTIFICACION_ORDER(usuario, mensaje), true, true);
            }
        }
    }

    public static Set<String> obtenerUsuarios(Equipo equipo) {
        logger.debug("Buscando los usuarios de " + equipo);
        Tarea tarea = ejecutarComando(equipo, Function.USER_LIST_ORDER);
        String salida = tarea.getExecQueue().retornarBuffer();
        if ("Error".equals(salida)) {
            logger.error("No se pudieron obtener los usuarios");
            return null;
        } else {
            logger.debug("Usuarios obtenidos: " + salida);
            Set<String> usuarios = new HashSet<>();
            usuarios.addAll(Arrays.asList(salida.split("\\r?\\n")));
            return usuarios;
        }
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

    public static Tarea actualizar(Equipo equipo) {
        return ejecutarComando(equipo, Function.UPDATE_ORDER, true, false);
    }
}
