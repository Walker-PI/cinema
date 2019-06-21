$(document).ready(function() {

    $(document).on("click","#newButton",function () {
        renderTicketStrategy([{
            endMinute:0,
            percent:0.0,
        }]);
    });
    $(document).on("click","#ackButton",function () {
        updateStra(update());
    });
    $(document).on("click",".deleteStrategy",function () {
        $(this).parents('.row')[0].remove();//检查
        updateStra(update());
        flush();
    });

    renderTicketStrategy(getTicketStrategy());

    function updateStra(stra){
        stra.map(function (item) {
            var li=item;
            li.percent=item.percent/100;
            return li;
        });

        postRequest(
            '/refund/update',
            stra,
            function (res) {
                alert('success');
            },
            function(err) {
                alert('update err');
            }
        );
    }
    function update(stra) {//肯定有错
        var stra=[];
        $('.straitem').each(function () {
            stra.push({
                endMinute:$(this).find('.touchspin1').val(),
                percent:$(this).find('.touchspin2').val(),
            });
        });

        return stra;
    }
    function getTicketStrategy(){
        var stra;
        getRequest(
            '/refund/get',
            function (res) {
                stra=res.content;
            },
            function (err) {
                alert('refundGetErr');
                stra=[{endMinute:0,percent:0,}];
            },
        );
        stra.map(function (item) {
            var li=item;
            li.percent=item.percent*100;
            return li;
        });
        return stra;
    }
    function renderTicketStrategy(stra){
        for(var i=0;i<stra.length;i++){
            var percent=stra[i].percent*100;
            var endMinute=stra[i].endMinute;
            $('#strategyList').append(
                '<div class="row straitem" style="background-color: rgb(255,255,255);padding: 8px">'+
                '<div class="col-md-4">'+
                '<p class="font-bold">'+
                '开场前几分钟'+
                '</p>'+
                '<input class="touchspin1" type="text" value="'+endMinute+'" name="demo1">'+
                '</div>'+
                '<div class="col-md-4">'+
                '<p class="font-bold">'+
                '退款百分比'+
                '</p>'+
                '<input class="touchspin2" type="text" value="'+percent+'" name="demo2">'+
                '</div>'+
                '<div class="col-md-4">'+
                '<p class="font-bold">'+
                '取消策略'+
                '</p>'+
                '<button type="button" class="btn btn-w-m btn-danger deleteStrategy">删除</button>'+
                '</div>'+
                '</div>'
            );

        }
        flush();
    }

    function  flush() {
        $(".touchspin1").TouchSpin({
            buttondown_class: 'btn btn-white',
            buttonup_class: 'btn btn-white',
            min: -9999999,
            max:150,
            step:1,
        });
        $(".touchspin2").TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '%',
            buttondown_class: 'btn btn-white',
            buttonup_class: 'btn btn-white'
        });
        $('.dual_select').bootstrapDualListbox({
            selectorMinimalHeight: 160
        });
    }
});

// $(document).ready(function() {
//
//     function flush() {
//         $(".touchspin1").TouchSpin({
//             buttondown_class: 'btn btn-white',
//             buttonup_class: 'btn btn-white',
//             min: -9999999,
//             max:150,
//             step:1,
//         });
//         $(".touchspin2").TouchSpin({
//             min: 0,
//             max: 100,
//             step: 0.1,
//             decimals: 2,
//             boostat: 5,
//             maxboostedstep: 10,
//             postfix: '%',
//             buttondown_class: 'btn btn-white',
//             buttonup_class: 'btn btn-white'
//         });
//         $('.dual_select').bootstrapDualListbox({
//             selectorMinimalHeight: 160
//         });
//     }
//     function getTicketStrategy(){
//         var stra;
//         getRequest(
//             '/refund/get',
//             function (res) {
//                 stra=res.content;
//             },
//             function (err) {
//                 alert('refundGetErr');
//                 stra=[{endMinute:0,percent:0,}];
//             },
//         );
//         stra.map(function (item) {
//             var li=item;
//             li.percent=item.percent*100;
//             return li;
//         });
//         return stra;
//     }
//     function renderTicketStrategy(stra){
//         for(var i=0;i<stra.length;i++){
//             var percent=stra[i].percent*100;
//             var endMinute=stra[i].endMinute;
//             $('#strategyList').append(
//                 '<div class="row straitem" style="background-color: rgb(255,255,255);padding: 8px">'+
//                 '<div class="col-md-4">'+
//                 '<p class="font-bold">'+
//                 '开场前几分钟'+
//                 '</p>'+
//                 '<input class="touchspin1" type="text" value="'+endMinute+'" name="demo1">'+
//                 '</div>'+
//                 '<div class="col-md-4">'+
//                 '<p class="font-bold">'+
//                 '退款百分比'+
//                 '</p>'+
//                 '<input class="touchspin2" type="text" value="'+percent+'" name="demo2">'+
//                 '</div>'+
//                 '<div class="col-md-4">'+
//                 '<p class="font-bold">'+
//                 '取消策略'+
//                 '</p>'+
//                 '<button type="button" class="btn btn-w-m btn-danger deleteStrategy">删除</button>'+
//                 '</div>'+
//                 '</div>'
//             );
//
//         }
//         flush();
//     }
//     function update(stra) {//肯定有错
//         var stra=[];
//         $('.straitem').each(function () {
//             stra.push({
//                 endMinute:$(this).find('.touchspin1').val(),
//                 percent:$(this).find('.touchspin2').val(),
//             });
//         });
//
//         return stra;
//     }
//     function updateStra(stra){
//         stra.map(function (item) {
//             var li=item;
//             li.percent=item.percent/100;
//             return li;
//         });
//
//         postRequest(
//             '/refund/update',
//             stra,
//             function (res) {
//                 alert('success');
//             },
//             function(err) {
//                 alert('update err');
//             }
//         );
//     }
//
//     renderTicketStrategy(getTicketStrategy());
//
//     $(document).on("click","#newButton",function () {
//         renderTicketStrategy([{
//             endMinute:0,
//             percent:0.0,
//         }]);
//     });
//     $(document).on("click","#ackButton",function () {
//         updateStra(update());
//     });
//     $(document).on("click",".deleteStrategy",function () {
//         $(this).parents('.row')[0].remove();//检查
//         updateStra(update());
//         flush();
//     });
// });
