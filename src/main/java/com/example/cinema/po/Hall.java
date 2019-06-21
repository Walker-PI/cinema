package com.example.cinema.po;

/**
 * @author fjj
 * @date 2019/4/28 5:09 PM
 */
public class Hall {
    private Integer id;

    /**
     * 影厅名称
     */
    private String name;

    /**
     * 影厅行数
     */
    private Integer column;

    /**
     * 影厅列数
     */
    private Integer row;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
