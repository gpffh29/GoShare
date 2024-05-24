//삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton){
    deleteButton.addEventListener('click' ,event => {
        let id = document.getElementById('board-id').value;
        fetch(`/api/boards/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/boards');
            });
    });
}

//수정 기능
const modifyButton= document.getElementById('modify-btn');

if (modifyButton){
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/boards/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type" : "application/json",
            },
            body: JSON.stringify({
                region: document.getElementById('region').value,
                startDate: document.getElementById('startDate').value,
                lastDate: document.getElementById('lastDate').value,
                price: document.getElementById('price').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/boards/post/${id}`);
            });
    });
}

<!-- 날짜 선택 시 캘린더 제공 -->
$(function () {
    var today = new Date();

    $("#startDate").datepicker({
        dateFormat: "yyyy-mm-dd",
        minDate: today,
        onClose: function (selectedDate) {
            $("#lastDate").datepicker("option", "minDate", selectedDate);
        }
        // onSelect: function(selectedDate) {
        //     var startDate = new Date(selectedDate);
        //     // 여기서 startDate를 사용하여 필요한 작업을 수행할 수 있습니다.
        // }
    });
    $("#lastDate").datepicker({
        dateFormat: "yyyy-mm-dd",
        minDate: today,
        onClose: function (selectedDate) {
            $("#startDate").datepicker("option", "maxDate", selectedDate);
        }
        // onSelect: function(selectedDate) {
        //     var startDate = new Date(selectedDate);
        //     // 여기서 startDate를 사용하여 필요한 작업을 수행할 수 있습니다.
        // }
    });
});

//생성 기능
const createButton = document.getElementById("create-btn");

if (createButton){
    createButton.addEventListener("click", (event) => {
        fetch("/api/boards", {
            method: "POST",
            headers: {
                "Content-Type" : "application/json",
            },
            body: JSON.stringify({
                region: document.getElementById("region").value,
                startDate: document.getElementById("startDate").value,
                lastDate: document.getElementById("lastDate").value,
                price: document.getElementById("price").value,
                content: document.getElementById("content").value
            })
        })
            .then(() => {
                alert("등록을 완료했습니다.");
                location.replace("/boards");
            });
    });
}