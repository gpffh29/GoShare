$(function () {
    var boardStartDate = document.getElementById('boardStartDate').textContent;
    var boardLastDate = document.getElementById('boardLastDate').textContent;

    var inputStartDate = document.getElementById('inputStartDate');
    var inputLastDate = document.getElementById('inputLastDate');

    inputStartDate.setAttribute('min', boardStartDate);
    inputStartDate.setAttribute('max', boardLastDate);

    inputLastDate.setAttribute('min', boardStartDate);
    inputLastDate.setAttribute('max', boardLastDate);

    inputStartDate.addEventListener('change', function (){
        inputLastDate.setAttribute('min', inputStartDate.value);
    });

});