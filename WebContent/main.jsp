<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content ="width=device-width", initial-scale="1">
<link rel = "stylesheet" href="css/bootstrap.css">
<link rel = "stylesheet" href="css/custom.css">
<title>Insert title here</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = session.getAttribute("userID").toString();
		}	
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">			
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>			
			</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹사이트</a>		
		</div>
		<div class="collapse navbar-collapse" id="#bs-example-navbar-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li  class="active"><a href="bbs.jsp">게시판</a>
			</ul>
			
			<% if(userID == null) {
				
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					ara-expanded="false">접속하기<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="loginForm.jsp">로그인</a>
					<a href="join.jsp">회원가입</a>					
			</ul>			
			<% 	
			} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					ara-expanded="false">회원관리<span class="caret"></span></a>
				<ul class="dropdown-menu">					
					<a href="logoutAction.jsp">로그아웃</a>					
			</ul>			
			<%				  
			}
			%>
					
		</div>
	</nav>	
	<div class="container">
		<div class="jumbotron">
			<div class="container">
			<h1>웹 사이트 소개</h1>
			<p>	이 웹사이트는 부트스트랩으로 만든 JSP웹사이트 입니다.</p>
			<p><a class="btn btn-primary btn-pull" href="#" role="button">자세히 알아보기</a></p>			
			</div>
		</div>	
	</div>	
	<div class="container">
		<div id="myCarousel" class="carousel" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active">
				<li data-target="#myCarousel" data-slide-to="1">
				<li data-target="#myCarousel" data-slide-to="2">
			</ol>
			<div class="carousel-inner">
				<div class="item active">
					<img src="images/1.png">
				</div>
				<div class="item">
					<img src="images/2.png">
				</div>
				<div class="item">
					<img src="images/3.png">
				</div>
				<a class="left carousel-control" href="#myCarousel" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left"></span>
				</a>
				<a class="right carousel-control" href="#myCarousel" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>		
		</div>	
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>