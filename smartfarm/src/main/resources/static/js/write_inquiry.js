let inquiryForm  = {
    init: function() {
        $('#submit_btn').on('click', (event) => {
            event.preventDefault();
            this.save();
        });
    },

    save: function() {
        const isConfirmed = confirm('정말로 문의사항을 작성하시겠습니까?');
        if (!isConfirmed) {
            alert('작성이 취소되었습니다.');
            return;
        }

        let data = {
            title: $('#inquiry-title').val(),
            content: $('#inquiry-content').val()
        };

        $.ajax({
            type: "POST",
            url: "/addInquiry",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(response) {
                console.log('Success:', response);
                alert('문의사항 작성이 완료되었습니다.');
                window.location.href = "/inquiry";
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('문의사항 작성 중 오류가 발생했습니다.');
            }
        });
    }
};

inquiryForm.init();
