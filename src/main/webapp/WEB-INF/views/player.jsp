<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<div class="container" >
	<h3>Add New Player</h3>
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
						<sf:input id="dateOfBirth" path="dateOfBirth" placeholder="MM-DD-YYYY" cssClass="input-medium"/>
						<sf:errors path="dateOfBirth" cssClass="alert-error"  />
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
				<tr>
					<td>
						<label>Position:</label>
					</td>
					<td>
						<sf:select path="position" cssClass="input-medium">
							<sf:options items="${positions}"/>
						</sf:select>
					</td>
				</tr>
				<tr>
					<td>
						<label>Home Town:</label>
					</td>
					<td>
						<sf:input id="homeTown" path="homeTown" cssClass="input-medium" maxlength="25"/>
					</td>
				</tr>
			</table>	
		</fieldset>
		<input type="submit" name="Add" value="Add Player" class="btn-primary" />
		<input type="reset" name="Cancel" value="Cancel" />
	</sf:form>
</div>