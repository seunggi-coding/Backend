let commentInquiry = {
    init: function() {
        // '댓글 작성' 버튼의 ID가 'submit-comment'임에 주의하세요.
        $('#submit-comment').on('click', (event) => {
            event.preventDefault();
            this.save();
        });
        $('#delete-btn').on('click', (event) => {
            event.preventDefault();
            this.delete();
        });
    },

    save: function() {
        const isConfirmed = confirm('정말로 댓글을 작성하시겠습니까?');
        if (!isConfirmed) {
            alert('댓글 작성이 취소되었습니다.');
            return;
        }

        let data = {
            content: $('#comment-box').val(),
            writer: '관리자',
            inquiry: {
                inquiryId: $('#inquiry-id').val()
            }
        };

        $.ajax({
            type: "POST",
            url: "/addInquiryComment",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(response) {
                console.log('Success:', response);
                alert('댓글 작성이 완료되었습니다.');
                window.location.reload();
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('댓글 작성 중 오류가 발생했습니다.');
            }
        });
    },

    delete: function() {
        const isConfirmed = confirm('정말로 이 게시글을 삭제하시겠습니까?');
        if (!isConfirmed) {
            alert('게시글 삭제가 취소되었습니다.');
            return;
        }

        const inquiryId = $('#inquiry-id').val();

        $.ajax({
            type: "DELETE",
            url: `/inquiry/${inquiryId}/delete`,
            contentType: "application/json; charset=utf-8",
            success: function(response) {
                console.log('Success:', response);
                alert('게시글이 삭제되었습니다.');
                location.href = '/inquiry'; // 삭제 후 문의사항 목록 페이지로 리다이렉트
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('게시글 삭제 중 오류가 발생했습니다.');
            }
        });
    }
};

commentInquiry.init();
