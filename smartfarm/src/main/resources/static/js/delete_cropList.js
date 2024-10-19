let deleteCropList = {
    init: function() {
        console.log("deleteCropList 실행");
        $('.delete-btn').on('click', function(event) {
            const id = $(this).closest('tr').find('td[data-id]').data('id');
            console.log(id);
            if(id) {
                deleteCropList.delete(id);
            } else {
                console.error('ID 값을 찾을 수 없습니다.');
            }
        });
    },

    delete: function(id) {
        const isConfirmed = confirm('정말로 이 게시글을 삭제하시겠습니까?');
        if (!isConfirmed) {
            alert('게시글 삭제가 취소되었습니다.');
            return;
        }

        $.ajax({
            type: "DELETE",
            url: `/cropList/${id}/delete`,
            contentType: "application/json; charset=utf-8",
            success: function(response) {
                console.log('Success:', response);
                alert('게시글이 삭제되었습니다.');
                location.href = '/croplist';
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('게시글 삭제 중 오류가 발생했습니다.');
            }
        });
    }
};

deleteCropList.init();
