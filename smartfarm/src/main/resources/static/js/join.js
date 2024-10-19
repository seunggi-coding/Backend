let index = {
    init: function() {
        $("#btn_save").on("click", (event) => {
            event.preventDefault();
            this.save();
        });
        $("#email").on("blur", () => {
            this.checkUserId();
        });
    },

    // 회원가입 기능
    save: function() {
        let pwd = $("#pwd").val();
        let pwdChk = $("#pwdChk").val();
        // 체크박스 상태 확인
        let isAgreeTerms = $("#agree-terms").is(":checked");

        let data = {};
        console.log('pwd= ' + pwd);
        console.log('pwdChk= ' + pwdChk);

        if((pwd === pwdChk) && isAgreeTerms) {
            data={
                name: $("#name").val(),
                email: $("#email").val(),
                pwd: pwd
            };
            console.log(data);
        } else if (!isAgreeTerms) {
            alert("이용 약관에 동의해주세요.");
            return;
        } else {
            alert("비밀번호와 비밀번호 확인란이 일치하지 않습니다.");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (resp) {
                alert("회원가입이 완료되었습니다.");
                location.href = "/auth/login";
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
                alert("회원가입에 실패했습니다. 다시 시도해주세요.");
            }
        });
    },

    // 아이디 중복체크 기능
    checkUserId: function() {
        let userEmail = $("#email").val();
        console.log(userEmail);
        console.log(typeof userEmail);
        if(userEmail !== '') {
            $.ajax({
                type: "GET",
                url: "/auth/checkUserId?userEmail=" + userEmail,
                success: function (resp) {
                    if (resp === true) {
                        alert("사용할 수 있는 이메일입니다.");
                    } else {
                        alert("이미 사용 중인 이메일입니다. 다른 이메일을 입력해주세요.");
                    }
                },
                error: function(xhr, status, error) {
                    console.error(xhr.responseText)
                    alert("이메일 중복 확인 중에 오류가 발생했습니다. 다시 시도해주세요.");
                }
            });
        } else {
            alert("이메일을 입력해주세요.");
        }
    }
};

index.init();


