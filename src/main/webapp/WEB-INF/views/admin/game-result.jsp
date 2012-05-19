<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<script type="text/javascript" src="<c:url value="/resources/js/game-result.js/" />"></script>

<c:url value="/resources/stylesheets/stylesheet.css" />
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
                    <fieldset>
                        <div id="gameStats" class="well">
                            <table>
                                <tr>
                                    <td>
                                        <label>Team Score:</label>
                                    </td>
                                    <td>
                                        <sf:input path="teamScore" cssClass="input-x-small numeric" maxlength="3" size="2"/>
                                    </td>
                                    <td>
                                        <label>Opponent Team Score</label>
                                    </td>
                                    <td>
                                        <sf:input path="opponentTeamScore" cssClass="input-x-small numeric" maxlength="3" size="2"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="playerGameStats">
                            <table class="table table-striped table-bordered table-condensed">
                                <thead>
                                <tr class="row">
                                    <th>No</th>
                                    <th>Player</th>
                                    <th>Goals</th>
                                    <th>Assits</th>
                                    <th>Penalty Minutes</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="playerGameStats" items="${game.gameStats}" varStatus="status">
                                    <tr class="row">
                                        <td class="span1">
                                            ${playerGameStats.player.jerseyNumber}
                                        </td>
                                        <td class="span4">
                                            ${playerGameStats.player.fullName}
                                        </td>
                                        <td class="span1">
                                            <sf:input path="gameStats[${status.index}].goals" maxlength="2" cssClass="input-x-small numeric goals " />
                                        </td>
                                        <td class="span1">
                                            <sf:input path="gameStats[${status.index}].assists" maxlength="2" cssClass="input-x-small numeric assists" />
                                        </td>
                                        <td class="span1">
                                            <sf:input path="gameStats[${status.index}].penaltyMinutes" maxlength="2" cssClass="input-x-small numeric penaltyMinutes" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr class="row">
                                    <td colspan="2">Totals</td>
                                    <td id="totalGoals"></td>
                                    <td id="totalAssists"></td>
                                    <td id="totalPenaltyMinutes"></td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <div id="goalieGameStats">
                            <table class="table table-striped table-bordered table-condensed">
                                <thead>
                                <tr class="row">
                                    <th>No</th>
                                    <th>Player</th>
                                    <th>Goals Against</th>
                                    <th>Shots on Goal</th>
                                    <th>Save Pct</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="goalieGameStats" items="${game.goalieGameStats}" varStatus="status">
                                    <tr class="row">
                                        <td class="span1">
                                                ${goalieGameStats.player.jerseyNumber}
                                        </td>
                                        <td class="span4">
                                                ${goalieGameStats.player.fullName}
                                        </td>
                                        <td class="span1">
                                            <sf:input path="goalieGameStats[${status.index}].goalsAgainst" maxlength="2" cssClass="input-x-small numeric" />
                                        </td>
                                        <td class="span1">
                                            <sf:input path="goalieGameStats[${status.index}].shotsOnGoal" maxlength="2" cssClass="input-x-small numeric" />
                                        </td>
                                        <td class="span1">
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr class="row">
                                    <td colspan="2">Totals</td>
                                    <td id="totalGoalsAgainst"></td>
                                    <td id="totalShotsOnGoal"></td>
                                    <td id="savePercentage"></td>
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
     </div>
</div>
