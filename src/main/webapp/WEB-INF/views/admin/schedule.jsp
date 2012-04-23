<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

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
                    <tr class="row">
                        <th>Date</th>
                        <th>Time</th>
                        <th>Opponent</th>
                        <th>Home/Away</th>
                        <th>Location</th>
                        <th>Beverage Duty</th>
                        <th>Result</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="game" items="${schedule.games}">
                        <s:url var="gameUrl" value="/admin/schedules/schedule/${schedule.id}/game/${game.id}" />
                        <s:url var="gameResultUrl" value="/admin/schedules/schedule/${schedule.id}/game/${game.id}/result" />
                        <tr class="row">
                            <td class="span4">
                                <joda:format value="${game.date}" style="L-" />
                             </td>
                            <td class="span4">
                                <joda:format value="${game.time}" style="-S" />
                            </td>
                            <td class="span2">
                                <a href="${gameUrl}">
                                    ${game.opponent}
                                </a>
                            </td>
                            <td class="span1">
                                ${game.homeOrAway.description}
                            </td>
                            <td class="span8">
                                ${game.location}
                            </td>
                            <td class="span7">
                                ${game.beverageDutyPlayer.fullName}
                            </td>
                            <td span="1">
                                <a href="${gameResultUrl}">
                                    <c:choose>
                                        <c:when test="game.isGameWon()">
                                            W
                                        </c:when>
                                        <c:when test="game.isGameLost()">
                                            L
                                        </c:when>
                                        <c:when test="game.isGameTied()">
                                            T
                                        </c:when>
                                        <c:otherwise>
                                            Not Played
                                        </c:otherwise>
                                    </c:choose>
                                    ${game.description}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
		</div>
	</div>
</div>
