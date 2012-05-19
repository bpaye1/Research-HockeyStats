function isDigit(value){
    return !isNaN(value) && Math.round(value) % 1 == 0;
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
        var goals = sum("#playerGameStats .goals");
        $("#totalGoals").text(goals);
    });

    $("#playerGameStats .assists").keyup(function(event){
        var assits = sum("#playerGameStats .assists");
        $("#totalAssists").text(assits);
    });

    $("#playerGameStats .penaltyMinutes").keyup(function(event){
        var penaltyMinutes = sum("#playerGameStats .penaltyMinutes");
        $("#totalPenaltyMinutes").text(penaltyMinutes);
    });

    function sum(elementsSelection){
        var stats = 0;
        $(elementsSelection).each(function(){
            stats += Number($(this).val());
        });
        return stats;
    }

});

