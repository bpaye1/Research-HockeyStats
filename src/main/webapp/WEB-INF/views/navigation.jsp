<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
        <div class="container">
        	<s:url value="/" var="home_url" />
          <a class="brand" href="${home_url}">Hanson Brothers Hockey Club</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="">
              	<s:url value="/players" var="players_url" />
                <a href="${players_url}">Players</a>
              </li>
              <li>
              	<s:url value="/players/player" var="player_url"/>
                <a href="${player_url}">Add New Player</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
</div>