var order = {ticketId: [], couponId: 0};
var coupons = [];
var isVIP = false;
var useVIP = true;

$(document).ready(function () {
    order.ticketId=window.location.href.split('?')[1].split('=')[1].split(',').map(Number);

    postRequest(
        '/ticket/generateOrder?ticketIds='+order.ticketId,
        {},
        function (res) {
            if(res.success){
                var orderInfo={
                    movieName:res.content.movieName,
                    hallName:res.content.hallName,
                    singlePrice:res.content.singlePrice,
                    scheduleTime:res.content.scheduleTime,
                    ticketVOList:res.content.ticketVOList,
                    total:res.content.total,
                    couponVOList:res.content.couponVOList,
                    generateTime:res.content.generateTime
                };
                renderOrder(orderInfo);
            }
            else {
                alert("获取订单失败");
                window.history.back(-1);
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }

    );

    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            isVIP = res.success;
            useVIP = res.success;
            if (isVIP) {
                $('#member-balance').html("<div><b>会员卡余额：</b>" + res.content.balance.toFixed(2) + "元</div>");
            } else {

                $("#member-pay").css("display", "none");
                $("#nonmember-pay").addClass("active");

                $("#modal-body-member").css("display", "none");
                $("#modal-body-nonmember").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });

});


function switchPay(type) {
    useVIP = (type == 0);
    if (type == 0) {
        $("#member-pay").addClass("active");
        $("#nonmember-pay").removeClass("active");

        $("#modal-body-member").css("display", "");
        $("#modal-body-nonmember").css("display", "none");
    } else {
        $("#member-pay").removeClass("active");
        $("#nonmember-pay").addClass("active");

        $("#modal-body-member").css("display", "none");
        $("#modal-body-nonmember").css("display", "");
    }
}

function renderOrder(orderInfo) {
    $('#order-movie-name').text(orderInfo.movieName);
    $('#order-schedule-hall-name').text(orderInfo.hallName);
    $('#order-schedule-fare').text(orderInfo.singlePrice.toFixed(2));
    $('#order-schedule-time').text(orderInfo.scheduleTime.substring(5, 7) + "月" + orderInfo.scheduleTime.substring(8, 10) + "日 " + orderInfo.scheduleTime.substring(11, 16) + "场");
    var ticketStr = "<div>" + order.ticketId.length + "张</div>";
    for (let ticketInfo of orderInfo.ticketVOList) {
        ticketStr += "<div>" + (ticketInfo.rowIndex + 1) + "排" + (ticketInfo.columnIndex + 1) + "座</div>";
    }
    $('#order-tickets').html(ticketStr);

    var total = orderInfo.total.toFixed(2);
    $('#order-total').text(total);
    $('#order-footer-total').text("总金额： ¥" + total);


    var couponTicketStr = "<option>不使用优惠券</option>";

    if (orderInfo.couponVOList.length > 0) {
        coupons = orderInfo.couponVOList;
        for (let coupon of coupons) {
            couponTicketStr += "<option>满" + coupon.targetAmount + "减" + coupon.discountAmount + "</option>"
        }
    }
    $('#order-coupons').html(couponTicketStr);
    changeCoupon(0);
    setCountDown(orderInfo.generateTime);
}

function changeCoupon(couponIndex) {
    if(couponIndex===0){
        total=parseFloat($('#order-total').text()).toFixed(2);
        $('#order-discount').text("优惠金额：无");
        $('#order-actual-total').text(" ¥" + total);
        $('#pay-amount').html("<div><b>金额：</b>" + total + "元</div>");
    } else {
        couponIndex--;
        order.couponId = coupons[couponIndex].id;
        $('#order-discount').text("优惠金额： ¥" + coupons[couponIndex].discountAmount.toFixed(2));
        var actualTotal = (parseFloat($('#order-total').text()) - parseFloat(coupons[couponIndex].discountAmount)).toFixed(2);
        $('#order-actual-total').text(" ¥" + actualTotal);
        $('#pay-amount').html("<div><b>金额：</b>" + actualTotal + "元</div>");
    }

}

function setCountDown(ticketTime) {
    UpdateTime();
    setInterval(UpdateTime,1000);

    function UpdateTime(){

        var Year=parseInt(ticketTime.substring(0,4));
        var Month=parseInt(ticketTime.substring(5, 7));
        var Day=parseInt(ticketTime.substring(8, 10));
        var hour=parseInt(ticketTime.substring(11,13));
        var min=parseInt(ticketTime.substring(14,16));
        var sec=parseInt(ticketTime.substring(17,19));

        var endDate=new Date(); //创建时间对象
        var newDate=new Date(); //获取现在的时间

        //设置结束时间
        endDate.setFullYear(Year);
        endDate.setMonth(Month-1);
        endDate.setDate(Day);
        endDate.setHours(hour);
        endDate.setMinutes(min);
        endDate.setSeconds(sec);


        //获取时间差毫秒数，用结束时间毫秒数-现在时间毫秒数
        var s=(endDate.getTime()+15*60*1000-newDate.getTime())/1000;  //时间差的秒数
        if(s<=0){
            alert("支付超时");
            window.location.href = '/user/buy';
        }
        var minutes=Math.floor(s/60);
        s=s%60;
        var s4=s;

        $("p.c_Date span").eq(0).html(fullZero(minutes,2)); //分钟
        $("p.c_Date span").eq(1).html(fullZero(s4,2));//秒

    }


//给时间前面补0
    function fullZero(_time,n){
        var str=""+_time;
        while(str.length<n){
            str="0"+str;
        }
        return str;
    }

}

function payConfirmClick() {
    if (useVIP) {
        postPayRequest("/ticket/vip/buy");

    } else {
        postPayRequest("/ticket/buy");
    }
}

// TODO:一会可能要改
function postPayRequest(url) {
    postRequest(
        url+"?ticketId="+order.ticketId+"&couponId="+order.couponId,
        {},
        function (res) {
            if(res.success){
                if(!useVIP){
                    document.write(res.content);

                }
                else{
                    $('#order-state').css("display", "none");
                    $('#success-state').css("display", "");
                    $('#buyModal').modal('hide');
                }
            }
            else {
                alert("支付失败");
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}


function cancelPayClick(){
    postRequest(
        "/ticket/cancel?ticketId="+order.ticketId,
        {},
        function(res) {
            if(res.success){
                movieId=res.content.movieId;
                scheduleId=res.content.scheduleId;
                window.location.href = '/user/movieDetail/buy?id='+movieId+'&scheduleId='+scheduleId;
            }
            else {
                alert("操作失败");
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    )
}

function delayPayClick() {
    window.location.href = '/user/home';
}