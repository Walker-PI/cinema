$(document).ready(function(){

    getMovieList('');

    function getMovieList(keyword) {
        getRequest(
            '/movie/search?keyword='+keyword,
            function (res) {
                renderMovieList(res.content);
            },
             function (error) {
            alert(error);
        });
    }

    function renderMovieList(list) {
        $('.movie-on-list').empty();
        var movieDomStr = '';
        list.forEach(function (movie) {
            movie.description = movie.description || '';
            movieDomStr +=
                "<li class='movie-item card'>" +
                "<img class='movie-img' src='" + movie.posterUrl + "'/>" +
                "<div class='movie-info'>" +
                "<div class='movie-title'>" +
                "<span class='primary-text'>" + movie.name + "</span>" +
                "<span class='label "+(!movie.status ? 'primary-bg' : 'error-bg')+"'>" + (movie.status ? '已下架' : (new Date(movie.startDate)>=new Date()?'未上映':'热映中')) + "</span>" +
                "<span class='movie-want'><i class='icon-heart error-text'></i>" + (movie.likeCount || 0) + "人想看</span>" +
                "</div>" +
                "<div class='movie-description dark-text'><span>" + movie.description + "</span></div>" +
                "<div>类型：" + movie.type + "</div>" +
                "<div style='display: flex'><span>导演：" + movie.director + "</span><span style='margin-left: 30px; max-width: 80%'>主演：" + movie.starring + "</span>" +
                "<div class='movie-operation' style='width:max-content ' ><a href='/user/movieDetail?id="+ movie.id +"'>详情</a></div></div>" +
                "</div>"+
                "</li>";
        });
        $('.movie-on-list').append(movieDomStr);
    }

    $('#search-btn').click(function () {
        getMovieList($('#search-input').val());
    })

    $("#typefilter a").click(function () {
        if($(this).text()==="全部")
            getMovieList('');
        else
            getMovieList($(this).text());
        $("#typefilter a").each(function () {
            $(this).removeClass('active');
        });

        $(this).addClass('active');
    });

    $("#showfilter a").click(function () {
        if($(this).text()==="全部")
            getMovieList('');
        else
            getMovieList($(this).text());
        $("#showfilter a").each(function () {
            $(this).removeClass('active');
        });
        $(this).addClass('active');
    });

});

