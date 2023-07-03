let host = 'http://' + window.location.host;

$(document).ready(function () {
    const auth = getToken();
    if(auth === '') {
        $('#login-true').hide();
        $('#login-false').show();
    } else {
        $('#login-true').show();
        $('#login-false').hide();
    }
})

function logout() {
    // 토큰 삭제
    Cookies.remove('Authorization', { path: '/' });
    location.reload();
}

function getToken() {
    let auth = Cookies.get('Authorization');

    if(auth === undefined) {
        return '';
    }

    return auth;
}