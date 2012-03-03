<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
	<title>Player Form</title>
	<link rel="stylesheet" href="<c:url value="/resources/bootstrap.min.css" />" type="text/css" media="screen, projection">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="navigation.jsp" />
	<br/><br/><br/>
	<div class="container" style="display: block;clear:both;">
		<header>
			<h3>Add New Player</h3>
		</header>
		<sf:form method="post" modelAttribute="player">
			<fieldset class="well">
			<table cellspacing="0">
				<tr>
					<td>
						<label>First Name:</label>
					</td>
					<td>
						<sf:input id="firstName" path="firstName" cssClass="input-medium" />
						<sf:errors path="firstName" cssClass="alert-error" />
					</td>
				</tr>
				<tr>	
					<td>
						<label>Last Name:</label>
					</td>
					<td>
						<sf:input id="lastName" path="lastName" cssClass="input-medium" />
						<sf:errors path="lastName" cssClass="alert-error" />
					</td>
				</tr>
				<tr>	
					<td>
						<label>Email:</label>
					</td>
					<td>
						<sf:input id="email" path="email" cssClass="input-medium" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Date of Birth:</label>
					</td>
					<td>
						<sf:input id="dateOfBirth" path="dateOfBirth" cssClass="input-medium"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Address:</label>
					</td>
					<td>
						<sf:input id="address" path="address.address" cssClass="input-large"/>
					</td>
				</tr>
				<tr>	
					<td>
						<label>City:</label>
					</td>
					<td>
						<sf:input id="address_city" path="address.city" cssClass="input-medium"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>State:</label>
					</td>
					<td>
						<sf:select id="address_state" path="address.state" cssClass="input-mini"/>
					</td>
				</tr>
				<tr>	
					<td>
						<label>Zip Code:</label>
					</td>
					<td>
						<sf:input id="address_zipCode" path="address.zipCode" cssClass="input-small" maxlength="5"/>
					</td>
				</tr>
				<tr>	
					<td>
						<label>Phone Number:</label>
					</td>
					<td>
						<sf:input id="phoneNumber" path="phoneNumber" cssClass="input-medium" maxlength="12"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Jersey Number:</label>
					</td>
					<td>
						<sf:input id="jerseyNumber" path="jerseyNumber" cssClass="input-mini" maxlength="2"/>
						<sf:errors path="jerseyNumber" cssClass="alert-error" />
					</td>
				</tr>
			</table>	
			</fieldset>
			<input type="submit" name="Add" value="Add Player" class="btn-primary" />
			<input type="reset" name="Cancel" value="Cancel" />
		</sf:form>
	
	</div>
</body>
</html>