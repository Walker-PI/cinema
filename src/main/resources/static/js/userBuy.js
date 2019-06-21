$(document).ready(function () {
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        var ticketcontent = $('.ticketcontent');


        list.map(function(ticket){
            getRequest(
                '/schedule/' + ticket.scheduleId,
                function(res) {
                    if(res.success){
                        var singleTicketMessage = '<tr class="ticket-info">';
                        var scheduleInfo = res.content;
                        var movieName = scheduleInfo.movieName;
                        var hallName = scheduleInfo.hallName;

                        var seat = (Number(ticket.rowIndex) + 1) + '排' + (Number(ticket.columnIndex) + 1) + '座';
                        var startTime = scheduleInfo.startTime.split(".")[0].replace("T"," ");
                        var endTime = scheduleInfo.endTime.split(".")[0].replace("T", " ");
                        var payState = ticket.state;


                        singleTicketMessage = singleTicketMessage + '<td>' + movieName + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + hallName + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + seat + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + startTime + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + endTime + '</td>';
                        if(ticket.state===2){
                            payState="已失效";
                            singleTicketMessage = singleTicketMessage + '<td>' + payState + '</td>';
                        }
                        else if(ticket.state===1) {
                            payState = "已完成";
                            if (canRefund(ticket.id)) {
                                singleTicketMessage = singleTicketMessage + '<td>' + payState + ' <button type="button" class="btn btn-default " stytle="opacity:0.8;padding:5px;"  onclick="refund(' + ticket.id + ')">退款</button>'+'<button onclick="function () { alert(\"出票成功\") ; }">打印</button>' + '</td>';
                            } else{
                                singleTicketMessage = singleTicketMessage + '<td>' + payState + '</td>';
                            }
                        }else if(ticket.state===0){
                            payState="待支付" ;
                            singleTicketMessage = singleTicketMessage + '<td>' + payState + '    <button class="btn-link" onclick="goPay('+ ticket.id +')">去支付</button>' + '</td>';
                        }
                        else if(ticket.state===3){
                            payState="已退款" ;
                            singleTicketMessage = singleTicketMessage + '<td>' + payState + '</td>';
                        }

                        singleTicketMessage += '</tr>';
                        var ticketsBlock = $(singleTicketMessage);
                        ticketcontent.append(ticketsBlock);
                    } else{
                        alert("查找排片信息失败");
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
        });



    }

});
function goPay(id) {
    window.location.href = '/user/pay?id='+id;
}

function refund(id) {
    window.location.href = '/user/refund?id='+id;
}

function canRefund(id) {
    ticketId=id||[].map(Number);
    var flag=false;
    $.ajax({
        type: 'GET',
        url: "/ticket/get/canRefund?ticketId="+ticketId,
        async: false,
        success: function (res) {
            if(res.success){
                ticketState=res.content||[].map(Number);
                flag= ticketState[0]===1;
            }
            else {
                alert("电影票状态查询失败");
            }
        },
        error: function (error) {
            alert(JSON.stringify(error));
        }
    });
    return flag;
}