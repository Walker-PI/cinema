package com.example.cinema.po;

public class SimpleScheduleItem {
    private Integer id;

    /**
     * 影厅id
     */
    private Integer hallId;

    /**
     * 电影id
     */
    private Integer movieId;

    /**
     * 电影名
     */
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
