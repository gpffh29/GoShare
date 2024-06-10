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
//         // var selectedRegion = $('#regionSelect').val().trim();
//         var selectedStartDate = $('#startDate').val();
//         var selectedLastDate = $('#lastDate').val();
//         console.log("선택 시작 날짜 : ", selectedStartDate);
//         console.log("선택 마감 날짜 : ", selectedLastDate);
//
//         $('.board-item').each(function() {
//             // var itemRegion = $(this).data('region').trim();
//             var itemStartDate = $(this).data('start-date');
//             var itemLastDate = $(this).data('last-date');
//
//             console.log("게시물 시작 날짜:", itemStartDate);
//             console.log("게시물 종료 날짜:", itemLastDate);
//
//             // var isRegionMatch = selectedRegion ? itemRegion === selectedRegion : true;
//             var isStartDateMatch = selectedStartDate ? new Date(itemStartDate) >= new Date(selectedStartDate) : true;
//             var isLastDateMatch = selectedLastDate ? new Date(itemLastDate) <= new Date(selectedLastDate) : true;
//
//             console.log("시작 날짜 일치 여부:", isStartDateMatch);
//             console.log("종료 날짜 일치 여부:", isLastDateMatch);
//
//             if (isStartDateMatch && isLastDateMatch) {
//                 $(this).show();
//                 console.log("search success");
//             } else {
//                 $(this).hide();
//                 console.log("search fail");
//             }
//         });
//     }
// });

// $(document).ready(function() {
//     // 페이지 로드 시 모든 게시물을 표시
//     showAllBoards();
//
//     // 검색 버튼 클릭 시 필터링 수행
//     $('#searchBtn').on('click', function() {
//         console.log("검색 버튼 클릭됨");
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
//         // 요소가 올바르게 선택되었는지 확인
//         var selectedRegion = $('#regionSelect').val();
//         console.log("선택된 지역:", selectedRegion);
//
//         if (selectedRegion === undefined) {
//             console.error("ID가 'regionSelect'인 요소를 찾을 수 없습니다.");
//             return;
//         }
//
//         $('.board-item').each(function() {
//             var itemRegion = $(this).data('region');
//             console.log("게시물 지역:", itemRegion);
//
//             var isRegionMatch = selectedRegion ? itemRegion === selectedRegion : true;
//             console.log("지역 일치 여부:", isRegionMatch);
//
//             if (isRegionMatch) {
//                 $(this).show();
//                 console.log("검색 성공");
//             } else {
//                 $(this).hide();
//                 console.log("검색 실패");
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
        var selectedStartDate = $('#startDate').val();
        var selectedLastDate = $('#lastDate').val();
        console.log("선택된 시작 날짜:", selectedStartDate);
        console.log("선택된 종료 날짜:", selectedLastDate);

        $('.board-item').each(function() {
            var itemStartDate = $(this).data('start-date');
            var itemLastDate = $(this).data('last-date');

            console.log("게시물 시작 날짜:", itemStartDate);
            console.log("게시물 종료 날짜:", itemLastDate);

            var isWithinRange = true;

            if (selectedStartDate && selectedLastDate) {
                //게시물의 시작 날짜와 종료 날짜 사이를 검색해야 출력
                isWithinRange = (new Date(selectedStartDate) >= new Date(itemStartDate)) && (new Date(selectedLastDate) <= new Date(itemLastDate));
            }

            console.log("날짜 범위 일치 여부:", isWithinRange);

            if (isWithinRange) {
                $(this).show();
                console.log("검색 성공");
            } else {
                $(this).hide();
                console.log("검색 실패");
            }
        });
    }
});