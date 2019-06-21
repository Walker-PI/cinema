package com.example.cinema.po;

/**
 * @author fjj
 * @date 2019/4/28 6:09 PM
 */
public class MovieTotalBoxOffice {

    /**
     * 电影id
     */
    private int movieId;

    /**
     * 票房(单位：元)，(PS:如果后续数据量大，可自行处理单位，如改成单位：万元)
     */
    private Double boxOffice;

    /**
     * 电影名
     */
    private String name;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
