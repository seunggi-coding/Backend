function validateForm() {
    // 1. 각 입력 항목의 값을 가져오기
    var custName = document.getElementById("cust_name").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var custEmail = document.getElementById("cust_email").value.trim();
    var grade = document.getElementById("grade").value.trim();

    // 2. 데이터 유효성 체크
    if (custName === "") {
        alert("고객이름이 입력되지 않았습니다.");
        document.getElementById("cust_name").focus();
        return false;
    }

    // 전화번호 유효성 체크
    if (!isValidPhone(phone)) {
        alert("올바른 전화번호 형식이 아닙니다.");
        document.getElementById("phone").value = "";
        document.getElementById("phone").focus();
        return false;
    }

    // 이메일 유효성 체크
    if (!isValidEmail(custEmail)) {
        alert("올바른 이메일 형식이 아닙니다.");
        document.getElementById("cust_email").value = "";
        document.getElementById("cust_email").focus();
        return false;
    }

    // 고객 등급 유효성 체크
    if (!isValidGrade(grade)) {
        alert("고객 등급은 P, G, S 중 하나여야 합니다.");
        document.getElementById("grade").value = "";
        document.getElementById("grade").focus();
        return false;
    }

    if(confirm("입력한 값을 제대로 확인하셨나요?")) {
        alert("정상적으로 등록되었습니다.");
    } else {
        return false;
    }
}

function isValidPhone(phone) {
    // 전화번호 유효성 체크 로직을 구현 (예시: 10자리 또는 11자리 숫자)
    var phoneRegex = /^\d{3}-\d{4}-\d{4}$/;
    
    // 주어진 문자열이 정규표현식에 맞으면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
    return phoneRegex.test(phone);
}

function isValidEmail(email) {
    // 이메일 유효성 체크 로직을 구현 (예시: 이메일 형식)
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
    // 주어진 문자열이 정규표현식에 맞으면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
    return emailRegex.test(email);
}

function isValidGrade(grade) {
    // 고객 등급 유효성 체크 로직을 구현 (예시: P, G, S 중 하나)
    return ["P", "G", "S"].includes(grade);
}

// 숫자가 10 미만일 경우 앞에 0을 붙이는 함수
function padZero(num) {
    return num < 10 ? '0' + num : num;
}