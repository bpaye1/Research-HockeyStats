<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<script type="text/javascript">
    $(function(){
        $("#datePicker").datepicker();
        $("#timePicker").timepicker();
    });
</script>

<div class="container">
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
            <c:choose>
                <c:when test="${game.id !=null}">
                    <joda:format value="${game.date}" style="M-" /> -
                    ${game.opponent}
                </c:when>
                <c:otherwise>
                    New Game
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
    <sf:form method="post" modelAttribute="game">
        <fieldset class="well">
            <table cellspacing="0">
                <tr>
                    <td>
                        <label>Date:</label>
                    </td>
                    <td>
                        <sf:input id="datePicker" path="date" cssClass="input-small" />
                        <sf:errors path="date" cssClass="alert-error" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Time:</label>
                    </td>
                    <td>
                        <sf:input id="timePicker" path="time" cssClass="input-small" />
                        <sf:errors path="time" cssClass="alert-error" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Opponent</label>
                    </td>
                    <td>
                        <sf:input path="opponent" cssClass="input-medium" />
                        <sf:errors path="opponent" cssClass="alert-error" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Home/Away</label>
                    </td>
                    <td>
                        <sf:select path="homeOrAway" cssClass="input-small">
                            <sf:option value="" label="Select" />
                            <sf:options items="${homeOrAway}" itemLabel="description" />
                        </sf:select>
                        <sf:errors path="homeOrAway" cssClass="alert-error" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Location</label>
                    </td>
                    <td>
                        <sf:input path="location" cssClass="input-medium" />
                        <sf:errors path="location" cssClass="alert-error" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Beverage Duty</label>
                    </td>
                    <td>
                        <sf:select path="playerOnBeverageDuty" cssClass="input-medium">
                            <sf:option value="" label="Select" />
                            <sf:options items="${players}" itemValue="id" itemLabel="fullName" />
                        </sf:select>
                        <sf:errors path="playerOnBeverageDuty" cssClass="alert-error" />
                    </td>
                </tr>
            </table>
        </fieldset>
        <div>
            <a href="${scheduleUrl}" class="btn btn-small">Cancel</a>
            <button class="btn btn-primary" name="Save" type="submit">Save</button>
        </div>
    </sf:form>
</div>
