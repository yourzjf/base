<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表e</title>
</head>
<body>
	Current login user is: ${loginUser.username } <br/>
	<c:forEach items="${users }" var="us">
		<a href="${us.value.username }">${us.value.username }</a> -- ${us.value.password } -- ${us.value.email }<a href = "${us.value.username }/update">修改</a> <a href="${us.value.username }/delete">删除</a> <br/>
	</c:forEach>
	<a href="add">添加</a>
</body>
</html>