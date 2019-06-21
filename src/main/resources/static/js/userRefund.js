var order = {ticketId: [], couponId: 0};
var refund;

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
                };
                renderOrder(orderInfo);
            }
            else {
                alert("获取订单失败");
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        });

    getRequest(
        "/ticket/get/refund?tickedId="+order.ticketId,
        function (res) {
            if(res.success){
                refund=res.content;
                $('#order-actual-total').text(" ¥" + refund.toFixed(2));
            }
            else {
                alert("查询金额失败");
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        });

    getRequest(
        "/refund/get",
        function (res) {
            (res.content||[]).map(function (item) {
                var strg='<tr><td style="text-align: left;margin: 10px">';
                if(parseInt(item.endMinute)<0)
                    strg+='开场前<span style=\'color: #d43f3a\'>'+item.endMinute.toString().substring(1);
                else
                    strg+='开场<span style=\'color: #d43f3a\'>'+item.endMinute;
                strg+='</span>分钟，</td><td style="text-align: left;margin: 10px">退款百分比为<span style=\'color: #d43f3a\'>'+(parseFloat(item.percent)*100).toFixed(2)+'</span>%</td></tr>';
                $("#strategyContent").append($(strg));

            });
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );

});

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

}

function refundClick() {
    postRequest(
        "/ticket/complete/refund?ticketId="+order.ticketId,
        {},
        function (res) {
            if(res.success){
                $('#order-state').css("display", "none");
                $('#success-state').css("display", "");
            }
            else {
                alert("退款失败");
            }
        },
    function (error) {
        alert(JSON.stringify(error));
        }
    )
}

function cancelRefundClick() {
    window.location.href = '/user/buy';
}