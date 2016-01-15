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
		</header>

		<a class="mobile" href="#">MENU</a>

		<div id="container-fluid">
			<aside class="sidebar">
				<ul id="nav">
					<li><a href="<c:url value='/' />"
						class="selected flaticon-plasma"> Inicio </a></li>
					<li><a href="<c:url value='/sugerencia' />"
						class="flaticon-science93"> Sugerencias</a></li>
					<li><a href="<c:url value='/nuevo' />"
						class="flaticon-robots2"> Agregar equipos</a></li>
					<li><a href="<c:url value='/salas' />"
						class="flaticon-robots2"> Lista salas</a></li>
					<li><a href="<c:url value='/laboratorios' />"
						class="flaticon-robots2"> Lista laboratorios</a></li>
					<li><a class="flaticon-sun45"
						href="<c:url value='/acercade' />"> Acerca de</a></li>
				</ul>
			</aside>


			<div class="content">
				<div class="panel panel-primary">
					<div class="panel-heading">Salas</div>
					<div class="panel-body">

						<c:choose>
							<c:when test="${not empty salas }">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>Nombre</th>
											<th>Laboratorio</th>
											<th>Medios</th>
											<th>Enseres</th>
											<th>Editar</th>
											<th>Eliminar</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${salas}" var="sala">
											<tr>
												<th scope="row">${sala.id}</th>
												<td>${sala.nombre}</td>
												<td>${sala.laboratorio}</td>
												<td>${sala.mediosAudiovisuales}</td>
												<td>${sala.enseres}</td>
												<td><a href="<c:url value='/editar-sala-${sala.id}'/>">Editar</a></td>
												<td><a href="<c:url value='/eliminar-sala-${sala.id}'/>">Eliminar</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<div id="no-encontrado">
									<i class="flaticon-boxes27"></i>
									<h1>Aqu� no hay salas</h1>
									<p>Parece que no se han encontrado salas. Si deseas puedes
										agregar salas desde el siguiente bot�n:</p>
								</div>
							</c:otherwise>
						</c:choose>
						<div class="btn-agregar-tabla">
							<a href="<c:url value='/agregar-sala' />"
								class="btn btn-default " role="button">Agregar sala</a>
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