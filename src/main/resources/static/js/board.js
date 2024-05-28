document.addEventListener('DOMContentLoaded', function() {

    //js 실행 test
    console.log("test test")
    alert ("test") ;

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


    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    //생성 기능
    const createButton = document.getElementById("create-btn");

    if (createButton) {
        console.log("create button found");  //버튼이 생성되었는지 test
        createButton.addEventListener('click', (event) => {
            event.preventDefault();  //form 제출 막기
            console.log("Create button clicked");  //버튼이 클릭되었는지 test

            fetch("/api/boards", {

                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    [csrfHeader]: csrfToken // CSRF 토큰 추가
                },
                body: JSON.stringify({
                    region: document.getElementById("region").value,
                    startDate: document.getElementById("startDate").value,
                    lastDate: document.getElementById("lastDate").value,
                    price: document.getElementById("price").value,
                    content: document.getElementById("content").value
                }),
            })
                .then(() => {
                    alert("등록을 완료했습니다.");
                    location.replace("/boards");
                });
        });
    }

});