$(document).ready(function(){
    var movieForm;
    var movieId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var userId = sessionStorage.getItem('id');
    var isLike = false;
    $("#movieModal").modal('hide');
    getMovie();
    if(sessionStorage.getItem('role') === 'admin')
        getMovieLikeChart();

    function getMovieLikeChart() {
       getRequest(
           '/movie/' + movieId + '/like/date',
           function(res){
               var data = res.content,

                    dateArray = [],
                    numberArray = [];
               data.forEach(function (item) {
                   dateArray.push(item.likeTime);
                   numberArray.push(item.likeNum);
               });

               var myChart = echarts.init($("#like-date-chart")[0]);

               // 指定图表的配置项和数据
               var option = {
                   title: {
                       text: '想看人数变化表'
                   },
                   xAxis: {
                       type: 'category',
                       data: dateArray
                   },
                   yAxis: {
                       type: 'value'
                   },
                   series: [{
                       data: numberArray,
                       type: 'line'
                   }]
               };

               // 使用刚指定的配置项和数据显示图表。
               myChart.setOption(option);
           },
           function (error) {
               alert(error);
           }
       );
    }

    function getMovie() {
        getRequest(
            '/movie/'+movieId + '/' + userId,
            function(res){
                var data = res.content;
                movieForm=res.content;
                isLike = data.islike;
                repaintMovieDetail(data);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function repaintMovieDetail(movie) {
        !isLike ? $('.icon-heart').removeClass('error-text') : $('.icon-heart').addClass('error-text');
        $('#like-btn span').text(isLike ? ' 已想看' : ' 想 看');
        $('#movie-img').attr('src',movie.posterUrl);
        $('#movie-name').text(movie.name);
        $('#order-movie-name').text(movie.name);
        $('#movie-description').text(movie.description);
        $('#movie-startDate').text(new Date(movie.startDate).toLocaleDateString());
        $('#movie-type').text(movie.type);
        $('#movie-country').text(movie.country);
        $('#movie-language').text(movie.language);
        $('#movie-director').text(movie.director);
        $('#movie-starring').text(movie.starring);
        $('#movie-writer').text(movie.screenWriter);
        $('#movie-length').text(movie.length);
    }

    // user界面才有
    $('#like-btn').click(function () {
        var url = isLike ?'/movie/'+ movieId +'/unlike?userId='+ userId :'/movie/'+ movieId +'/like?userId='+ userId;
        postRequest(
             url,
            null,
            function (res) {
                 isLike = !isLike;
                getMovie();
            },
            function (error) {
                alert(error);
            });
    });

    // admin界面才有
    // $("#modify-btn").click(function () {
    //     // $("#movieModal").modal('display');
    //
    //    // alert('交给你们啦，修改时需要在对应html文件添加表单然后获取用户输入，提交给后端，别忘记对用户输入进行验证。（可参照添加电影&添加排片&修改排片）');
    // });

    $("#delete-btn").click(function () {
        if(!confirm('确定要删除吗？')){
            return ;
        }
        deleteRequest(
            '/movie/delete'+'?movieIds='+movieForm.id,
            {},
            function (res) {
                alert('删除成功');
                window.location.href='/admin/movie/manage';
                //window.event.returnValue=false;
            },
            function (error) {
                alert('删除失败');
            });
        return false;
        //alert('交给你们啦，下架别忘记需要一个确认提示框，也别忘记下架之后要对用户有所提示哦');
    });
    $("#pull-btn").click(function () {
        if(!confirm('确定要下架吗？')){
            return ;
        }
        var formData = {};
        formData.movieIdList=[movieId];
        postRequest(
            '/movie/off/batch',
            formData,
            function (res) {
                alert('下架成功');
                getMovie();
                // window.location.href='/admin/movie/manage';
                //window.event.returnValue=false;
            },
            function (error) {
                alert('下架失败');
            });
        return false;
        //alert('交给你们啦，下架别忘记需要一个确认提示框，也别忘记下架之后要对用户有所提示哦');
    });
    $("#movie-form-btn").click(function () {
        var formData = getMovieForm();
        formData.id=movieId;
        if(!validateMovieForm(formData)) {
            alert('电影信息填写错误');
            return;
        }
        postRequest(
            '/movie/update',
            formData,
            function (res) {
                getMovie();
                $("#movieModal").modal('hide');
                alert('修改成功');
            },
            function (error) {
                alert(error+'修改失败');
            });
        return false;
    });


    function getMovieForm() {
        return {
            name: $('#movie-name-input').val(),
            startDate: $('#movie-date-input').val(),
            endDate:$('#movie-enddate-input').val(),
            posterUrl: $('#movie-img-input').val(),
            description: $('#movie-description-input').val(),
            type: $('#movie-type-input').val(),
            length: $('#movie-length-input').val(),
            country: $('#movie-country-input').val(),
            starring: $('#movie-star-input').val(),
            director: $('#movie-director-input').val(),
            screenWriter: $('#movie-writer-input').val(),
            language: $('#movie-language-input').val()
        };
    }
    function validateMovieForm(data) {
        var isValidate = true;
        if(!data.name) {
            isValidate = false;
            $('#movie-name-input').parent('.form-group').addClass('has-error');
        }
        if(!data.posterUrl) {
            isValidate = false;
            $('#movie-img-input').parent('.form-group').addClass('has-error');
        }
        if(!data.startDate) {
            isValidate = false;
            $('#movie-date-input').parent('.form-group').addClass('has-error');
        }
        if(!data.endDate) {
            isValidate = false;
            $('#movie-enddate-input').parent('.form-group').addClass('has-error');
        }
        if(!data.posterUrl) {
            isValidate = false;
            $('#movie-img-input').parent('.form-group').addClass('has-error');
        }
        if(!data.description) {
            isValidate = false;
            $('#movie-description-input').parent('.form-group').addClass('has-error');
        }
        if(!data.type) {
            isValidate = false;
            $('#movie-type-input').parent('.form-group').addClass('has-error');
        }
        if(!data.length) {
            isValidate = false;
            $('#movie-length-input').parent('.form-group').addClass('has-error');
        }
        if(!data.country) {
            isValidate = false;
            $('#movie-country-input').parent('.form-group').addClass('has-error');
        }
        if(!data.starring) {
            isValidate = false;
            $('#movie-star-input').parent('.form-group').addClass('has-error');
        }
        if(!data.director) {
            isValidate = false;
            $('#movie-director-input').parent('.form-group').addClass('has-error');
        }
        if(!data.screenWriter) {
            isValidate = false;
            $('#movie-writer-input').parent('.form-group').addClass('has-error');
        }
        if(!data.language) {
            isValidate = false;
            $('#movie-language-input').parent('.form-group').addClass('has-error');
        }

        return isValidate;
    }
});

