<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="java.net.InetAddress" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Displays Client IP Address</title>
</head>
<body>


<%
	String remoteAddr = "";
	 if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
%>
sdfdsfsdfdf
<span><br>Local Host  : <%= request.getRemoteAddr() %></span>
<span><br>ServerPort  : <%= request.getServerPort() %></span>
<span><br>Path        : <%= request.getRequestURI()%></span>

test tes

<span><br>Local Port      : <%= request.getLocalPort()%></span>
<span><br>Server Port     : <%= request.getServerPort()%></span>
<span><br>Remote port      : <%=request.getRemotePort()%></span>
<span><br>Remote Host        : <%= request.getRemoteHost()%></span>
<span><br>Request URI        : <%= request.getRequestURI()%></span>


<span><br>Request Address        : <%= remoteAddr %></span>

</span>
</body>
</html>