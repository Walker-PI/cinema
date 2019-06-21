$(document).ready(function () {
    $(".login_msg").keypress(function (e) {
        if (e.keyCode == 13){
            login();
        }
    });
    $(".login_ack_btn").click(function () {
        login()
    });
});

function getLoginForm() {
    return {
        username: $('#exampleInputEmail').val(),
        password: $('#exampleInputPassword').val()
    };
}

function validateLoginForm(data) {
    var isValidate = true;
    if (!data.username) {
        isValidate = false;
        // $('#index-name').parent('.input-group').addClass('has-error');
        // $('#index-name-error').css("visibility", "visible");
    }
    if (!data.password) {
        isValidate = false;
        // $('#index-password').parent('.input-group').addClass('has-error');
        // $('#index-password-error').css("visibility", "visible");
    }
    return isValidate;
}

function login(){
    var formData = {
        username: $('#exampleInputEmail').val(),
        password: $('#exampleInputPassword').val()
    };
    if (!validateLoginForm(formData)) {
        return;
    }

    postRequest(
        '/login',
        formData,
        function (res) {
            if (res.success) {
                sessionStorage.setItem('username', formData.username);
                sessionStorage.setItem('id', res.content.id);

                if (res.content.userType == 2) {
                    sessionStorage.setItem('role', 'user');
                    window.location.href = "/user/home";
                } else {
                    sessionStorage.setItem('role', 'admin');
                    window.location.href = "/admin/movie/manage"
                }
            } else {
                alert(res.message);
            }
        },
        function (error) {
            alert(error);
        });
}