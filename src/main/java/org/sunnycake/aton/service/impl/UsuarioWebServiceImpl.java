/**
 *
 */
package org.sunnycake.aton.service.impl;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunnycake.aton.dao.UsuarioWebDAO;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.exception.ExcepcionConsulta;
import org.sunnycake.aton.service.UsuarioWebService;

/**
 *
 * @author Camilo Sampedro
 *
 */
@Service("usuarioWebService")
// Transactional crea una transacción cuando se inicia cada uno de los métodos
// y la cierra cuando termina el método
@Transactional
public class UsuarioWebServiceImpl implements UsuarioWebService {

    private Logger logger = LogManager.getLogger(UsuarioWebService.class);

    @Autowired
    UsuarioWebDAO dao;

    public void guardarUsuarioWeb(UsuarioWeb usuario) {
        try {
            dao.guardarUsuarioWeb(usuario);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al guardar el usuario: " + usuario, e);
        }
    }

    public void actualizarUsuarioWeb(UsuarioWeb usuarioWeb) {
        UsuarioWeb entidad;
        try {
            entidad = dao.obtenerUsuarioWebPorUsuario(usuarioWeb.getUsuario());
            if (entidad != null) {
                entidad.setRols(usuarioWeb.getRols());
                entidad.setPassword(usuarioWeb.getPassword());
                entidad.setEnabled(usuarioWeb.getEnabled());
            }
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar el usuario: " + usuarioWeb, e);
        }
    }

    public void eliminarUsuarioWebPorUsuario(String usuario) {
        try {
            dao.eliminarUsuarioWebPorUsuario(usuario);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al actualizar el usuario: " + usuario, e);
        }

    }

    public Set<UsuarioWeb> buscarTodosLosUsuariosWeb() {
        try {
            return dao.obtenerTodos();
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al obtener todos los usuarios", e);
            return null;
        }
    }

    public boolean esUsuarioUnico(String usuario) {
        UsuarioWeb usuarioWeb = null;
        try {
            usuarioWeb = dao.obtenerUsuarioWebPorUsuario(usuario);
        } catch (ExcepcionConsulta e) {
            logger.error("Ocurrió un error al buscar el usuario a través del usuario: " + usuario, e);
            e.printStackTrace();
        }
        return (usuarioWeb == null || usuario == null);
    }

    @Override
    public UsuarioWeb obtenerUsuarioWebPorUsuario(String usuario) {
        try {
            return dao.obtenerUsuarioWebPorUsuario(usuario);
        } catch (ExcepcionConsulta e) {
            // TODO Auto-generated catch block
            logger.error("Error buscando al usuario " + usuario, e);
            return null;
        }
    }

    @Override
    public void guardarUsuarioWeb(String usuario, String password, boolean enabled) {
        UsuarioWeb usuarioWeb = new UsuarioWeb(usuario, password, enabled);
        guardarUsuarioWeb(usuarioWeb);
    }

    public UsuarioWebDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioWebDAO dao) {
        this.dao = dao;
    }

    
}
