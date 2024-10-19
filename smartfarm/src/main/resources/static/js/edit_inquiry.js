let inquiryForm  = {
    init: function() {
        $('#submit_btn').on('click', (event) => {
            event.preventDefault();
            this.update();
        });
    },

    update: function() {
        const isConfirmed = confirm('정말로 문의사항을 수정하시겠습니까?');
        if (!isConfirmed) {
            alert('수정이 취소되었습니다.');
            return;
        }

        let data = {
            inquiryId: $('#inquiry-id').val(),
            title: $('#inquiry-title').val(),
            content: $('#inquiry-content').val()
        };

        $.ajax({
            type: "PUT", // HTTP 메서드를 PUT으로 지정
            url: "/updateInquiry", // 요청을 보낼 URL
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(response) {
                console.log('Success:', response);
                alert('문의사항 수정이 완료되었습니다.');
                window.location.href = "/inquiry";
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('문의사항 수정 중 오류가 발생했습니다.');
            }
        });
    }
};

inquiryForm.init();
