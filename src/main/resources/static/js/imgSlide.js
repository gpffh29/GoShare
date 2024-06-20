// 슬라이드 쇼 기능
document.addEventListener('DOMContentLoaded', function() {
    var slides = document.querySelectorAll('.slides');
    var current = 0;

    function showSlide() {

        for (let i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        current++;
        if (current > slides.length)
            current = 1;
        slides[current - 1].style.display = "block";
        setTimeout(showSlide, 3000);
    }

    showSlide();
})