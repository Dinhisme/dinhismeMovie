<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dinhisme</title>
<script src="views/js/jquery.min.js"></script>
<link href="views/css/bootstrap.min.css" rel="stylesheet" />
<script src="views/js/bootstrap.min.js"></script>
<!-- my -->
<link rel="stylesheet" href="views/css/myNewCSS.css">
<script src="views/js/myscripts.js"></script>
<!-- link icon -->
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/uicons-solid-rounded/css/uicons-solid-rounded.css'>
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/uicons-bold-straight/css/uicons-bold-straight.css'>
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/uicons-solid-straight/css/uicons-solid-straight.css'>
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/uicons-regular-straight/css/uicons-regular-straight.css'>
<link rel="icon" href="views/images/iconD.png">
</head>

<body>
	<div class="container" id="main">
		<div id="mySidenav" class="sidenav">
			<a href="#" class="closebtn" onclick="closeNav()">&times;</a> <a
				href="#"> <img style="width: 80%; margin: 10px 0;"
				src="views/images/logoDred2.png" alt="">
			</a> <a href="./HomeServlet?page=home">HOME</a> <a
				href="./VideoFavoriteServlet?page=video_favorite">FAVORITE</a>
			<c:if test="${User.admin == true}">
				<a href="./VideoEditorServlet?page=video_editor">VIDEO</a>
				<a href="./UserEditorServlet?page=user_editor">USERS</a>
				<a href="./ReportServlet?page=favorite_editor">REPORTS</a>
			</c:if>
		</div>
		<header class="row">
			<nav class="navbar navbar-inverse menu-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a href="./HomeServlet?page=home"> <img class="navbar-brand"
							style="padding: 6px 15px" src="views/images/logoDred2.png" alt="">
						</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							<li><a href="#" onclick="openNav()">CATEGORY <span
									class="glyphicon glyphicon-chevron-right"></span>
							</a></li>
							<li><a href="./MainServlet?page=contact">CONTACT </a></li>
						</ul>
						<c:if test="${User == null}">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="./SignUpServlet?page=signup"><span
										class="glyphicon glyphicon-user"></span> SIGN UP</a></li>
								<li><a href="./LogInServlet?page=login"><span
										class="glyphicon glyphicon-log-in"></span> LOGIN</a></li>
							</ul>
						</c:if>
						<c:if test="${User != null}">
							<ul class="nav navbar-nav navbar-right">
								<li data-toggle="dropdown"><a href=""><span
										style="font-size: x-larger;" class="glyphicon glyphicon-user"></span>
										${User.id} <span class="caret"></span></a></li>
								<ul class="dropdown-menu">
									<li><a href="./AccountServlet?page=account">Account</a></li>
									<li><a href="./LogOutServlet?page=logout">Log out</a></li>
								</ul>
							</ul>
						</c:if>
					</div>
				</div>
			</nav>
		</header>
		<main id="bg" onclick="closeNav()">
			<jsp:include page="layout/${viewPage}.jsp"></jsp:include>
		</main>
		<footer id="ft" class="row ft">
			<div class="ft-content">
				<div class="text-center ft-content-child">
					<a href="./MainServlet?page=about"> <img
						style="width: 150px; margin: 15px 0;"
						src="views/images/logoDred2.png" alt="">
					</a>
					<div class="row-ic">
						<a href="https://www.facebook.com/Dinhisme/">
							<button type="button" class="btn btn-primary btn-ic">
								<img src="views/icon/glyphicon-facebook.png " alt=" ">
							</button>
						</a>
						<!-- <a href="">
                            <button type="button" class="btn btn-primary btn-ic"><img src="icon/glyphicon-twitter.png "
                                    alt=" "></button>
                        </a> -->
						<a href="https://www.instagram.com/dinhisme/">
							<button type="button" class="btn btn-primary btn-ic">
								<img src="views/icon/glyphicon-instagram.png " alt=" ">
							</button>
						</a> <a href="https://www.linkedin.com/in/dinhisme/">
							<button type="button" class="btn btn-primary btn-ic">
								<img src="views/icon/glyphicon-linkedln.png " alt="">
							</button>
						</a> <a href="https://github.com/Dinhisme/"><button type="button"
								class="btn btn-primary btn-ic">
								<img src="views/icon/glyphicon-github.png " alt=" ">
							</button></a>
					</div>
					<br> <br>
				</div>
			</div>
		</footer>
	</div>
</body>

</html>