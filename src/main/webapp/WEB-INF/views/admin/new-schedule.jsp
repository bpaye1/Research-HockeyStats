<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<div class="container">
	<div class="row">
		<div class="span8">
			<h3>Add New Schedule</h3>
			<sf:form method="post" modelAttribute="schedule">
				<fieldset class="well">
					<table cellspacing="0">
						<tr>
							<td>
								<label>Description:</label>
							</td>
							<td>
								<sf:input id="description" path="description"/>
								<sf:errors path="description" cssClass="alert-error" />
							</td>
						</tr>
						<tr>	
							<td>
								<label>League:</label>
							</td>
							<td>
								<sf:input id="league" path="league"/>
								<sf:errors path="league" cssClass="alert-error" />
							</td>
						</tr>
					</table>	
				</fieldset>
				<input type="submit" name="Add" value="Add" class="btn-primary" />
			</sf:form>
		</div>
	</div>
</div>
