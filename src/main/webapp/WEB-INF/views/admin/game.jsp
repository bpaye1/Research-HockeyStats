<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

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
             New Game
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
                        <sf:input path="date" cssClass="input-small" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Time:</label>
                    </td>
                    <td>
                        <sf:input path="time" cssClass="input-small" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Opponent</label>
                    </td>
                    <td>
                        <sf:input path="opponent" cssClass="input-medium" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Home/Away</label>
                    </td>
                    <td>
                        <sf:select path="homeOrAway" cssClass="input-small" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Beverage Duty</label>
                    </td>
                    <td>
                        <sf:select path="beverageDutyPlayer" cssClass="input-medium"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <input type="submit" name="Save" value="Save" class="btn-primary" />
        <input type="reset" name="Cancel" value="Cancel" />
    </sf:form>
</div>
