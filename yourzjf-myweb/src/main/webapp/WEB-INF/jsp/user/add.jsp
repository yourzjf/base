<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
</head>
<body>
	<from:form method="POST" modelAttribute="user" enctype="multipart/form-data">
		Username: <from:input path="username" /><from:errors path="username"/><br/>
		Password: <from:input path="password" /><from:errors path="password"/><br/>
		Email:	  <from:input path="email"/><from:errors path="email"/><br/>
		AttachOne:<input type="file" name="attachs" ><br/>
		AttachTwo:<input type="file" name="attachs" ><br/>
		<input type="submit" value="新增用户">
	</from:form>
</body>
</html>