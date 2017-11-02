<%@page import="bbs.Bbs"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "bbs.BbsDAO" %>
<% request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(userID == null ) {
			PrintWriter script = response.getWriter();
			script.println("<script>");		
			script.println("alert('로그인을 하세요')");
			script.println("location.href = 'loginForm.jsp'");
			script.println("</script>");
		} 
		
		int bbsID = 0;
		if(request.getParameter("bbsID") != null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if(bbsID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");			
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("history.back()");
			script.println("</script>");			
		}
		Bbs bbs = new BbsDAO().getBbs(bbsID);
		if(!userID.equals(bbs.getBbsUserID())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");			
			script.println("alert('권한이 없습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
			
			if(request.getParameter("bbsTitle") == null	|| request.getParameter("bbsContent") == null
					|| request.getParameter("bbsTitle").equals(" ")|| request.getParameter("bbsContent").equals(" ")) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('빈칸이 있습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					BbsDAO bbsDAO = new BbsDAO(); 
					int result = bbsDAO.update(bbsID,request.getParameter("bbsTitle"),request.getParameter("bbsContent"));
					if(result == -1) {
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("alert('글수정에 실패했습니다.')");
						script.println("history.back()");
						script.println("</script>");
					} 
					else {						
						PrintWriter script = response.getWriter();
						script.println("<script>");
						script.println("location.href='bbs.jsp'");				
						script.println("</script>");
					}
				}				
		}
	
		
	%>
	
</body>
</html>