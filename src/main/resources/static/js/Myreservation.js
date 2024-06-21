document.addEventListener('DOMContentLoaded', () => {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    // 모든 삭제 버튼을 선택합니다.
    const deleteButtons = document.querySelectorAll('.delete-btn');

    // 각 버튼에 클릭 이벤트 리스너를 추가합니다.
    deleteButtons.forEach(button => {
        button.addEventListener('click', () => {
            const id = button.getAttribute('data-reservation-id');
            fetch(`/reservation/delete/${id}`, {
                method: 'DELETE',
                headers: {
                    [csrfHeader]: csrfToken
                }
            })
                .then(() => {
                    alert("예약이 취소되었습니다.");
                    location.replace('/reservation/status');
                })
                .catch(error => console.error('Error:', error));
        });
    });
});