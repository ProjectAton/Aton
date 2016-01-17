<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Aton Web - Agregar laboratorio</title>
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
					<li><a href="<c:url value='/' />"
						class="selected flaticon-plasma"> Inicio </a></li>
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
					<li><a class="flaticon-science30"
						href="<c:url value='/admin' />"> Administración</a></li>
				</ul>
			</aside>


			<div class="content">

				<div class="row">
					<div class="col-xs-6">
						<div class="panel panel-primary">
							<div class="panel-heading">Agregar laboratorio</div>
							<div class="panel-body">


								<form:form method="POST" modelAttribute="laboratorio"
									role="form" class="form-horizontal">

									<div class="form-group">
										<label for="nombre" class="control-label col-xs-2">Nombre:
										</label>
										<div class="col-xs-10">
											<form:errors path="nombre"
												class="alert alert-danger glyphicon glyphicon-exclamation-sign"
												element="div" />
											<form:input path="nombre" type="text" class="form-control"
												id="nombre" placeholder="Nombre del laboratorio" />
										</div>
									</div>
									<div class="form-group">
										<label for="ubicacion" class="control-label col-xs-2">Ubicacion:</label>
										<div class="col-xs-10">
											<form:errors path="ubicacion"
												class="alert alert-danger glyphicon glyphicon-exclamation-sign"
												element="div" />
											<form:input path="ubicacion" type="text" class="form-control"
												id="ubicacion" placeholder="¿Dónde está el laboratorio?" />
										</div>
									</div>
									<div class="form-group">
										<label for="administracion" class="control-label col-xs-2">Administracion:</label>
										<div class="col-xs-10">
											<form:errors path="administracion"
												class="alert alert-danger glyphicon glyphicon-exclamation-sign"
												element="div" />
											<form:input path="administracion" type="text"
												class="form-control" id="administracion"
												placeholder="¿Quién está a cargo?" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-xs-offset-2 col-xs-10">
											<button type="submit" class="btn btn-primary">Agregar</button>
										</div>
									</div>
								</form:form>

							</div>
						</div>
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