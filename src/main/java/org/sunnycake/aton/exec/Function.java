package org.sunnycake.aton.exec;

/**
 *
 * Clase con todas las funciones necesarias y usadas por diferentes clases.
 *
 * @author Camilo Sampedro
 * @version 0.1.0
 */
public final class Function {

	public static final String IP_ORDER = "ifconfig eth0 2>/dev/null|awk '/Direc. inet:/ {print $2}'|sed 's/inet://'";
	public static final String ALT_IP_ORDER = "ifconfig eth0 |awk '/inet addr:/ {print $2}'|sed 's/addr://'";
	public static final String MAC_ORDER = "ifconfig eth0 2>/dev/null|awk '/direcciónHW/ {print $5}'";
	public static final String ALT_MAC_ORDER = "ifconfig eth0 2>/dev/null|awk '/HWaddr/ {print $5}'";
	public static final String HOST_ORDER = "cat /etc/hostname";
	public static final String ROOT_VERIFICATION_ORDER = "id -u";
	public static final String USER_IDENTIFIER_ORDER = "whoamai";
	public static final String SHUTDOWN_ORDER = "shutdown -h now ~.";
	public static final String IP_OBTAINING_ORDER = "ifconfig eth0 2>/dev/null|awk '/Direc. inet:/ {print $2}'|sed 's/inet://'";
	public static final String USER_LIST_ORDER = "who | cut -d' ' -f1 | sort | uniq";

	public static final String COMPUTER_WAKEUP_ORDER(int sufijoIPSala, String mac) {
		return "wakeonlan -i 192.168." + sufijoIPSala + ".255 " + mac;
	}

	public static String NOTIFICACION_ORDER(String usuario, String mensaje) {
		return GENERATE_ORDER_FOR_USER(usuario, "zenity --info --title=\"Mensaje desde Aton\" --text=\"" + mensaje + "\"");
	}

	public static String GENERATE_ORDER_FOR_USER(String usuario, String orden) {
		return "sudo -u " + usuario + " DISPLAY=:0.0 " + orden;
	}
}