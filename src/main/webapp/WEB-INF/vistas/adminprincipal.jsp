<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Aton Web</title>
        <link rel="shortcut icon" href="<c:url value='/static/favicon.ico' />" />
        <link rel="stylesheet" type="text/css"
              href="<c:url value='/static/estilos/global.css' />" />
        <link rel="stylesheet" type="text/css"
              href="<c:url value='/static/estilos/flaticon.css' />">
        <meta name="viewport"
              content="width=device-width, initial-scale:1.0, user-scale=0" />
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script src="<c:url value='/static/scripts/general.js' />"></script>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
              integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
              crossorigin="anonymous">
        <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>
        <!--Ventana para mensajes-->
        <script>
            $(document).ready(function () {
                $("#mensaje1").click(function () {
                    var mensaje = prompt("Ingrese el mensaje", "");
                    if (mensaje !== null) {
                        $("#mensaje").val(mensaje);
                        $("#mensaje1clic").click();
                    }
                });
                $(".enviar-mensaje").click(function (event) {
                    var id = event.target.id;
                    var mensaje = prompt("Ingrese el mensaje", "");
                    if (mensaje !== null) {
                        window.location.href = "/aton/admin/enviar-mensaje-" + id + "-" + mensaje;
                    }

                });
            });
        </script>
    </head>


    <body>
        <div class="wrapper">
            <header>
                <div class="logo">
                    <a href="<c:url value='/' />">Aton <span>Web</span></a>
                </div>
                <div class="usuario">
                    <button type="button" class="btn btn-default dropdown-toggle"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${user} <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li><a href="<c:url value='/admin/editar-usuario-${user}'/>"
                               class="flaticon-edit18"> Editar usuario</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="<c:url value='/logout'/>"
                               class="flaticon-power107"> Cerrar sesión</a></li>
                    </ul>
                </div>
            </header>

            <a class="mobile" href="#">MENU</a>

            <div id="container-fluid">
                <aside class="sidebar">
                    <ul id="nav">
                        <li><a href="<c:url value='/' />" class="flaticon-plasma">
                                Inicio </a></li>
                        <li><a href="<c:url value='/sugerencia' />"
                               class="flaticon-science93"> Sugerencias</a></li>
                        <li><a href="<c:url value='/admin/nuevo' />"
                               class="flaticon-robots2"> Agregar equipos</a></li>
                        <li><a href="<c:url value='/admin/salas' />"
                               class="flaticon-robots2"> Lista salas</a></li>
                        <li><a href="<c:url value='/admin/laboratorios' />"
                               class="flaticon-robots2"> Lista laboratorios</a></li>
                        <li><a class="flaticon-sun45"
                               href="<c:url value='/acercade' />"> Acerca de</a></li>
                        <li><a class="selected flaticon-science30"
                               href="<c:url value='/admin' />"> Administración</a></li>
                    </ul>
                </aside>


                <div class="content">

                    <h1>Administración</h1>

                    <div class="panel panel-primary">
                        <div class="panel-heading">Equipos</div>
                        <div class="panel-body">

                            <form:form method="POST" modelAttribute="equipos">
                                <c:choose>
                                    <c:when test="${not empty equipos }">
                                        <div class="row">

                                            <c:forEach items="${equipos.equipos}" var="equipo"
                                                       varStatus="status">
                                                <div class="col-xs-2 panel-equipo">
                                                    <div class="panel panel-success">
                                                        <div class="panel-heading flaticon-robot28">
                                                            <c:choose>
                                                                <c:when test="${not empty equipo.nombre}">${equipo.nombre}</c:when>
                                                                <c:otherwise>[${equipo.ip}]</c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <div class="panel-body interior-equipo">

                                                            <div
                                                                class="btn-group btn-group-justified boton-interior-equipo"
                                                                role="group" aria-label="...">

                                                                <div class="btn-group" role="group">
                                                                    <button type="button"
                                                                            class="btn btn-default dropdown-toggle flaticon-console4"
                                                                            data-toggle="dropdown" aria-haspopup="true"
                                                                            aria-expanded="false">
                                                                        <span class="caret"></span>
                                                                    </button>
                                                                    <ul class="dropdown-menu">
                                                                        <li><p class="dropdown-text-mac">MAC:
                                                                                ${equipo.mac}</p></li>
                                                                        <li><p class="dropdown-text-ip">IP:
                                                                                ${equipo.ip}</p></li>
                                                                        <li role="separator" class="divider"></li>
                                                                        <li><a class="flaticon-console4"
                                                                               href="<c:url value='/admin/enviar-orden-${equipo.ip}' />">
                                                                                Enviar una orden</a></li>
                                                                        <li><a class="flaticon-power107"
                                                                               href="<c:url value='/admin/apagar-${equipo.ip}' />">
                                                                                Apagar</a></li>
                                                                        <li><a class="flaticon-update26"
                                                                               href="<c:url value='/admin/reiniciar-${equipo.ip}' />">
                                                                                Reiniciar</a></li>
                                                                        <li><a
                                                                                href="<c:url value='/admin/actualizar-${equipo.ip}' />"
                                                                                class="flaticon-refresh57"> Actualizar software</a></li>
                                                                        <li role="separator" class="divider"></li>
                                                                        <li><a
                                                                                href="#"
                                                                                class="flaticon-chat51 enviar-mensaje" id='${equipo.ip}'> Enviar
                                                                                mensaje</a></li>
                                                                        <li role="separator" class="divider"></li>
                                                                        <li><a
                                                                                href="<c:url value='/admin/eliminar-equipo-${equipo.ip}' />"
                                                                                class="flaticon-delete96"> Eliminar equipo</a></li>
                                                                        <li><a
                                                                                href="<c:url value='/admin/editar-equipo-${equipo.ip}' />"
                                                                                class="flaticon-edit18"> Editar equipo</a></li>

                                                                    </ul>
                                                                </div>
                                                                <div class="btn-group" role="group">
                                                                    <label class="btn btn-default flaticon-login12"><form:checkbox
                                                                            path="equipos[${status.index}].seleccionado"
                                                                            id="equipos[${status.index}].seleccionado"
                                                                            autocomplete="off" /></label> <input type="hidden"
                                                                                    name="equipos[${status.index}].ip" value="${equipo.ip}" />
                                                                </div>
                                                            </div>


                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>


                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="no-encontrado">
                                            <i class="flaticon-boxes27"></i>
                                            <h1>Aquí no hay equipos</h1>
                                            <p>Parece que no se han encontrado equipos. Si deseas
                                                puedes agregar equipos desde el menú lateral o dando clic en
                                                el siguiente botón:</p>

                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="mensaje" id="mensaje" value="" />
                                <div class="btn-agregar-tabla">
                                    <a href="<c:url value='/admin/nuevo' />" class="btn btn-default"
                                       role="button">Agregar equipos</a>
                                    <div class="btn-group" role="group">
                                        <button type="button"
                                                class="btn btn-default dropdown-toggle flaticon-console4"
                                                data-toggle="dropdown" aria-haspopup="true"
                                                aria-expanded="false">
                                            Enviar órdenes <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li><button type="submit" name="enviar-mensajes"
                                                        id="mensaje1clic"
                                                        style="position: absolute; left: -9999px; width: 1px; height: 1px;"
                                                        tabindex="-1"></button>
                                                <button class="flaticon-console4" id="mensaje1">Enviar
                                                    un mensaje</button></li>
                                            <li><button type="submit" name="apagar"
                                                        class="flaticon-power107">Apagar</button></li>
                                            <li><a class="flaticon-update26"
                                                   href="<c:url value='/admin/reiniciar-${equipo.ip}' />">
                                                    Reiniciar</a></li>
                                            <li><a
                                                    href="<c:url value='/admin/actualizar-${equipo.ip}' />"
                                                    class="flaticon-refresh57"> Actualizar software</a></li>
                                            <li role="separator" class="divider"></li>
                                            <li><a
                                                    href="<c:url value='/admin/enviar-mensaje-${equipo.ip}' />"
                                                    class="flaticon-chat51"> Enviar mensaje</a></li>
                                            <li role="separator" class="divider"></li>
                                            <li><a
                                                    href="<c:url value='/admin/eliminar-equipo-${equipo.ip}' />"
                                                    class="flaticon-delete96"> Eliminar equipo</a></li>
                                            <li><a
                                                    href="<c:url value='/admin/editar-equipo-${equipo.ip}' />"
                                                    class="flaticon-edit18"> Editar equipo</a></li>
                                        </ul>
                                    </div>
                                </div>

                            </form:form>
                        </div>
                    </div>





                    <div>
                        Icons made by <a href="http://www.flaticon.com/authors/simpleicon"
                                         title="SimpleIcon">SimpleIcon</a>, <a
                                         href="http://www.freepik.com" title="Freepik">Freepik</a> from <a
                                         href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a>
                        is licensed by <a
                            href="http://creativecommons.org/licenses/by/3.0/"
                            title="Creative Commons BY 3.0">CC BY 3.0</a>
                    </div>

                </div>

            </div>
        </div>
    </body>
</html>