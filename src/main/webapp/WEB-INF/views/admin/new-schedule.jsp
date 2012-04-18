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
                        New Schedule
                    </li>
                </ul>
            </div>

            <div id="content">
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
                    <div>
                        <s:url value="/admin/schedules" var="cancelUrl" />
                        <a href="${cancelUrl}" class="btn btn-small">Cancel</a>
                        <button class="btn btn-primary" name="Save" type="submit">Save</button>
                    </div>
                </sf:form>
            </div>

		</div>
	</div>
</div>
