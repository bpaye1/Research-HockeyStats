<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<div class="container">
	<div class="row">
		<div class="span8">

            <div id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <s:url var="homeUrl" value="/" />
                        <a href="${homeUrl}">
                            Admin Home
                        </a>
                        <span class="divider">/</span>
                    </li>
                    <li>
                        <s:url var="schedulesUrl" value="/admin/schedules/" />
                        <a href="${schedulesUrl}">
                            Schedules
                        </a>
                        <span class="divider">/</span>
                    </li>
                    <li class="active">
                        ${schedule.description} - ${schedule.league} Schedule
                    </li>
                </ul>
            </div>

            <div id="toolbar" class="btn-toolbar">
                <s:url var="newGameUrl" value="/admin/schedules/schedule/${schedule.id}/game" />
                <a href="${newGameUrl}" class="btn btn-small">Add New Game</a>
            </div>

            <div id="content">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th class="span1">Date</th>
                        <th class="span1">Time</th>
                        <th class="span1">Opponent</th>
                        <th class="span1">Home/Away</th>
                        <th class="span1">Beverage Duty</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="game" items="${schedule.games}">
                        <s:url var="gameUrl" value="/admin/schedules/schedule/${schedule.id}/games/${game.id}" />
                        <tr>
                            <td>${game.date}</td>
                            <td>${game.time}</td>
                            <td>
                                <a href="${gameUrl}">
                                        ${game.opponent}
                                </a>
                            </td>
                            <td>${game.homeOrAway}</td>
                            <td>${game.beverageDutyPlayer}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

		</div>
	</div>
</div>
