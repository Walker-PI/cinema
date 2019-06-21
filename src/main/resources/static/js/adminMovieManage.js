$(document).ready(function(){

    getMovieList();

    $("#movie-form-btn").click(function () {
        var formData = getMovieForm();
        if(!validateMovieForm(formData)) {
            alert('电影信息填写错误');
            return;
        }
        postRequest(
            '/movie/add',
            formData,
            function (res) {
                getMovieList();
                $("#movieModal").modal('hide');
                $("#movieModal input").val("");
            },
             function (error) {
                alert(error);
            });
    });

    function getMovieForm() {
        return {
            name: $('#movie-name-input').val(),
            startDate: $('#movie-date-input').val(),
            endDate:$('#movie-enddate-input').val(),
            posterUrl: $('#movie-img-input').val(),
            bigPosterUrl: $('#movie-big-img-input').val(),
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
        if(!data.bigPosterUrl) {
            isValidate = false;
            $('#movie-big-img-input').parent('.form-group').addClass('has-error');
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

    function getMovieList() {
        getRequest(
            '/movie/all',
            function (res) {
                renderMovieList(res.content);
            },
            function (error) {
                alert(error);
            }
        );
    }

    function renderMovieList(list) {
        $('.movie-on-list').empty();
        var movieDomStr = '';
        list.forEach(function (movie) {
            movie.description = movie.description || '';
            movieDomStr +=
                "<li class='movie-item card'>" +
                "<img class='movie-img' src='" + (movie.posterUrl || "../images/defaultAvatar.jpg") + "'/>" +
                "<div class='movie-info'>" +
                "<div class='movie-title'>" +
                "<span class='primary-text'>" + movie.name + "</span>" +
                "<span class='label "+(!movie.status ? 'primary-bg' : 'error-bg')+"'>" + (movie.status ? '已下架' : (new Date(movie.startDate)>=new Date()?'未上映':'热映中')) + "</span>" +
                "<span class='movie-want'><i class='icon-heart error-text'></i>" + (movie.likeCount || 0) + "人想看</span>" +
                "</div>" +
                "<div class='movie-description dark-text'><span>" + movie.description + "</span></div>" +
                "<div>类型：" + movie.type + "</div>" +
                "<div style='display: flex'><span>导演：" + movie.director + "</span><span style='margin-left: 30px;'>主演：" + movie.starring + "</span>" +
                "<div class='movie-operation'><a href='/admin/movieDetail?id="+ movie.id +"'>详情</a></div></div>" +
                "</div>"+
                "</li>";
        });
        $('.movie-on-list').append(movieDomStr);
    }
});