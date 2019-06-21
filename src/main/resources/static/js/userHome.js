$(document).ready(function () {

    // var likeList=[];
    // var boxList=[];
    // var outList=[];
    // function getPopular(){
    //     var popular=[];
    //     getRequest(
    //         '/statistics/popular/movie?days='+7+'&movieNum='+5,
    //         function (res) {
    //             if(res.success){
    //                 popular=res.content;
    //             } else{
    //                 alert('popular'+JSON.stringify(res.message));
    //             }
    //         },
    //         function (err) {
    //             alert('popular fail'+JSON.stringify(err));
    //         },
    //     );
    //     return popular;
    // }
    // function getOut(){
    //     var out=[];
    //     getRequest(
    //         '/movie/out/list?num=5',
    //         function (res) {
    //             if(res.success){
    //                 out=res.content;
    //             } else{
    //                 alert('out'+JSON.stringify(res.message));
    //             }
    //         },
    //         function (err) {
    //             alert('out'+JSON.stringify(err));
    //         },
    //     );
    //     return out;
    // }
    // function getLike(){
    //     getRequest(
    //         '/movie/like/list?num=5',
    //         function (res) {
    //             if(res.success){
    //                 likeList=res.content;
    //                 alert(JSON.stringify(like));
    //             } else{
    //                 alert('like'+JSON.stringify(res.message));
    //             }
    //         },
    //         function (err) {
    //             alert('like'+JSON.stringify(err));
    //         },
    //     );
    //
    // }
    // likeList=getLike();
    // // boxList=getPopular();
    // // outList=getOut();
    // var i=0;
    // // $('.imgtoslide').each(function () {
    // //     getLike();
    // //     alert('likeList'+i+" " +JSON.stringify(likeList));
    // //     var list=likeList;
    // //     alert(list[i].bigPosterUrl)
    // //     $(this).append("<img src='"+list[i].bigPosterUrl+"'>");
    // //     $(this).href+=list[i].id;
    // //     $(this).title=list[i].name;
    // //     i++;
    // // });
    // // var i=1;
    // $('.imgtoslide').each(function () {
    //     $(this).append("<img src='https://bossaudioandcomic-1252317822.file.myqcloud.com/activity/document/da505b7f0abfdd6fac6d8bdad81ddc03.jpg'>");
    //     i++;
    // });



    function getPopular(){
        var popular=[];
        getRequest(
            '/statistics/popular/movie?days='+7+'&movieNum='+10,
            function (res) {
                if(res.success){
                    popular=res.content;
                    popular=popular||[];
                    var DOM="";
                    popular.forEach(function (item) {
                        DOM+= "<div class=\"statistic-item\" style='border-radius:8px;height:38px;padding: 4px;margin: 4px;background-color: rgba(210, 225, 202, 0.13)'>\n" +
                            "     <span style='font-size:16px;'>"+item.name+"</span>\n" +
                            "     <span style='font-size:16px;' class=\"error-text\">"+item.boxOffice+"</span>\n" +
                            "</div>";

                    });
                    $('#boxOfMovie').append(DOM);
                } else{
                    alert('popular'+JSON.stringify(res.message));
                }
            },
            function (err) {
                alert('popular fail'+JSON.stringify(err));
            },
        );

    }
    function getOut(){
        var out=[];
        getRequest(
            '/movie/out/list?num=6',
            function (res) {
                if(res.success){
                    out=res.content;
                    var DOM="";
                    out.forEach(function (item) {
                                DOM+="<div class=\"col-md-2\">\n" +
                                "            <div class=\"ibox\" >\n" +
                                "                <div class=\"ibox-content product-box\" style='border-radius: 8px'>\n" +
                                "                    <div class=\"product-imitation\" style='padding: 0px;border-radius: 8px'>\n" +
                                "                       <img style='height: 210px;width: 175.22px border-radius: 8px' src='"+item.posterUrl+"'/> \n"+
                                "                    </div>\n" +
                                "                    <div class=\"product-desc\">\n" +
                                "                        <a href='/user/movieDetail\?id="+item.id+"' class='product-name'> "+item.name+"</a>\n" +
                                "                        <div class=\"small m-t-xs\">\n" +
                                "                        </div>\n" +
                                "                    </div>\n" +
                                "                </div>\n" +
                                "            </div>\n" +
                                "        </div>"
                    });
                    $("#iscoming").append(DOM);
                } else{
                    alert('out'+JSON.stringify(res.message));
                }
            },
            function (err) {
                alert('out'+JSON.stringify(err));
            },
        );

    }
    function getLike(){
        var like=[];
        getSyncRequest(
            '/movie/like/list?num=5',
            function (res) {
                if(res.success){
                    like=res.content;
                    var i=0;
                    $('.imgtoslide').each(function () {
                        var list=like;
                        $(this).append("<img style='width: inherit;height:400px;' src='"+list[i].bigPosterUrl+"'>");
                        $(".imgtoslide")[i].href+=list[i].id;
                        $(".imgtoslide")[i].title=list[i].name;
                        i++;
                    });
                    // $('.imgtoslide').each(function () {
                    //     $(this).append("<img src='../images/adminIcon.jpg'>");
                    //     i++;
                    // });

                } else{
                    alert('like'+JSON.stringify(res.message));
                }
            },
            function (err) {
                alert('like'+JSON.stringify(err));
            },
        );

    }

    getLike();
    getPopular();
    getOut();





});
(function($) {

    $.fn.slideBox = function(options) {
        //默认参数

        var defaults = {
            direction : 'left',//left,top
            duration : 0.6,//unit:seconds
            easing : 'swing',//swing,linear
            delay : 3,//unit:seconds
            startIndex : 0,
            hideClickBar : true,
            clickBarRadius : 5,//unit:px
            hideBottomBar : false,
            width : null,
            height : null
        };
        var settings = $.extend(defaults, options || {});
        //计算相关数据
        var wrapper = $(this), ul = wrapper.children('ul.items'), lis = ul.find('li'), firstPic = lis.first().find('img');
        var li_num = lis.length, li_height = 0, li_width = 0;
        //定义滚动顺序:ASC/DESC.ADD.JENA.201208081718
        var order_by = 'ASC';
        //初始化
        var init = function(){
            if(!wrapper.length) return false;
            //手动设定值优先.ADD.JENA.201303141309
            li_height = settings.height ? settings.height : lis.first().height();
            li_width = settings.width ? settings.width : lis.first().width();

            wrapper.css({width: li_width+'px', height:li_height+'px'});
            lis.css({width: li_width+'px', height:li_height+'px'});//ADD.JENA.201207051027

            if (settings.direction == 'left') {
                ul.css('width', li_num * li_width + 'px');
            } else {
                ul.css('height', li_num * li_height + 'px');
            }
            ul.find('li:eq('+settings.startIndex+')').addClass('active');

            if(!settings.hideBottomBar){//ADD.JENA.201208090859
                var tips = $('<div class="tips"></div>').css('opacity', 0.6).appendTo(wrapper);
                var title = $('<div class="title"></div>').html(function(){
                    var active = ul.find('li.active').find('a'), text = active.attr('title'), href = active.attr('href');
                    return $('<a>').attr('href', href).text(text);
                }).appendTo(tips);
                var nums = $('<div class="nums"></div>').hide().appendTo(tips);
                lis.each(function(i, n) {
                    var a = $(n).find('a'), text = a.attr('title'), href = a.attr('href'), css = '';
                    i == settings.startIndex && (css = 'active');
                    $('<a>').attr('href', href).text(text).addClass(css).css('borderRadius', settings.clickBarRadius+'px').mouseover(function(){
                        $(this).addClass('active').siblings().removeClass('active');
                        ul.find('li:eq('+$(this).index()+')').addClass('active').siblings().removeClass('active');
                        start();
                        stop();
                    }).appendTo(nums);
                });

                if(settings.hideClickBar){//ADD.JENA.201206300847
                    tips.hover(function(){
                        nums.animate({top: '0px'}, 'fast');
                    }, function(){
                        nums.animate({top: tips.height()+'px'}, 'fast');
                    });
                    nums.show().delay(2000).animate({top: tips.height()+'px'}, 'fast');
                }else{
                    nums.show();
                }
            }

            lis.length>1 && start();
        }
        //开始轮播
        var start = function() {
            var active = ul.find('li.active'), active_a = active.find('a');
            var index = active.index();
            if(settings.direction == 'left'){
                offset = index * li_width * -1;
                param = {'left':offset + 'px' };
            }else{
                offset = index * li_height * -1;
                param = {'top':offset + 'px' };
            }

            wrapper.find('.nums').find('a:eq('+index+')').addClass('active').siblings().removeClass('active');
            wrapper.find('.title').find('a').attr('href', active_a.attr('href')).text(active_a.attr('title'));

            ul.stop().animate(param, settings.duration*1000, settings.easing, function() {
                active.removeClass('active');
                if(order_by=='ASC'){
                    if (active.next().length){
                        active.next().addClass('active');
                    }else{
                        order_by = 'DESC';
                        active.prev().addClass('active');
                    }
                }else if(order_by=='DESC'){
                    if (active.prev().length){
                        active.prev().addClass('active');
                    }else{
                        order_by = 'ASC';
                        active.next().addClass('active');
                    }
                }
            });
            wrapper.data('timeid', window.setTimeout(start, settings.delay*1000));
        };
        //停止轮播
        var stop = function() {
            window.clearTimeout(wrapper.data('timeid'));
        };
        //鼠标经过事件
        wrapper.hover(function(){
            stop();
        }, function(){
            wrapper.data('timeid', window.setTimeout(start, settings.delay*1000));//ADD.JENA.201303141309
        });
        //首张图片加载完毕后执行初始化
        var imgLoader = new Image();
        imgLoader.onload = function(){
            imgLoader.onload = null;
            init();
        }
        imgLoader.src = firstPic.attr('src');

    };
})(jQuery);
