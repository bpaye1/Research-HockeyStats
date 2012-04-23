<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<div class="container">

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
            <li>
                <s:url var="scheduleUrl" value="/admin/schedules/schedule/${game.schedule.id}" />
                <a href="${scheduleUrl}">
                    ${game.schedule.description} - ${game.schedule.league}
                </a>
                <span class="divider">/</span>
            </li>
            <li class="active">
                <joda:format value="${game.date}" style="M-" /> -
                ${game.opponent} Results
            </li>
        </ul>
    </div>

    <div id="content">
        <sf:form modelAttribute="game" method="post">
            <fieldset class="well">
                <div id="gameStats">
                    <table>
                        <tr>
                            <td>
                                <label>Team Score:</label>
                            </td>
                            <td>
                                <sf:input path="teamScore" cssClass="input-mini" maxlength="3" size="2"/>
                            </td>
                            <td>
                                <label>Opponent Team Score</label>
                            </td>
                            <td>
                                <sf:input path="opponentTeamScore" cssClass="input-mini" maxlength="3" size="2"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="playerGameStats">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr class="row">
                            <th>Player</th>
                            <th>Goals</th>
                            <th>Assits</th>
                            <th>Penalty Minutes</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="playerGameStats" items="${game.gameStats}">
                            <tr class="row">
                                <td class="span2">
                                        ${playerGameStats.player.jerseyNumber} - ${playerGameStats.player.fullName}
                                </td>
                                <td class="span1">
                                    <sf:input path="playerGameStats.goals" maxlength="2" size="4" />
                                </td>
                                <td class="span1">
                                    <sf:input path="playerGameStats.assists" maxlength="2" size="4" />
                                </td>
                                <td class="span1">
                                    <sf:input path="playerGameStats.penaltyMinutes" maxlength="2" size="4" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr class="row">
                            <td>Totals</td>
                            <td id="totalGoals"></td>
                            <td id="totalAssists"></td>
                            <td id="totalPenaltyMinutes"></td>
                        </tr>
                        </tfoot>
                    </table>
                </div>

            </fieldset>
            <div>
                <a href="${scheduleUrl}" class="btn btn-small">Cancel</a>
                <button class="btn btn-primary" name="Save" type="submit">Save</button>
            </div>
        </sf:form>
    </div>

</div>