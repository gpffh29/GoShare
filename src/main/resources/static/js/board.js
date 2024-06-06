document.addEventListener('DOMContentLoaded', function() {
    console.log("Page loaded");

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    // 삭제 기능
    const deleteButton = document.getElementById('delete-btn');
    if (deleteButton) {
        console.log("Delete button found");
        deleteButton.addEventListener('click', event => {
            event.preventDefault();
            console.log("Delete button clicked");

            const id = document.getElementById('board-id').value;
            fetch(`/api/boards/${id}`, {
                method: 'DELETE',
                headers: {
                    [csrfHeader]: csrfToken
                }
            })
                .then(() => {
                    alert('삭제가 완료되었습니다.');
                    location.replace('/boards');
                })
                .catch(error => console.error('Error:', error));
        });
    }

    // 수정 기능
    const modifyButton = document.getElementById('modify-btn');
    if (modifyButton) {
        modifyButton.addEventListener('click', event => {
            event.preventDefault();
            console.log("Modify button clicked");

            const params = new URLSearchParams(location.search);
            const id = params.get('id');

            const form = document.getElementById('boardForm');
            const formData = new FormData(form);

            const requestPayload = {
                region: document.getElementById('region').value,
                startDate: document.getElementById('startDate').value,
                lastDate: document.getElementById('lastDate').value,
                price: document.getElementById('price').value,
                content: document.getElementById('content').value
            };

            formData.append("request", new Blob([JSON.stringify(requestPayload)], {
                type: "application/json"
            }));

            const images = document.getElementById('images1').files;
            for (let i = 0; i < images.length; i++) {
                formData.append("images1", images[i]);
            }

            fetch(`/api/boards/${id}`, {
                method: 'PUT',
                headers: {
                    [csrfHeader]: csrfToken
                },
                body: formData
            })
                .then(() => {
                    alert('수정이 완료되었습니다.');
                    location.replace('/boards');
                })
                .catch(error => console.error('Error:', error));
        });
    }

    // 생성 기능
    const createButton = document.getElementById('create-btn');
    if (createButton) {
        console.log("Create button found");
        createButton.addEventListener('click', event => {
            event.preventDefault();
            console.log("Create button clicked");

            const form = document.getElementById('boardForm');
            const formData = new FormData(form);

            const requestPayload = {
                content: document.getElementById('content').value,
                region: document.getElementById('region').value,
                startDate: document.getElementById('startDate').value,
                lastDate: document.getElementById('lastDate').value,
                price: document.getElementById('price').value,
                images: [] // 이미지는 MultipartFile로 전송되기 때문에 비워둡니다.
            };

            formData.append("request", new Blob([JSON.stringify(requestPayload)], {
                type: "application/json"
            }));

            const images = document.getElementById('images').files;
            for (let i = 0; i < images.length; i++) {
                formData.append("images", images[i]);
            }

            fetch("/api/boards", {
                method: 'POST',
                headers: {
                    [csrfHeader]: csrfToken
                },
                body: formData
            })
                .then(() => {
                    alert("등록을 완료했습니다.");
                    location.replace("/boards");
                })
                .catch(error => console.error('Error:', error));
        });
    }

    // 이미지 파일명 업데이트 및 유효성 검사
    document.querySelectorAll('.custom-file-input').forEach(input => {
        input.addEventListener('change', function() {
            const fileName = this.value.split("\\").pop();
            const fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            if (!['jpg', 'jpeg', 'gif', 'png', 'bmp'].includes(fileExt)) {
                alert("이미지 파일만 등록이 가능합니다.");
                return;
            }

            this.nextElementSibling.innerHTML = fileName;
        });
    });
});
