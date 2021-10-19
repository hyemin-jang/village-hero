/**
 * 변수 선언
 */
var id = document.querySelector('#id');
var categoryErrand = document.querySelector('#category-errand');
var pay = document.querySelector('#pay');

var errLoca = document.querySelector('#errLoca');

var error = document.querySelectorAll('.error_next_box');

/**
 * 이벤트 핸들러 연결
 */
id.addEventListener("focusout", checkId);
categoryErrand.addEventListener("focusout", function() {
   if (categoryErrand.value === "필수선택") {
      error[1].style.display = "block";
   } else {
      error[1].style.display = "none";
   }
})
pay.addEventListener("focusout", checkPay);
errLoca.addEventListener("focusout", checkErrLoca);


/**
 * 콜백 함수
 */
function checkId() {
   var idPattern = /[a-zA-Z가-힣0-9\[~!@#$%^&*\-()_+|<>?:;\]{}]{5,50}/;
   if (id.value === "") {
      error[0].innerHTML = "필수 정보입니다.";
      error[0].style.display = "block";
   } else if (!idPattern.test(id.value)) {
      error[0].innerHTML = "형식에 맞지 않는 제목입니다.";
      error[0].style.display = "block";
   } else {
      error[0].innerHTML = "유효한 제목입니다!";
      error[0].style.color = "#08A600";
      error[0].style.display = "block";
   }
}

function checkPay() {
   var payPattern = /[0-9]{4,7}/;
   if (pay.value === "") {
      error[2].innerHTML = "필수 정보입니다.";
      error[2].style.display = "block";
   } else if (!payPattern.test(pay.value) || pay.value === "원") {
      error[2].innerHTML = "숫자만 사용하세요.";
      error[2].style.display = "block";
   } else {
      error[2].innerHTML = "위 가격이 확실하겠죠?";
      error[2].style.color = "#08A600";
      error[2].style.display = "block";
   }
}

function checkErrLoca() {
   var errLocaPattern = /[a-zA-Z가-힣0-9\[~!@#$%^&*\-()_+|<>?:;\]{}]{5,50}/;
   if (errLoca.value === "") {
      error[3].innerHTML = "필수 정보입니다.";
      error[3].style.display = "block";
   } else if (!errLocaPattern.test(errLoca.value)) {
      error[3].innerHTML = "형식에 맞지 않는 주소입니다"
      error[3].style.display = "block";
   } else {
      error[3].innerHTML = "다시 한번 확인하세요!";
      error[3].style.color = "#08A600";
        error[3].style.display = "block";
   }
}