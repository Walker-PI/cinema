$(document).ready(function () {
    getHistoryList();

    function getHistoryList() {
        getRequest(
            '/history/all/' + sessionStorage.getItem('id'),
            function (res) {
                res.content.map(function (item) {
                    var historycontent = $('.historycontent');
                    var singleHistoryMessage = '<tr class="history-info">';
                    if(item.type==='card'){
                        singleHistoryMessage = singleHistoryMessage + '<td>购买会员卡</td>';
                    }
                    else if(item.type==='ticket'){
                        singleHistoryMessage = singleHistoryMessage + '<td>购买电影票</td>';
                    }
                    else if(item.type==='charge'){
                        singleHistoryMessage = singleHistoryMessage + '<td>充值会员卡</td>';
                    }
                    singleHistoryMessage = singleHistoryMessage + '<td>' + item.time.split(".")[0].replace("T"," ") + '</td>';
                    singleHistoryMessage = singleHistoryMessage + '<td>¥' + item.fare + '</td>';
                    if(item.type==='card'){
                        singleHistoryMessage = singleHistoryMessage + '<td><button class="item btn btn-link" onclick=getdetail("' + item.id + '","'+ item.type + '")>点击查看</button></td>';
                    }
                    else {
                        singleHistoryMessage = singleHistoryMessage + '<td><button class="item btn btn-link" data-toggle="modal" data-target="#detailModal" onclick=getdetail("' + item.id +'","'+ item.type +'")>点击查看</button></td>';
                    }


                    singleHistoryMessage += '</tr>';
                    var historyBlock = $(singleHistoryMessage);
                    historycontent.append(historyBlock);

                });
            },
            function (error) {
                alert(error);
            });
    }
});

function getdetail(id,type) {
    getRequest(
        '/history/item?historyId='+id+'&type='+type,
        function (res) {
            if(type==='card'){
                window.location.href = '/user/member';
                return;
            }
            var detailmessage=$("#detailmessage");
            if(type==='ticket'){
                detailmessage.text('购买电影详情');
                renderticketHistory(res.content.ticketIds);

            }else if(type==='charge'){
                detailmessage.text('会员卡余额：'+res.content.balance.toFixed(2));
            }
        },
        function (error) {
            alert(error);
        }
    );
}

function renderticketHistory(ticketIds) {
    postRequest(
        '/ticket/generateOrder?ticketIds='+ticketIds,
        {},
        function (res) {
            if(res.success){
                var detail="<table class='table table-bordered'>";
                detail  += '<tr><td>电影名称</td><td>' + res.content.movieName + '</td></tr>';
                detail  += '<tr><td>影厅名</td><td>' + res.content.hallName + '</td></tr>';

                var ticketStr = "";
                for (let ticketInfo of res.content.ticketVOList) {
                    ticketStr += " " + (ticketInfo.rowIndex + 1) + "排" + (ticketInfo.columnIndex + 1) + "座";
                }
                detail  += '<tr><td>座位</td><td>' + ticketStr +'</td></tr>';
                detail  += '<tr><td>放映时间</td><td>' + res.content.scheduleTime.split(".")[0].replace("T"," ") + '</td></tr>';
                detail+="</table>"
                var detailmessage=$("#detailmessage");
                detailmessage.html(detail);
            }
            else {
                alert("获取订单失败");
                window.history.back(-1);
            }
        },
        function (error) {
            alert(JSON.stringify(error));
        }

    )
}
