<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<div class="container">
	<div class="row">
		<div class="span16">
			<h3>Schedules</h3>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="span1">Description</th>
						<th class="span1">Division</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${schedules}">
						<s:url var="scheduleUrl" value="/admin/schedules/schedule/${schedule.id}" />
						<tr>
							<td>${schedule.description}</td>
							<td>
								<a href="${scheduleUrl}">
									${schedule.description}								
								</a>
							</td>	
							<td>${schedule.league}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
