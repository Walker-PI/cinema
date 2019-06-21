package com.example.cinema.data.management;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieMapperTest {

    @Autowired
    MovieMapper movieMapper;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertOneMovie() {
    }

    @Test
    public void selectMovieById() {
    }

    @Test
    public void selectMovieByIdAndUserId() {
    }

    @Test
    public void selectAllMovie() {
    }

    @Test
    public void selectOtherMoviesExcludeOff() {
    }

    @Test
    public void selectMovieByKeyword() {
    }

    @Test
    public void updateMovieStatusBatch() {
    }

    @Test
    public void updateMovie() {
    }

    @Test
    public void deleteMovie() {
        List<Integer>list = new ArrayList<>();
        list.add(1);
        movieMapper.deleteMovie(list);
    }
}