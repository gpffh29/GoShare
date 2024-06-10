// $(document).ready(function() {
//     // 페이지 로드 시 모든 게시물을 표시
//     showAllBoards();
//
//     // 검색 버튼 클릭 시 필터링 수행
//     $('#searchBtn').on('click', function() {
//         filterBoards();
//     });
//
//     // 모든 게시물을 표시하는 함수
//     function showAllBoards() {
//         $('.board-item').show();
//     }
//
//     // 게시물을 필터링하는 함수
//     function filterBoards() {
//         var selectedRegion = $('#regionSelect').val();
//         var selectedStartDate = $('#startDate').val();
//         var selectedLastDate = $('#lastDate').val();
//         var selectedCarType = $('#carTypeSelect').val();
//
//         $('.board-item').each(function() {
//             var itemRegion = $(this).data('region');
//             var itemStartDate = $(this).data('start-date');
//             var itemLastDate = $(this).data('last-date');
//             var itemCarType = $(this).data('car-type');
//
//             var isRegionMatch = selectedRegion ? itemRegion === selectedRegion : true;
//             var isStartDateMatch = selectedStartDate ? new Date(itemStartDate) >= new Date(selectedStartDate) : true;
//             var isLastDateMatch = selectedLastDate ? new Date(itemLastDate) <= new Date(selectedLastDate) : true;
//             var isCarTypeMatch = selectedCarType ? itemCarType === selectedCarType : true;
//
//             if (isRegionMatch && isStartDateMatch && isLastDateMatch && isCarTypeMatch) {
//                 $(this).show();
//             } else {
//                 $(this).hide();
//             }
//         });
//     }
// });

$(document).ready(function() {
    // 페이지 로드 시 모든 게시물을 표시
    showAllBoards();

    // 검색 버튼 클릭 시 필터링 수행
    $('#searchBtn').on('click', function() {
        filterBoards();
    });

    // 모든 게시물을 표시하는 함수
    function showAllBoards() {
        $('.board-item').show();
    }

    // 게시물을 필터링하는 함수
    function filterBoards() {
        var selectedRegion = $('#regionSelect').val();
        // var selectedStartDate = $('#startDate').val();
        // var selectedLastDate = $('#lastDate').val();

        $('.board-item').each(function() {
            var itemRegion = $(this).data('region');
            // var itemStartDate = $(this).data('start-date');
            // var itemLastDate = $(this).data('last-date');

            var isRegionMatch = selectedRegion ? itemRegion === selectedRegion : true;
            // var isStartDateMatch = selectedStartDate ? new Date(itemStartDate) >= new Date(selectedStartDate) : true;
            // var isLastDateMatch = selectedLastDate ? new Date(itemLastDate) <= new Date(selectedLastDate) : true;

            if (isRegionMatch) {
                $(this).show();
                console.log("search su");
            } else {
                $(this).hide();
                console.log("search fail");
            }
        });
    }
});
