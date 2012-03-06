<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
	<title>Insert title here</title>
	<link rel="stylesheet" href="<c:url value="/resources/bootstrap.min.css" />" type="text/css" media="screen, projection">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="navigation.jsp" />
	<div class="container">
		<br/><br/><br/>
		<div class="row">
			<div class="span16">
				<h4>Players</h4>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th class="span1">Name</th>
							<th class="span1">Position</th>
							<th class="span4">Home Town</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="player" items="${players}">
							<s:url var="playerUrl" value="/players/player/${player.id}" />
							<tr>
								<td>${player.jerseyNumber}</td>
								<td>
									<a href="${playerUrl}">
										${player.firstName} ${player.lastName}								
									</a>
								</td>	
								<td>${player.position}</td>
								<td>${player.homeTown}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>