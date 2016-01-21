<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </head>


    <body>
        <div class="wrapper">
            <header>
                <div class="logo">
                    <a href="<c:url value='/' />">Aton <span>Web</span></a>
                </div>
                <div class="usuario">
                    <c:choose>
                        <c:when test="${not empty user}">
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
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                No has iniciado sesión <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="<c:url value='/login'/>"
                                       class="flaticon-login12"> Iniciar sesión</a></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </header>

            <a class="mobile" href="#">MENU</a>

            <div id="container">
                <aside class="sidebar">
                    <ul id="nav">
                        <li><a href="<c:url value='/' />"
                               class="selected flaticon-plasma"> Inicio </a></li>
                        <li><a href="<c:url value='/sugerencia' />"
                               class="flaticon-science93"> Sugerencias</a></li>
                        <li><a class="flaticon-sun45"
                               href="<c:url value='/acercade' />"> Acerca de</a></li>
                        <li><a class="flaticon-science30"
                               href="<c:url value='/admin' />"> Administración</a></li>
                    </ul>
                </aside>


                <div class="content">

                    <h1>Laboratorio</h1>

                    <c:choose>
                        <c:when test="${not empty salas}">
                            <c:forEach items="${salas}" var="sala">

                                <div class="panel panel-primary">
                                    <div class="panel-heading">${sala.nombre}</div>
                                    <div class="panel-body">

                                        <c:choose>
                                            <c:when test="${not empty sala.equipos }">
                                                <div class="row">
                                                    <c:forEach items="${sala.equipos}" var="equipo">
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
                                                                                    class="btn btn-default dropdown-toggle flaticon-info28"
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
                                                                            </ul>
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
                                                    <p>Parece que no se han encontrado equipos.</p>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="panel panel-primary">
                                <div class="panel-heading">Vacío</div>
                                <div class="panel-body">
                                    <div id="no-encontrado">
                                        <i class="flaticon-boxes27"></i>
                                        <h1>Aquí no hay salas</h1>
                                        <p>Parece que no se han encontrado salas.</p>
                                        <p> Por favor dile al administrador de este problema.</p>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>







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