<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<div class="container">
	<div class="row">
		<div class="span16">

            <div id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <s:url var="homeUrl" value="/" />
                        <a href="${homeUrl}">
                            Admin Home
                        </a>
                        <span class="divider">/</span>
                    </li>
                    <li class="active">
                        Players
                    </li>
                </ul>
            </div>

            <div id="toolbar" class="btn-toolbar">
                <s:url var="newPlayerUrl" value="/admin/players/player/" />
                <a href="${newPlayerUrl}" class="btn btn-small">Add New Player</a>
            </div>

            <div id="content">
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
                        <s:url var="playerUrl" value="/admin/players/player/${player.id}" />
                        <tr>
                            <td>${player.jerseyNumber}</td>
                            <td>
                                <a href="${playerUrl}">
                                   ${player.fullName}
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
</div>
