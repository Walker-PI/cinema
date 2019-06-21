$(document).ready(function() {

    getAllMovies();

    getVIPStrategy();

    getActivities();


    function getVIPUser(spend){
        var userList=[];
        getRequest(
            '/coupon/getVIP/'+spend,
            function (res) {
                if(res.success){
                    userList=res.content;
                    renderVIPUser(userList);
                }
                else{
                    alert('nothing found');
                }
            },
            function (err) {
                alert(err);
            },
        );
        return userList;
    }
    function renderVIPUser(userList) {
        alert('123'+JSON.stringify(userList));

        var DOM='';

        userList.forEach(function (item) {
            DOM+="                    <div class='row'>" +
                "                        <div class='col-md-4' style='margin-top: 10px;margin-bottom: 10px' >" +
                "                            <label  class='userName'>"+item.name+"</label>" +
                "                        </div>" +
                "                        <div class='col-md-4' style='margin-top: 10px;margin-bottom: 10px'>" +
                "                            <label  class='userSpend'>"+item.spend+"元</label>" +
                "                        </div>" +
                "                        <div class='checkbox checkbox-info col-md-4'>" +
                "                            <input id='ackGive"+item.id+"' class='ackGiveInput' type='checkbox'>" +
                "                            <label for='ackGive"+item.id+"'>赠送</label>" +
                "                        </div>" +
                "                    </div>";
            alert(DOM);
        });
        $('#userListToGive').append(DOM);
    }
    function getBeGiftedUser() {
        var userIdList=[];
        var len=$('.ackGiveInput').length;
        for(var i=0;i<len;i++){
            if($('.ackGiveInput')[i].checked==true){//selected 要改
                userIdList.push($('.ackGiveInput')[i].id.substring(7));
            }
        }
        sessionStorage.setItem('listUser',JSON.stringify(userIdList));
        return userIdList;
    }
    function updateGift(userIdList,CouponId){
        alert(userIdList,CouponId);
        postRequest(
            '/coupon/'+userIdList+'/issue?couponId='+CouponId,
            {},
            function (res) {
                if(res.success){
                    alert('update successfully');
                }else{
                    alert(res.message);
                }
            },
            function (err) {
                alert(err.message);
            }
        )
    }
    function getVIPStrategy() {
        getRequest(
            "/vipStrategy/get",
            function (res) {
                $(".content-VIPStrategy").empty();
                var vipstrategyDomStr = "";
                var targetadd="";
                res.content.forEach(function (strategy) {
                    targetadd+="<span class='title'>满"+strategy.target+"减<span class='error-text title'>"+strategy.add+"</span></span>";
                });
                vipstrategyDomStr+=
                    "<div class='activity-container'>" +
                    "    <div class='activity-card card'>" +
                    "       <div class='activity-line'>" +
                    "           <span class='title'>会员卡充值优惠</span>" +
                    "       </div>" +
                    "       <div class='activity-line'>" +
                    "           <span>优惠描述：</span>" +
                    "           <span class='activity-movie primary-text'>梯度优惠</span>" +
                    "       </div>" +
                    "       <div class='activity-line'>" +
                    "           <span>参与成员：</span>" +
                    "           <span class='activity-movie primary-text'>所有会员</span>" +
                    "       </div>" +
                    "       <div class='activity-line'>" +
                    "           <span>参与电影：</span>" +
                    "           <span class='activity-movie primary-text'>所有电影</span>" +
                    "       </div>" +
                    "    </div>" +
                    "    <div class='activity-coupon primary-bg'>" +
                                targetadd +
                    "    </div>" +
                    "</div>";
                $(".content-VIPStrategy").append(vipstrategyDomStr);
            },
        function (error) {
            alert(error);
            }
        );
    }

    //点击确认发布优惠
    $("#vipstrategy-form-btn").click(function () {
        var form = [];
        var targets=[];
        $(".vipstrategy-target-input").each(function () {
            if($(this).val()!="")
                targets.push(parseInt($(this).val())) ;
        });
        for(i=0;i<targets.length-1;i++){
            if(targets[i]>=targets[i+1]){
                alert("请递增填写需满金额！");
                return;
            }
        }
        var adds=[];
        $(".vipstrategy-add-input").each(function () {
            if($(this).val()!="")
                adds.push(parseInt($(this).val())) ;
        });
        if(targets.length===0){
            alert("发布优惠不能为空。");
            return;
        }
        if(targets.length!=adds.length){
            alert("请填写完整表单，不要留空。如不需要梯度，可选择删除。");
            return;
        }
        for( i=0;i<targets.length;i++){
            form.push({target:targets[i],add: adds[i]});
        }
        postRequest(
            '/vipStrategy/publish',
            form,
            function (res) {
                if(res.success){
                    getVIPStrategy();
                    $("#vipstrategyModal").modal('hide');
                    $("#strategy").empty();
                    addlevel();
                    alert("发布成功");
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    function getActivities() {
        getRequest(
            '/activity/get',
            function (res) {
                var activities = res.content;
                renderActivities(activities);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }
    
    function renderActivities(activities) {
        $(".content-activity").empty();
        var activitiesDomStr = "";

        activities.forEach(function (activity) {
            var movieDomStr = "";
            if(activity.movieList.length){
                activity.movieList.forEach(function (movie) {
                    movieDomStr += "<li class='activity-movie primary-text'>"+movie.name+"</li>";
                });
            }else{
                movieDomStr = "<li class='activity-movie primary-text'>所有热映电影</li>";
            }

            activitiesDomStr+=
                "<div class='activity-container'>" +
                "    <div class='activity-card card'>" +
                "       <div class='activity-line'>" +
                "           <span class='title'>"+activity.name+"</span>" +
                "           <span class='gray-text'>"+"<button class='giveCoupon btn btn-w-m btn-default' style='border:none' data-backdrop='static' data-toggle='modal' data-target='#giftModal' id='"+activity.id+"'>赠送</button>"+"</span>" +
                "       </div>" +
                "       <div class='activity-line'>" +
                "           <span>活动时间："+formatDate(new Date(activity.startTime))+"至"+formatDate(new Date(activity.endTime))+"</span>" +
                "       </div>" +
                "       <div class='activity-line'>" +
                "           <span>参与电影：</span>" +
                "               <ul>"+movieDomStr+"</ul>" +
                "       </div>" +
                "    </div>" +
                "    <div class='activity-coupon primary-bg'>" +
                "        <span class='title'>优惠券："+activity.coupon.name+"</span>" +
                "        <span class='title'>满"+activity.coupon.targetAmount+"减<span class='error-text title'>"+activity.coupon.discountAmount+"</span></span>" +
                "        <span class='gray-text'>"+activity.coupon.description+"</span>" +
                "    </div>" +
                "</div>";
        });
        $(".content-activity").append(activitiesDomStr);
    }

    function getAllMovies() {
        getRequest(
            '/movie/all/exclude/off',
            function (res) {
                var movieList = res.content;
                $('#activity-movie-input').append("<option value="+ -1 +">所有电影</option>");
                movieList.forEach(function (movie) {
                    $('#activity-movie-input').append("<option value="+ movie.id +">"+movie.name+"</option>");
                });
            },
            function (error) {
                alert(error);
            }
        );
    }

    $("#activity-form-btn").click(function () {
        var vals=$("#activityFill input").map(function () {
            return $(this).val();
        });
        for(i=0;i<vals.length;i++){
            if(vals[i]===""){
                alert("请填写完整！");
                return;
            }
        }
       var form = {
           name: $("#activity-name-input").val(),
           description: $("#activity-description-input").val(),
           startTime: $("#activity-start-date-input").val(),
           endTime: $("#activity-end-date-input").val(),
           movieList: [...selectedMovieIds],
           couponForm: {
               description: $("#coupon-name-input").val(),
               name: $("#coupon-description-input").val(),
               targetAmount: $("#coupon-target-input").val(),
               discountAmount: $("#coupon-discount-input").val(),
               startTime: $("#activity-start-date-input").val(),
               endTime: $("#activity-end-date-input").val()
           }
       };


        postRequest(
            '/activity/publish',
            form,
            function (res) {
                if(res.success){
                    getActivities();
                    $("#activityModal").modal('hide');
                    $("#activityModal input").val("");
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    //ES6新api 不重复集合 Set
    var selectedMovieIds = new Set();
    var selectedMovieNames = new Set();

    $('#activity-movie-input').change(function () {
        var movieId = $('#activity-movie-input').val();
        var movieName = $('#activity-movie-input').children('option:selected').text();
        if(movieId==-1){
            selectedMovieIds.clear();
            selectedMovieNames.clear();
        } else {
            selectedMovieIds.add(movieId);
            selectedMovieNames.add(movieName);
        }
        renderSelectedMovies();
    });

    //渲染选择的参加活动的电影
    function renderSelectedMovies() {
        $('#selected-movies').empty();
        var moviesDomStr = "";
        selectedMovieNames.forEach(function (movieName) {
            moviesDomStr += "<span class='label label-primary'>"+movieName+"</span>";
        });
        $('#selected-movies').append(moviesDomStr);
    }

    $('#VIP-User-Search').click(function () {
        $('#userListToGive').find('.row').remove();
        var spend=$('#needSpend').val();
        var userList=getVIPUser(spend);

    });
    $('#gift-form-btn').click(function () {
        updateGift(getBeGiftedUser(),JSON.parse(sessionStorage.getItem('beingGifted')))
    });
    $(document).on("click",'.giveCoupon',function () {
        sessionStorage.setItem('beingGifted',JSON.stringify($(this).attr("id")));
    });
});


//梯度修改
function addlevel() {
    var newlevel="<div class=\"form-group row\" >" +
        "               <label class=\"col-lg-2 control-label\" ><span class=\"error-text\">*</span>需满金额</label>" +
        "               <div class=\"col-lg-4\">" +
        "                   <input type=\"number\" class=\"form-control vipstrategy-target-input\"  placeholder=\"请输入需满金额\">" +
        "               </div>" +
        "               <label class=\"col-lg-2 control-label\" ><span class=\"error-text\">*</span>优惠金额</label>" +
        "               <div class=\"col-lg-4\">" +
        "                   <input type=\"number\" class=\"form-control vipstrategy-add-input\"  placeholder=\"请输入优惠金额\">" +
        "               </div>" +
        "        </div>";
    var newlevelblock=$(newlevel);
    $("#strategy").append(newlevelblock);
}

function deletelevel() {
    $("#strategy").children(':last-child').remove();
}