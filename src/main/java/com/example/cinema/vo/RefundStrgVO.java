package com.example.cinema.vo;

import com.example.cinema.po.RefundStrg;

public class RefundStrgVO {
    /**
     * 以数轴的形式表示距离距离电影开始播放的时间
     * 比如-60 表示距离电影播放还有60分钟
     * 15表示电影已经播放了15分钟
     */
    private int endMinute;

    /**
     *  应该收取的手续费比例 用小数表示
     *  比如0.1 表示收取10%
     */

    private double percent;

    public RefundStrgVO(){

    }

    public RefundStrgVO(RefundStrg refundStrg){
        this.endMinute = refundStrg.getEndMinute();
        this.percent = refundStrg.getPercent();
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }
}
