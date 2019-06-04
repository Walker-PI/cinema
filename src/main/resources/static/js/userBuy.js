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
                        if(ticket.state===2){
                            payState="已失效";
                        }
                        else if(ticket.state===1){
                            payState="已完成";
                        }else{
                            return ;
                        }

                        singleTicketMessage = singleTicketMessage + '<td>' + movieName + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + hallName + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + seat + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + startTime + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + endTime + '</td>';
                        singleTicketMessage = singleTicketMessage + '<td>' + payState + '</td>';
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
