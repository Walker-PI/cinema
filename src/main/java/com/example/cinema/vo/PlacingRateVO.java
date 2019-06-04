package com.example.cinema.vo;

/**
 * 某一个电影的上座率，用一个含%的字符串表示如“30.0%”,保留了一位小数
 */

public class PlacingRateVO {

    private int movieId;

    private String placingRate;

    private String name;

    public PlacingRateVO(int movieId, String placingRate, String name){
        this.movieId = movieId;
        this.placingRate = placingRate;
        this.name = name;
    }

    public int getMovieId(){ return movieId; }

    public void setMovieId(int movieId){ this.movieId = movieId; }

    public String getPlacingRate(){ return placingRate; }

    public void setPlacingRate(String placingRate){ this.placingRate = placingRate; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }


}
