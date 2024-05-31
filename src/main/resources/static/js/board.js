document.addEventListener('DOMContentLoaded', function() {

    //js 실행 test
    console.log("test test")

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    //삭제 기능
    const deleteButton = document.getElementById('delete-btn');

    if (deleteButton){
        console.log("delete-btn found");
        deleteButton.addEventListener('click' ,event => {
            let id = document.getElementById('board-id').value;
            event.preventDefault();
            console.log("delete-btn clicked")
            fetch(`/api/boards/${id}`, {
                method: 'DELETE',
                headers: {
                    [csrfHeader]: csrfToken // CSRF 토큰 추가
                }
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
            event.preventDefault();
            console.log("modify button clicked");

            fetch(`/api/boards/${id}`, {
                method: 'PUT',
                headers: {
                    "Content-Type" : "application/json",
                    [csrfHeader]: csrfToken // CSRF 토큰 추가
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
                    location.replace(`/boards`);
                });
        });
    }




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

    function bindDomEvent(){
        $(".custom-file-input").on("change", function() {
            var fileName = $(this).val().split("\\").pop();
            var fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
            fileExt = fileExt.toLowerCase();

            if(fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif" && fileExt != "png" && fileExt != "bmp"){
                alert("이미지 파일만 등록이 가능합니다.");
                return;
            }
            $(this).siblings(".custom-file-label").html(fileName);
        });
    }

});