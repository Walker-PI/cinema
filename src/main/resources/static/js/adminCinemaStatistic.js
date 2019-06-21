$(document).ready(function() {


    getScheduleRate();
    
    getBoxOffice();

    getAudiencePrice();

    getPlacingRate();

    getPolularMovie();

    function getScheduleRate() {

        getRequest(
            '/statistics/scheduleRate',
            function (res) {
                var data = res.content||[];
                var tableData = data.map(function (item) {
                   return item.time;
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var doughnutData = {
                    labels: nameList,
                    datasets: [{
                        data: tableData,
                        backgroundColor: ["#a3e1d4","#dedede","#b5b8cf"]
                    }]
                } ;


                var doughnutOptions = {
                    responsive: true,
                };


                var ctx4 = document.getElementById("doughnutChart").getContext("2d");
                var doughnutChart=new Chart(ctx4, {type: 'doughnut', data: doughnutData, options:doughnutOptions});

            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getBoxOffice() {

        getRequest(
            '/statistics/boxOffice/total',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.boxOffice;
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var barData = {
                    labels: nameList,
                    datasets: [
                        {
                            label: "box office",
                            backgroundColor: 'rgba(26,179,148,0.5)',
                            borderColor: "rgba(26,179,148,0.7)",
                            pointBackgroundColor: "rgba(26,179,148,1)",
                            pointBorderColor: "#fff",
                            data: tableData
                        },

                    ]
                };

                var barOptions = {
                    responsive: true,

                };

                var ctx2 = document.getElementById("barChart1").getContext("2d");
                var barchart=new Chart(ctx2, {type: 'bar', data: barData, options:barOptions});
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getAudiencePrice() {
        getRequest(
            '/statistics/audience/price',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.price;
                });
                var nameList = data.map(function (item) {
                    return formatDate(new Date(item.date));
                });
                var lineData = {
                    labels: nameList,
                    datasets: [

                        {
                            label: "audience price",
                            backgroundColor: 'rgba(26,179,148,0.5)',
                            borderColor: "rgba(26,179,148,0.7)",
                            pointBackgroundColor: "rgba(26,179,148,1)",
                            pointBorderColor: "#fff",
                            data: tableData,
                        }
                    ]
                };

                var lineOptions = {
                    responsive: true
                };


                var ctx = document.getElementById("lineChart").getContext("2d");
                new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});

            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function postURL(id){
        var moviePostURL="../images/adminIcon.jpg";
        getSyncRequest(
            '/movie/'+id+'/'+sessionStorage.getItem('id'),
            function (res) {
                if(res.success){
                    moviePostURL=res.content.posterUrl;
                }else {
                    alert(res.message);
                }
            },
            function (err) {
                alert(err.message);
            }
        );
        return moviePostURL;
    }
    function getPlacingRate() {
        // todo
        getRequest(
            '/statistics/PlacingRate',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    var len=item.placingRate.length;
                    return 0.01*Number(item.placingRate.substring(0,len-1));
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var barData = {
                    labels:nameList,
                    datasets: [
                        {
                            label: "placing rate",
                            backgroundColor: 'rgba(26,179,148,0.5)',
                            borderColor: "rgba(26,179,148,0.7)",
                            pointBackgroundColor: "rgba(26,179,148,1)",
                            pointBorderColor: "#fff",
                            data: tableData,
                        },
                    ]
                };

                var barOptions = {
                    responsive: true
                };
                var ctx2 = document.getElementById("barChart2").getContext("2d");
                new Chart(ctx2, {type: 'bar', data: barData, options:barOptions});
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getPolularMovie() {
        // todo
        var days=7;
        var movieNum=4;
         getRequest(
            '/statistics/popular/movie?days='+days+'&movieNum='+movieNum,
            function (res) {
                var text="<h3>热门电影排行</h3>";
                $('#popular-movie-container').append(text);
                var data = res.content || [];
                var movieDomStr = "<div class='row' id='popularmovielist'>";
                data.forEach(function (item) {
                    movieDomStr +=
                        "<div  class='col-md-3 movie-item'>" +
                        "<img style='padding: 18px;' class='movie-img' src='"+postURL(item.movieId)+"'/>" +
                        "<span style='background-color: #e0e0e0'><h5>"+item.name+"</h5></span>"+
                        "</div>";
                });
                movieDomStr+="</div>";
                $('#popular-movie-container').append(movieDomStr);
                $('#popular-movie-container').css({
                    "text-align":"center"
                });
                $('.movie-item').css({
                        "width":"18%",
                        "margin-left":"20px"
                });
                $('.movie-img').css({
                    "height": "300px",
                    "width": "208px"
                });
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }
});