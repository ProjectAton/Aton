package org.sunnycake.aton.exec;

import org.sunnycake.aton.dto.Equipo;

import com.jcraft.jsch.UserInfo;

public class InformacionSSH implements UserInfo {

	private Equipo equipo;
	
	public InformacionSSH(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getPassphrase() {
		return null;
	}

	public String getPassword() {
		return equipo.getPassword();
	}

	public boolean promptPassword(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean promptPassphrase(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean promptYesNo(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
}
