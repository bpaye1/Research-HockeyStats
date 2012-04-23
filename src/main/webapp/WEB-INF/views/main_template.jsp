<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Hockey Stats Admin <tiles:insertAttribute name="title" /></title>
	<link rel="stylesheet" href="<c:url value="/resources/stylesheets/bootstrap.min.css" />" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<c:url value="/resources/stylesheets/stylesheet.css" />" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<c:url value="/resources/plugins/jquery/css/redmond/jquery-ui-1.8.18.custom.css" />" type="text/css" media="screen, projection">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
    <script src="<c:url value="/resources/plugins/jquery/js/jquery-ui-1.8.18.custom.min.js" />"></script>
    <script src="<c:url value="/resources/plugins/timepicker/jquery.ui.timepicker.js" />"></script>
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
