/*변수 선언*/


var id = document.querySelector('#id');

var pw1 = document.querySelector('#pswd1');
var pwMsg = document.querySelector('#alertTxt');
var pwImg1 = document.querySelector('#pswd1_img1');

var pw2 = document.querySelector('#pswd2');
var pwImg2 = document.querySelector('#pswd2_img1');
var pwMsgArea = document.querySelector('.int_pass');

var nickname = document.querySelector('#nickname');

var yy = document.querySelector('#yy');
var mm = document.querySelector('#mm');
var dd = document.querySelector('#dd');

var gender = document.querySelector('#gender');

var phone = document.querySelector('#phone');

var address = document.querySelector('#address');

var spe1 = document.querySelector('#spe1');

var error = document.querySelectorAll('.error_next_box');



/*이벤트 핸들러 연결*/


id.addEventListener("focusout", checkId);
pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
nickname.addEventListener("focusout", checkNickname);
yy.addEventListener("focusout", isBirthCompleted);
mm.addEventListener("focusout", isBirthCompleted);
dd.addEventListener("focusout", isBirthCompleted);
gender.addEventListener("focusout", function() {
    if(gender.value === "성별") {
        error[5].style.display = "block";
    } else {
        error[5].style.display = "none";
    }
})
phone.addEventListener("focusout", checkPhoneNum);
address.addEventListener("focusout", isAddressCorrect);
spe1.addEventListener("focusout", function() {
	if(spe1.value === "필수") {
		error[8].style.display = "block";
	} else {
		error[8].style.display = "none";
	}
})





/*콜백 함수*/


function checkId() {
    var idPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;
    if(id.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.display = "block";
    } else if(!idPattern.test(id.value)) {
        error[0].innerHTML = "'이메일@도메인.com' 형태로 입력하세요.";
        error[0].style.display = "block";
    } else {
        error[0].innerHTML = "유효한 이메일입니다!";
        error[0].style.color = "#08A600";
        error[0].style.display = "block";
    }
}

function checkPw() {
    var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
    if(pwPattern.test(pw1.value) && pw1.value != "") {
    	error[1].style.display = "none";
    	
        pwMsg.innerHTML = "안전";
        pwMsg.style.display = "block";
        pwMsg.style.color = "#03c75a";
        
        pwImg1.src = "images/m_icon_safe.png";
    }
    
    if(pw1.value === "") {
        error[1].innerHTML = "필수 정보입니다.";
        error[1].style.display = "block";
    } else if(!pwPattern.test(pw1.value)) {
        error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        error[1].style.display = "block";
        
        pwMsg.innerHTML = "사용불가";
        pwMsgArea.style.paddingRight = "93px";
        pwMsg.style.display = "block";
        
        pwImg1.src = "images/m_icon_not_use.png";
    } 
}

function comparePw() {
    if(pw2.value === pw1.value && pw2.value != "") {
        pwImg2.src = "images/m_icon_check_enable.png";
        error[2].style.display = "none";
    } else if(pw2.value !== pw1.value) {
        pwImg2.src = "images/m_icon_check_disable.png";
        error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[2].style.display = "block";
    } 

    if(pw2.value === "") {
        error[2].innerHTML = "필수 정보입니다.";
        error[2].style.display = "block";
    }
}

function checkNickname() {
    var nicknamePattern = /[a-zA-Z가-힣]{3,10}/;
    if(nickname.value === "") {
        error[3].innerHTML = "필수 정보입니다.";
        error[3].style.display = "block";
    } else if(!nicknamePattern.test(nickname.value) || nickname.value.indexOf(" ") > -1) {
        error[3].innerHTML = "3~10자 한글 or 3~20자 영문 사용(특수기호, 공백 X).";
        error[3].style.display = "block";
    } else {
    	error[3].innerHTML = "유효한 닉네임입니다!";
    	error[3].style.color = "#08A600";
        error[3].style.display = "block";
    }
}


function isBirthCompleted() {
    var yearPattern = /[0-9]{4}/;

    if(!yearPattern.test(yy.value)) {
        error[4].innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
        error[4].style.display = "block";
    } else {
        isMonthSelected();
    }

    function isMonthSelected() {
        if(mm.value === "월") {
            error[4].innerHTML = "태어난 월을 선택하세요.";
        } else {
            isDateCompleted();
        }
    }

    function isDateCompleted() {
        if(dd.value === "일") {
            error[4].innerHTML = "태어난 날짜를 선택하세요.";
        } else {
            isBirthRight();
        }
    }
}

function isBirthRight() {
    var datePattern = /\d{1,2}/;
    if(!datePattern.test(dd.value) || Number(dd.value)<1 || Number(dd.value)>31) {
        error[4].innerHTML = "생년월일을 다시 확인해주세요.";
    } else {
        checkAge();
    }
}

function checkAge() {
    if(Number(yy.value) < 1920) {
        error[4].innerHTML = "정말이세요?";
        error[4].style.display = "block";
    } else if(Number(yy.value) > 2022) {
        error[4].innerHTML = "미래에서 오셨군요!";
        error[4].style.display = "block";
    } else if(Number(yy.value) > 2005) {
        error[4].innerHTML = "만 14세 미만의 어린이는 보호자의 동의가 필요합니다.";
        error[4].style.display = "block";
    } else {
        error[4].style.display = "none";
    }
}

function checkPhoneNum() {
    var isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;

    if(phone.value === "") {
        error[6].innerHTML = "필수 정보입니다.";
        error[6].style.display = "block";
    } else if(!isPhoneNum.test(phone.value)) {
        error[6].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[6].style.display = "block";
    } else {
        error[6].style.display = "none";
	}
}

function isAddressCorrect() {
    var addressPattern;

    if(address.value === "") { 
    	error[7].innerHTML = "필수 정보입니다.";
        error[7].style.display = "block"; 
    } else if(!addressPattern.test(address.value)) {
    	error[7].innerHTML = "형식에 맞지 않는 주소입니다.";
        error[7].style.display = "block";
    } else {
        error[7].style.display = "none"; 
    }
}
