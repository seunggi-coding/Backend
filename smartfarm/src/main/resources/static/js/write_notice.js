let noticeForm  = {
    init: function() {
        $('#submit_btn').on('click', (event) => {
            event.preventDefault();
            this.save();
        });
    },

    save: function() {
        const isConfirmed = confirm('정말로 공지사항을 작성하시겠습니까?');
        if (!isConfirmed) {
            alert('작성이 취소되었습니다.');
            return;
        }

        let data = {
            title: $('#notice-title').val(),
            content: $('#notice-content').val()
        };

        $.ajax({
            type: "POST",
            url: "/addNotice",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(response) {
                console.log('Success:', response);
                alert('공지사항 작성이 완료되었습니다.');
                window.location.href = "/notice";
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('공지사항 작성 중 오류가 발생했습니다.');
            }
        });
    }
};

noticeForm.init();
