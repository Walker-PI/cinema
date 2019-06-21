package com.example.cinema.controller.management;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.vo.MovieBatchOffForm;
import com.example.cinema.vo.MovieForm;
import com.example.cinema.bl.management.MovieService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**电影管理
 * @author lms
 * @date 2019/6/12 14:10
 */
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieLikeService movieLikeService;

    @RequestMapping(value = "/movie/add", method = RequestMethod.POST)
    public ResponseVO addMovie(@RequestBody MovieForm addMovieForm){
        return movieService.addMovie(addMovieForm);
    }

    @RequestMapping(value = "/movie/{id}/{userId}", method = RequestMethod.GET)
    public ResponseVO searchOneMovieByIdAndUserId(@PathVariable int id, @PathVariable int userId){
        return movieService.searchOneMovieByIdAndUserId(id, userId);
    }

    @RequestMapping(value = "/movie/all", method = RequestMethod.GET)
    public ResponseVO searchAllMovie(){
        //返回结果中包括已经下架的电影
        return movieService.searchAllMovie();
    }

    @RequestMapping(value="/movie/like/list",method = RequestMethod.GET)
    public ResponseVO searchMovieOrderByLikeNum(@RequestParam int num){
        //返回指定数目的喜爱电影，按被喜爱数目排序，不够的时候返回实际的数目
        return movieService.searchAllOrderByLikeNum(num);
    }

    @RequestMapping(value="/movie/out/list",method = RequestMethod.GET)
    public ResponseVO searchOutMovie(@RequestParam int num){
        //返回尚未上映的电影
        return movieService.searchAllOutMovie(num);
    }

    @RequestMapping(value = "/movie/all/exclude/off", method = RequestMethod.GET)
    public ResponseVO searchOtherMoviesExcludeOff(){
        //返回结果中不包括已经下架的电影
        return movieService.searchOtherMoviesExcludeOff();
    }


    @RequestMapping(value = "/movie/{movieId}/like", method = RequestMethod.POST)
    public ResponseVO likeMovie(@PathVariable int movieId,@RequestParam int userId){
        return movieLikeService.likeMovie(userId,movieId);
    }
    @RequestMapping(value = "/movie/{movieId}/unlike", method = RequestMethod.POST)
    public ResponseVO unlikeMovie(@PathVariable int movieId,@RequestParam int userId){
        return movieLikeService.unLikeMovie(userId,movieId);
    }
    @RequestMapping(value = "/movie/{movieId}/like/count", method = RequestMethod.GET)
    public ResponseVO getMovieLikeCounts(@PathVariable int movieId){
        return movieLikeService.getCountOfLikes(movieId);
    }

    @RequestMapping(value = "/movie/{movieId}/like/date", method = RequestMethod.GET)
    public ResponseVO getMovieLikeCountByDate(@PathVariable int movieId){
        return movieLikeService.getLikeNumsGroupByDate(movieId);
    }

    @RequestMapping(value = "/movie/search",method = RequestMethod.GET)
    public ResponseVO getMovieByKeyword(@RequestParam String keyword){
        return movieService.getMovieByKeyword(keyword);
    }

    @RequestMapping(value = "/movie/off/batch",method = RequestMethod.POST)
    public ResponseVO pullOfBatchOfMovie(@RequestBody MovieBatchOffForm movieBatchOffForm){
        return movieService.pullOfBatchOfMovie(movieBatchOffForm);
    }

    @RequestMapping(value = "/movie/update",method = RequestMethod.POST)
    public ResponseVO updateMovie(@RequestBody MovieForm updateMovieForm){
        return movieService.updateMovie(updateMovieForm);
    }

    @RequestMapping(value = "movie/delete",method = RequestMethod.DELETE)
    public ResponseVO deleteMovie(@RequestParam List<Integer>movieIds){
        return movieService.deleteMovie(movieIds);
    }

}
