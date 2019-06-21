$(document).ready(function() {
    var canSeeDate = 0;
    var userData;
    getCanSeeDayNum();
    getCinemaHalls();
    searchAll();

    function searchAll(){
        getRequest(
            '/role/get/all',
            function(res){
                userData=res.content;
                renderUserList(res.content);
            },
            function(err){
                alert(err);
            }
        );
    }
    function searchByNameAndType(){
        var len=$('#status').children('option').length;
        var type;
        for(var i=0;i<len;i++){
            if($('#status').children('option')[i].selected=="true"){
                type=$('#status').children('option')[i].value;
            }
        }
        var name=$('#user-name-search-input').val();

        postRequest(
            '/role/getByName?usename='+name,
            {},
            function(res){
                if(res.success){
                    alert("res success")
                    alert(JSON.stringify(res.content))
                    var data=res.content;
                    var realcontent ;
                    if(type != 3){
                        realcontent = data.filter(function (item) {
                            return item.userType == type;
                        });
                    }else{
                        realcontent=data;
                    }
                    renderUserList(realcontent);
                }else{
                    alert("res.message:"+res.message)
                }

            },
            function(err){
                alert('cuowu'+JSON.stringify(err));
            }
        );
    }
    function renderUserList(con){
        $('#user-list-tbody').html("");
        var userdom='';
        con.forEach(function(item,index){
            userdom+=
                '<tr class="user_tr">'+
                '<td class="usernameitem">'+
                item.username+
                '</td>'+
                '<td class="text-right">'+
                '<div class="col-sm-4">'+
                '<div class="form-group">'+
                '<select name="status" class="form-control user-type-change">'+
                '<option value="0"'+(item.userType==0?' selected':'')+'>经理</option>'+
                '<option value="1"'+(item.userType==1?' selected':'')+'>员⼯</option>'+
                '<option value="2"'+(item.userType==2?' selected':'')+'>用户</option>'+
                '</select>'+
                '</div>'+
                '</div>'+
                '</td>'+
                '</tr>'
        });
        $('#user-list-tbody').append(userdom);
    }
    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                halls = res.content;
                sessionStorage.setItem('Hall',JSON.stringify(halls));
                renderHall(halls);
            },
            function (error) {
                alert(JSON.stringify(error));
                alert('cuowuhall');
            }
        );
    }
    function renderHall(halls){
        $('#hall-card').empty();
        var hallDomStr = "";
        halls.forEach(function (hall) {
            var seat = "";
            for(var i =0;i<hall.row;i++){
                var temp = ""
                for(var j =0;j<hall.column;j++){
                    temp+="<div class='cinema-hall-seat'></div>";
                }
                seat+= "<div>"+temp+"</div>";
            }
            var hallDom =
                "<div class='cinema-hall'>" +
                "<div>" +
                "<span class='cinema-hall-name'>"+ hall.name +"</span>" +
                "<span class='cinema-hall-size'>"+ hall.column +'*'+ hall.row
                +"</span>" +
                "</div>" +
                "<div class='cinema-seat'>" + seat +
                "</div>" +
                "<div class='hall-item-foot'>"+
                "<button type='button' class='btn btn-w-m btn-danger deleteHallButton' style='padding-right: 10px;'>删除影厅</button>"+
            "<span> </span>"+
            "<button type='button' class='btn btn-w-m btn-warning updateHallButton' style='padding-left: 10px;' data-backdrop='static' data-toggle='modal' data-target='#updateHallModal'>修改</button>"+
            "</div>"+
            "</div>";
            hallDomStr+=hallDom;
        });
        $('#hall-card').append(hallDomStr);
    }
    function getHallForm() {
        return {
            name: $('#hall-name-input').val(),
            column:$('#hall-column').val(),
            row:$('#hall-row').val(),
        };
    }
    function getupdateHallForm() {
        return {
            name: $('#update-hall-name-input').val(),
            column:$('#update-hall-column').val(),
            row:$('#update-hall-row').val(),
        };
    }
    function getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            function (res) {
                canSeeDate = res.content;
                $("#can-see-num").text(canSeeDate);
            },
            function (error) {
                alert(JSON.stringify(error));
                alert('cuowuday');
            }
        );
    }

    $("#searchButton").click(function(){
        var len=$('#status').children('option').length;
        var type;
        for(var i=0;i<len;i++){
            if($('#status').children('option')[i].selected==true){
                type=$('#status').children('option')[i].value;
            }
        }
        var name=$('#user-name-search-input').val();

        postRequest(
            '/role/getByName?username='+name,
            {},
            function(res){
                if(res.success){
                    alert("res success")
                    alert(JSON.stringify(res.content))
                    var data=res.content;
                    var realcontent ;
                    if(type != 3){
                        realcontent = data.filter(function (item) {
                            return item.userType == type;
                        });
                    }else{
                        realcontent=data;
                    }
                    renderUserList(realcontent);
                }else{
                    alert("res.message:"+res.message)
                }

            },
            function(err){
                alert('cuowu'+JSON.stringify(err));
            }
        );
    });
    $('#canview-modify-btn').click(function () {
        $("#canview-modify-btn").hide();
        $("#canview-set-input").val(canSeeDate);
        $("#canview-set-input").show();
        $("#canview-confirm-btn").show();
    });
    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
// 验证⼀下是否为数字
        postRequest(
            '/schedule/view/set',
            {day:dayNum},
            function (res) {
                if(res.success){
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
                alert('sche');
            }
        );
    })
    $('#searchButton').click(function () {
        searchByNameAndType();
    });
    $('#changeButton').click(function(){
        var namelist=[];
        $('.usernameitem').each(function(){
            namelist.push($(this).text());
        });
        var typelist=[];
        $('.user-type-change').each(function(){
            typelist.push($(this).find('option:selected').val());
            alert($(this).find('option:selected').val());
        });
        for(var i=0;i< namelist.length;i++){
            var usernowname=namelist[i];
            var type=typelist[i];
            var fildata=userData.filter(function (item) {
                return item.username==usernowname;
            });
            var id=fildata[0].id;
            postRequest(
                '/role/update?userId='+id+'&userType='+type,
                {},
                function (res) {
                    alert('chenggong');
                },
                function (err) {
                    alert(err);
                }
            );
        }
    });
    $("#hall-form-btn").click(function(){
        var hallVo=getHallForm();
        postRequest(
            '/hall/add',
            hallVo,
            function (res) {
                alert('success');
            },
            function (err) {
                alert('error');
            }
        );
        getCinemaHalls();
        window.location.reload();
    });
    $(document).on("click",".deleteHallButton",function(){
        var name=$(this).parents('.cinema-hall').find('.cinema-hall-name').text();
        var allhall=JSON.parse(sessionStorage.getItem('Hall'));
        var id=allhall.filter(function (item) {
            return item.name==name;
        })[0].id;
        deleteRequest(
            '/hall/delete?id='+id,
            {},
            function (res) {
                alert('success');
            },
            function (err) {
                alert(JSON.parse(err));
                alert('error');
            }
        );
        getCinemaHalls();
        window.location.reload();
    });
    $(document).on("click",".updateHallButton",function () {
        var name=$(this).parents('.cinema-hall').find('.cinema-hall-name').text();
        sessionStorage.setItem('beingUpdate',JSON.stringify({NAME:name,}));
    });
    $("#update-hall-form-btn").click(function(){
        var hallVo=getupdateHallForm();
        var name=JSON.parse(sessionStorage.getItem('beingUpdate')).NAME;
        var all=JSON.parse(sessionStorage.getItem('Hall'));
        var id;
        for(var i =0;i< all.length;i++){
            if(all[i].name==name){
                id=all[i].id;
            }
        }
        hallVo.id=id;
        postRequest(
            '/hall/update/'+id,
            hallVo,
            function (res) {
                if(res.success){
                    alert('success');
                }else{
                    alert(res.message);
                }
            },
            function (err) {
                alert(JSON.parse(error));
            }
        );
        getCinemaHalls();
        window.location.reload();
    });

});