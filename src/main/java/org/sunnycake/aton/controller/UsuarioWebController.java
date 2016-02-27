package org.sunnycake.aton.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sunnycake.aton.dao.RolDAO;
import org.sunnycake.aton.dao.impl.RolDAOImpl;
import org.sunnycake.aton.dto.UsuarioWeb;
import org.sunnycake.aton.service.UsuarioWebService;

/**
 *
 * Controlador de Usuarios web
 *
 * @author Julian Arango
 *
 */
@Component
@Path("usuarios")
public class UsuarioWebController {

    @Autowired
    UsuarioWebService usuarioWebService;
    @Autowired
    RolDAO rolDao;
    UsuarioWeb usuarioWeb;

    /**
     * El metodo siguiente logra crear un usuario para esto no se deben validar
     * los datos puesto que la validacion la realiza el servicio como tal al
     * igual que el manejo de excepciones
     *
     * @param usuario nombre de usuario unico
     * @param password contrase�a para la cuenta a crear
     * @param enabled este campo por defecto viene en true, indica a la base de
     * datos si el usuario esta activo o no
     * @return texto en html con el resultado de la operacion
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String crear(@QueryParam("usuario") String usuario, @QueryParam("password") String password, @QueryParam("enabled") boolean enabled) {
        usuarioWeb = new UsuarioWeb(usuario, password, enabled);
        usuarioWebService.guardarUsuarioWeb(usuarioWeb);
        return "Registro exitoso";
    }

    /**
     * Metodo principal, usado para loguear a los usuarios. dado que el password
     * no se almacena cifrado por ahora solo se compara literalmente la entrada
     * y el password de la bd
     *
     * @param usuario Usuario web existente, nombre de usuario
     * @param password Password del usuari
     * @return true si el usuario existe y coincide con el password, false de lo
     * contrario
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@QueryParam("usuario") String usuario, @QueryParam("password") String password) {
        usuarioWeb = usuarioWebService.obtenerUsuarioWebPorUsuario(usuario);
        if (usuarioWeb == null) {
            return "Error de autenticación";
        } else if (usuarioWeb.getPassword().equals(password)) {
            return "";
        }
        return "Error de autenticación";
    }

}
