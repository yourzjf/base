<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" type="text/css" />
</head>
<body>
	<!-- 该取值方式对应UserController中的局部异常处理方式 -->
	<%-- ${ex.message } --%>
	<!-- 该取值方式对应springmvc-servlet.xml 中的全局异常处理方式 -->
	${exception.message }
</body>
</html>