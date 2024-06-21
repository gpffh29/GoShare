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
//         var selectedStartDate = $('#startDate').val();
//         var selectedLastDate = $('#lastDate').val();
//         var selectedRegion = $('#regionSelect').val();
//         var selectedCarType = $('#carType').val();
//
//
//         $('.board-item').each(function() {
//             var itemStartDate = $(this).data('start-date');
//             var itemLastDate = $(this).data('last-date');
//             var itemRegion = $(this).data('region');
//             var itemCarType = $(this).data('car-type');
//
//             var isWithinRange = true;
//
//             //게시물의 시작 날짜와 종료 날짜 사이를 검색
//             if (selectedStartDate && selectedLastDate) {
//                 isWithinRange = (new Date(selectedStartDate) >= new Date(itemStartDate)) && (new Date(selectedLastDate) <= new Date(itemLastDate));
//             }
//
//             //게시물의 지역과 같은 지역만 검색
//             var isRegionMatch = true;
//             if(selectedRegion){
//                 isRegionMatch = itemRegion === selectedRegion;
//             }
//
//             //게시물의 자동차 타입과 같은 타입만 검색
//             var isCarTypeMatch = true;
//             if (selectedCarType && selectedCarType !== "전체") {
//                 isCarTypeMatch = itemCarType === selectedCarType;
//             }
//
//             console.log("날짜 범위 일치 여부:", isWithinRange);
//             console.log("지역 일치 여부:", isRegionMatch);
//             console.log("차종 일치 여부:", isCarTypeMatch);
//
//             if (isWithinRange && isRegionMatch && isCarTypeMatch) {
//                 $(this).show();
//                 console.log("검색 성공");
//             } else {
//                 $(this).hide();
//                 console.log("검색 실패");
//             }
//         });
//     }
// });