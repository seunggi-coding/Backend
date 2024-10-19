function doubleCheck() {
    const confirmDelete = confirm("정말로 탈퇴하시겠습니까?");
    if (confirmDelete) {
        deleteAccount();
    }
}

function deleteAccount() {
    fetch('/member/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            alert("회원 탈퇴가 완료되었습니다.");
            window.location.href = '/'; // 홈 페이지로 이동
        } else {
            alert("회원 탈퇴에 실패했습니다. 다시 시도해주세요.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("회원 탈퇴 중 오류가 발생했습니다.");
    });
}
