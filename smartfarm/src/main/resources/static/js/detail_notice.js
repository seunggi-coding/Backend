let commentNotice = {
    init: function() {
        $('#delete-btn').on('click', (event) => {
            event.preventDefault();
            this.delete();
        });
    },

    delete: function() {
        const isConfirmed = confirm('정말로 이 게시글을 삭제하시겠습니까?');
        if (!isConfirmed) {
            alert('게시글 삭제가 취소되었습니다.');
            return;
        }

        const noticeId = $('#notice-id').val();

        $.ajax({
            type: "DELETE",
            url: `/notice/${noticeId}/delete`,
            contentType: "application/json; charset=utf-8",
            success: function(response) {
                console.log('Success:', response);
                alert('게시글이 삭제되었습니다.');
                location.href = '/notice';
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('게시글 삭제 중 오류가 발생했습니다.');
            }
        });
    }
};

commentNotice.init();
