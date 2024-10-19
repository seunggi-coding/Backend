$(document).ready(function(){
    var savedId = localStorage.getItem('savedId');
    if(savedId) {
        $('#email').val(savedId);
        $('#remember-check').prop('checked', true);
    } else {
        $('#remember-check').prop('checked', false);
    }

    $('#login-form').submit(function(e) {
        if ($('#remember-check').is(':checked')) {
            localStorage.setItem('savedId', $('#email').val());
        } else {
            localStorage.removeItem('savedId');
        }
    });
});
