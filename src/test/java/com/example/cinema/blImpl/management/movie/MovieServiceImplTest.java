package com.example.cinema.blImpl.management.movie;

import com.example.cinema.bl.management.MovieService;
import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.MovieVO;
import com.example.cinema.vo.ResponseVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieServiceImplTest {
    @Autowired
    MovieService movieService;

    @Test
    public void searchAllOrderByLikeNum(){
        int num = 5;
        ResponseVO responseVO = movieService.searchAllOrderByLikeNum(num);
        List<MovieVO>movieVOList = (List)responseVO.getContent();
        for(MovieVO movieVO:movieVOList){
            System.out.println(movieVO.getLikeCount());
        }
    }

    @Test
    public void searchAllOutMovie(){
        int num=5;
        ResponseVO responseVO = movieService.searchAllOutMovie(5);
        List<MovieVO>movieVOList = (List)responseVO.getContent();
        for(MovieVO movieVO:movieVOList){
            System.out.println(movieVO.getId());
        }
    }
}