$(document).ready(function () {
    getVIP();
    getCoupon();
});

var isBuyState = true;
var vipCardId;
var payState=0;//0为银行卡支付，1为支付宝支付

function getVIP() {
    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                // 是会员
                $("#member-card").css("visibility", "visible");
                $("#member-card").css("display", "");
                $("#nonmember-card").css("display", "none");

                vipCardId = res.content.id;
                $("#member-id").text(res.content.id);
                $("#member-balance").text("¥" + res.content.balance.toFixed(2));
                $("#member-joinDate").text(res.content.joinDate.substring(0, 10));
            } else {
                // 非会员
                $("#member-card").css("display", "none");
                $("#nonmember-card").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });
    getRequest(
        "/vip/getVIPInfo",
        function (res) {
            if(res.success){
                $("#member-buy-price").text(res.content.price);
            }else {
                alert(res.content);
            }
        },
    function (error) {
        alert(error);
        }
    );

    getRequest(
        '/vipStrategy/get',
        function (res) {
            if (res.success) {
                var description="";
                res.content.forEach(function (strategy) {
                    description+="<div style='padding-left: 5px'> 满<span style='color: #d43f3a'>"+strategy.target+"</span>送<span style='color: #d43f3a'>"+strategy.add+"</span></div>";
                });
                $("#member-buy-description").empty();
                $("#member-valid-description").empty();
                $("#member-description").empty();
                $("#member-buy-description").append("<div>充值优惠：</div>" + description );
                $("#member-valid-description").append("<div>会员有效期：</div><div>永久有效</div>");
                $("#member-description").append(description);
            } else {
                alert(res.content);
            }

        },
        function (error) {
            alert(error);
        });
}

function buyClick() {
    clearForm();
    $('#buyModal').modal('show')
    $("#userMember-amount-group").css("display", "none");
    isBuyState = true;
}

function chargeClick() {
    clearForm();
    $('#buyModal').modal('show');
    $("#userMember-amount-group").css("display", "");
    isBuyState = false;
}

function clearForm() {
    $('#userMember-form input').val("");
    $('#userMember-form .form-group').removeClass("has-error");
    $('#userMember-form p').css("visibility", "hidden");
}

function switchPay(type) {
    payState = type;
    if (type === 0) {
        $("#bank-pay").addClass("active");
        $("#ali-pay").removeClass("active");

        $("#modal-body-bank").css("display", "");
        $("#modal-body-ali").css("display", "none");
    } else {
        $("#bank-pay").removeClass("active");
        $("#ali-pay").addClass("active");

        $("#modal-body-bank").css("display", "none");
        $("#modal-body-ali").css("display", "");
    }
}

function confirmCommit() {
    if (validateForm()) {
        var url='';
        if (isBuyState) {
            if(payState===0){
                url='/vip/add/directly?userId=' + sessionStorage.getItem('id') + '&fare=25';
                if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {

                } else {
                    alert("银行卡号或密码错误");
                    return;
                }
            }

            else
                url='/vip/add?userId=' + sessionStorage.getItem('id') + '&fare=25'
            postRequest(
                url,
                null,
                function (res) {
                    if(payState===1)
                        document.write(res.content);
                    else {
                        $('#buyModal').modal('hide');
                        alert("购买会员卡成功");
                        getVIP();
                    }
                },
                function (error) {
                    alert(error);
                });
        } else {
            amount=parseInt($('#userMember-amount').val());
            url='/vip/charge';
            if(payState===0){
                url+='/directly';
                if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {

                } else {
                    alert("银行卡号或密码错误");
                    return;
                }
            }
            postRequest(
                url,
                {vipId: vipCardId, amount: amount},
                function (res) {
                    if(payState===1)
                        document.write(res.content);
                    else {
                        $('#buyModal').modal('hide');
                        alert("充值成功");
                        getVIP();
                        if($('#chargeInfo').attr("display")!="none"){
                            $('#chargeHistory').empty();
                            getHistoryList();
                        }
                    }
                },
                function (error) {
                    alert(error);
                });
        }
    }
}

function validateForm() {
    var isValidate = true;
    if(payState===0){
        if (!$('#userMember-cardNum').val()) {
            isValidate = false;
            $('#userMember-cardNum').parent('.form-group').addClass('has-error');
            $('#userMember-cardNum-error').css("visibility", "visible");
        }
        if (!$('#userMember-cardPwd').val()) {
            isValidate = false;
            $('#userMember-cardPwd').parent('.form-group').addClass('has-error');
            $('#userMember-cardPwd-error').css("visibility", "visible");
        }
    }
    if (!isBuyState && (!$('#userMember-amount').val() || parseInt($('#userMember-amount').val()) <= 0)) {
        isValidate = false;
        $('#userMember-amount').parent('.form-group').addClass('has-error');
        $('#userMember-amount-error').css("visibility", "visible");
    }
    return isValidate;
}

function getCoupon() {
    getRequest(
        '/coupon/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                var couponList = res.content;
                var couponListContent = '';
                for (let coupon of couponList) {
                    couponListContent += '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
                        '<div class="col-md-8 left">' +
                        '<div class="name">' +
                        coupon.name +
                        '</div>' +
                        '<div class="description">' +
                        coupon.description +
                        '</div>' +
                        '<div class="price">' +
                        '满' + coupon.targetAmount + '送' + coupon.discountAmount +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-4 right">' +
                        '<div>有效日期：</div>' +
                        '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                        '</div></div></div></div>'
                }
                $('#coupon-list').html(couponListContent);

            }
        },
        function (error) {
            alert(error);
        });
}

function formatDate(date) {
    return date.substring(5, 10).replace("-", ".");
}

function chargeCheck() {
    $('#VIPInfo').css("display", "none");
    $('#chargeInfo').css("display", "");
    getHistoryList();
}

function getHistoryList() {
    getRequest(
        '/history/all/' + sessionStorage.getItem('id'),
        function (res) {
            res.content.map(function (item) {
                var historycontent = $('#chargeHistory');
                var singleHistoryMessage = '<div class="line" >';
                if(item.type==='charge'){
                    singleHistoryMessage = singleHistoryMessage + '<div>' + item.time.split(".")[0].replace("T"," ") + '</div>';
                    singleHistoryMessage = singleHistoryMessage + '<div>¥' + item.fare + '</div>';
                }
                singleHistoryMessage += '</div>';
                var historyBlock = $(singleHistoryMessage);
                historycontent.append(historyBlock);

            });
        },
        function (error) {
            alert(error);
        });
}

function checkComplete() {
    $('#VIPInfo').css("display", "");
    $('#chargeInfo').css("display", "none");
    $('#chargeHistory').empty();
}