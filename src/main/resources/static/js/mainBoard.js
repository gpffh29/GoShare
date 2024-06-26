//캘린더 제공
document.addEventListener('DOMContentLoaded', function() {

    document.querySelectorAll('.price').forEach(function(priceElement) {
        const price = parseFloat(priceElement.innerText);
        if (!isNaN(price)) {
            const formattedPrice = price.toLocaleString('ko-KR');
            priceElement.innerText = formattedPrice + '원(하루)';
        }
    });

    // 오늘 날짜를 yyyy-MM-dd 형식으로 반환하는 함수
    function getTodayDate() {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
        var yyyy = today.getFullYear();

        return yyyy + '-' + mm + '-' + dd;
    }

    // 오늘 날짜 이후로만 선택 가능하게 설정
    var startDateInput = document.getElementById('startDate');
    var lastDateInput = document.getElementById('lastDate');
    var today = getTodayDate();

    startDateInput.setAttribute('min', today);
    //기본 세팅 오늘 날짜
    startDateInput.value = today;
    lastDateInput.setAttribute('min', today);
    //기본 세팅 오늘 날짜
    lastDateInput.value = today;

    // 시작 날짜를 선택하면 종료 날짜의 최소 날짜도 변경
    startDateInput.addEventListener('change', function() {
        lastDateInput.setAttribute('min', startDateInput.value);
    });
});