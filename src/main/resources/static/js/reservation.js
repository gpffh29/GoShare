$(function () {
    function getTodayDate() {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
        var yyyy = today.getFullYear();

        return yyyy + '-' + mm + '-' + dd;
    }

    var today = getTodayDate();

    var boardStartDate = document.getElementById('boardStartDate').textContent;
    var boardLastDate = document.getElementById('boardLastDate').textContent;

    var inputStartDate = document.getElementById('inputStartDate');
    var inputLastDate = document.getElementById('inputLastDate');

    var minDate;
    if (boardStartDate < today)
        minDate = today;
    else
        minDate = boardStartDate;

    inputStartDate.value=today;
    inputLastDate.value=today;
    inputStartDate.setAttribute('min', minDate);
    inputStartDate.setAttribute('max', boardLastDate);

    inputLastDate.setAttribute('min', minDate);
    inputLastDate.setAttribute('max', boardLastDate);

    inputStartDate.addEventListener('change', function (){
        inputLastDate.setAttribute('min', inputStartDate.value);
    });

});