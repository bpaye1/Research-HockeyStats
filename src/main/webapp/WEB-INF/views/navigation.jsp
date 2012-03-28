<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
        <div class="container">
        	<s:url value="/" var="home_url" />
          <a class="brand" href="${home_url}">Hanson Brothers Hockey Club - Admin</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                	Team
                	<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
              		<li>
              			<s:url value="/admin/players" var="players_url" />
              			<a href="${players_url}">Players</a>
              		</li>
              		<li>
              			<s:url value="/admin/players/player" var="player_url"/>
                		<a href="${player_url}">Add Player</a>
              		</li>
              	</ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                	Schedule
                	<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
              		<li>
              			<s:url value="/admin/schedules" var="schedules_url" />
              			<a href="${schedules_url}">Schedules</a>
              		</li>
              		<li>
              			<s:url value="/admin/schedules/schedule" var="schedule_url"/>
                		<a href="${schedule_url}">Add Schedule</a>
              		</li>
              	</ul>
              </li>
            </ul>
          </div>
        </div>
      </div>
</div>