package com.example.cinema.po;

public class SimpleScheduleItem {
    private Integer id;

    private Integer hallId;

    private Integer movieId;

    private String name;

    public Integer getId(){return id; }

    public void setId(Integer id){this.id = id; }

    public Integer getHallId(){return hallId; }

    public void setHallId(Integer hallId){this.hallId = hallId; }

    public Integer getMovieId(){return movieId; }

    public void setMovieId(Integer movieId){this.movieId = movieId; }

    public String getName(){return name; }

    public void setName(String name){this.name = name; }

}
