let index = {
    init: function() {
        $("#btn_save").on("click", (event) => {
            event.preventDefault(); // 폼 전송 방지
            this.edit();
        });
    },

    // 회원정보 수정 기능
    edit: function() {
        let pwd = $("#pwd").val();
        let pwdChk = $("#pwdChk").val();

        let data = {};
        console.log('pwd= ' + pwd);
        console.log('pwdChk= ' + pwdChk);

        if((pwd === pwdChk)) {
            data={
                name: $("#name").val(),
                email: $("#email").val(),
                pwd: pwd
            };
            console.log(data);
        } else { // 비밀번호가 일치하지 않을 경우
            alert("비밀번호와 비밀번호 확인란이 일치하지 않습니다.");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/auth/editProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (resp) {
                alert("회원정보 수정이 완료되었습니다.");
                location.href = "/";
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
                alert("수정에 실패했습니다. 다시 시도해주세요.");
            }
        });
    }

};

index.init();