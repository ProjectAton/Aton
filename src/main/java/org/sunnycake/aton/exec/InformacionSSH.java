package org.sunnycake.aton.exec;

import org.sunnycake.aton.dto.Equipo;

import com.jcraft.jsch.UserInfo;

/**
 * Información para realizar la conexión SSH
 * @author camilo
 */
public class InformacionSSH implements UserInfo {

    private final Equipo equipo;

    public InformacionSSH(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String getPassphrase() {
        return null;
    }

    @Override
    public String getPassword() {
        return equipo.getPassword();
    }

    @Override
    public boolean promptPassword(String message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean promptPassphrase(String message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean promptYesNo(String message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void showMessage(String message) {
        // TODO Auto-generated method stub

    }

}
