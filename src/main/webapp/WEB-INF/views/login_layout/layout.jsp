<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="">
<head>
	<title>Login V18</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
	<tiles:insertAttribute name="headerInclude"/>
</head>
<body style="background-color: #666666;">		
	<tiles:insertAttribute name="body"/>
</body>
</html>