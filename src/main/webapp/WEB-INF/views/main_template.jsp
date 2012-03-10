<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Hockey Stats Admin <tiles:insertAttribute name="title" /></title>
	<link rel="stylesheet" href="<c:url value="/resources/bootstrap.min.css" />" type="text/css" media="screen, projection">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
</head>
<body>
	<div style="margin-bottom: 50px">
		<tiles:insertAttribute name="top" />
	</div>
	<div>
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html>
