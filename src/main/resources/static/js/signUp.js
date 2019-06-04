$(document).ready(function () {
    $(".signup_msg").keypress(function (e) {
        if (e.keyCode == 13){
            registe();
            return false;
            //因为表单提交事件先于 window.location跳转事件，所以return false阻止默认处理
        }
    });

    $(".signup_ack_btn").click(function () {
        registe();
        return false;
    });

});

function getSignUpForm() {
    return {
        username: $('#exampleInputEmail').val(),
        password: $('#exampleInputPassword').val(),
        secondPassword: $('#exampleSecondInputPassword').val()
    };
}

function validateSignUpForm(data) {
    var isValidate = true;
    if (!data.username || data.username.length < 4 || data.username.length > 10) {
        isValidate = false;
        alert("用户名长度应在4-10位内");
    }
    if (!data.password || data.password.length < 6 || data.password.length > 12) {
        isValidate = false;
        alert("密码长度应在6-12位内");
        // $('#signUp-password').parent('.input-group').addClass('has-error');
        // $('#signUp-password-error').css("visibility", "visible");
    }

    if (!data.secondPassword) {
        isValidate = false;
        alert("请再次输入密码");
        // $('#signUp-second-password').parent('.input-group').addClass('has-error');
        // $('#signUp-second-password-error').css("visibility", "visible");
        // $('#signUp-second-password-error').text("请再次输入密码");
    } else if (data.secondPassword != data.password) {
        isValidate = false;
        // $('#signUp-second-password').parent('.input-group').addClass('has-error');
        // $('#signUp-second-password-error').css("visibility", "visible");
        // $('#signUp-second-password-error').text("两次输入密码不一致");
        alert("两次密码不一致");
    }

    return isValidate;
}

function registe(){
    var formData = {
        username: $('#exampleInputName').val(),
        password: $('#exampleInputPassword').val(),
        secondPassword: $('#exampleInputPassword').val()
    };
    if (!validateSignUpForm(formData)) {
        return;
    }

    postRequest(
        '/register',
        formData,
        function (res) {
            if (res.success) {
                alert("注册成功");
                window.location.href = "/index";
            } else {
                alert("注册失败 "+res.message);
            }
        },
        function (error) {

            alert('发送失败'+error);
        });
}