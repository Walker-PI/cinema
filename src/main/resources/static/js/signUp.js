$(document).ready(function () {
    function getSignUpForm() {
        return {
            username: $('#exampleInputName').val(),
            password: $('#exampleInputPassword').val(),
            secondPassword: $('#exampleSecondInputPassword').val()
        };
    }
    function validateSignUpForm(data) {
        var isValidate = true;
        var res='';
        if (!data.username || data.username.length < 4 || data.username.length > 18) {
            isValidate = false;
            res+="用户名长度应在4-18位内\n";
        }
        if (!data.password || data.password.length < 6 || data.password.length > 20) {
            isValidate = false;
            res+="密码长度应在6-20位内\n";
        }
        if (!data.secondPassword) {
            isValidate = false;
            res+="请重复输入密码\n";
        } else if (data.secondPassword != data.password) {
            isValidate = false;
            res+="两次密码不一致\n";
        }
        if(!isValidate){
            alert(res);
        }

        return isValidate;
    }
    function registe(){
        var formData = getSignUpForm();

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

    $(".signup_msg").keypress(function (e) {
        if (e.keyCode == 13){
            if(validateSignUpForm(getSignUpForm())){
                registe();
            }
            else{
                return false;
            }
            return false;
            //因为表单提交事件先于 window.location跳转事件，所以return false阻止默认处理
        }
    });

    $(".signup_ack_btn").click(function () {
        if(validateSignUpForm(getSignUpForm())){
            registe();
        }
        else{
            return false;
        }
        return false;
    });

});

