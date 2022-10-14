<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>input_data</h1>
	<form:form action='input_pro' modelAttribute="dataBean1" method="post"> 
	data1:<form:input type='text' path='data1' />
		<form:errors path="data1" />
		<br>
	data2:<form:input type='text' path='data2' />
		<form:errors path="data2" />
		<br>
	data3:<form:input type='text' path='data3' />
		<form:errors path="data3" />
		<br>
		<form:button type='submit'>확인</form:button>
	</form:form>
</body>
</html>