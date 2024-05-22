
<!-- 날짜 선택 시 캘린더 제공 -->
$(function () {
    var today = new Date();

    $("#startDate").datepicker({

        dateFormat: "yy-mm-dd",
        minDate: today,
        onClose: function (selectedDate) {
            $("#lastDate").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#lastDate").datepicker({
        dateFormat: "yy-mm-dd",
        minDate: today,
        onClose: function (selectedDate) {
            $("#startDate").datepicker("option", "maxDate", selectedDate);
        }
    });
});