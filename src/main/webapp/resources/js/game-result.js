function isDigit(value){
    return !isNaN(value) && Math.round(value) % 1 == 0;
}

function sum(elements){
    var stats = 0;
    elements.each(function(){
        stats += Number($(this).val());
    });
    return stats;
}

$(function(){

    $(".numeric").keypress(function(event){
        var value = String.fromCharCode(event.which);
        if(!isDigit(value)){
            event.preventDefault();
        }
    });

    $(".numeric").live("paste", function(event){
        event.preventDefault();
    });

    $("#playerGameStats .goals").keyup(function(event){
        var goals = sum($("#playerGameStats .goals"));
        $("#totalGoals").text(goals);
    });

    $("#playerGameStats .assists").keyup(function(event){
        var assists = sum($("#playerGameStats .assists"));
        $("#totalAssists").text(assists);
    });

    $("#playerGameStats .penaltyMinutes").keyup(function(event){
        var penaltyMinutes = sum($("#playerGameStats .penaltyMinutes"));
        $("#totalPenaltyMinutes").text(penaltyMinutes);
    });

    $("#goalieGameStats .goalsAgainst").keyup(function(event){
       var goalsAgainst = sum($("#goalieGameStats .goalsAgainst"));
        $("#totalGoalsAgainst").text(goalsAgainst);
    });

    $("#goalieGameStats .shotsOnGoal").keyup(function(event){
        var shotsOnGoal = sum($("#goalieGameStats .shotsOnGoal"));
        $("#totalShotsOnGoal").text(shotsOnGoal);
    });

    $("#playerGameStats .goals").trigger("keyup");
    $("#playerGameStats .assists").trigger("keyup");
    $("#playerGameStats .penaltyMinutes").trigger("keyup");
    $("#goalieGameStats .goalsAgainst").trigger("keyup");
    $("#goalieGameStats .shotsOnGoal").trigger("keyup");
});

