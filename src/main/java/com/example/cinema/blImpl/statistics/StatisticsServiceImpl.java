package com.example.cinema.blImpl.statistics;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.data.statistics.StatisticsMapper;
import com.example.cinema.po.AudiencePrice;
import com.example.cinema.po.MovieScheduleTime;
import com.example.cinema.po.MovieTotalBoxOffice;
import com.example.cinema.po.SimpleScheduleItem;
import com.example.cinema.vo.AudiencePriceVO;
import com.example.cinema.vo.MovieScheduleTimeVO;
import com.example.cinema.vo.MovieTotalBoxOfficeVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import com.example.cinema.vo.PlacingRateVO;
import com.example.cinema.po.Hall;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;
    @Override
    public ResponseVO getScheduleRateByDate(Date date) {
        try{
            Date requireDate = date;
            if(requireDate == null){
                requireDate = new Date();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));

            Date nextDate = getNumDayAfterDate(requireDate, 10);
            return ResponseVO.buildSuccess(movieScheduleTimeList2MovieScheduleTimeVOList(statisticsMapper.selectMovieScheduleTimes(requireDate, nextDate)));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTotalBoxOffice() {
        try {
            return ResponseVO.buildSuccess(movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(statisticsMapper.selectMovieTotalBoxOffice()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAudiencePriceSevenDays() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date startDate = getNumDayAfterDate(today, -2);
            List<AudiencePriceVO> audiencePriceVOList = new ArrayList<>();
            for(int i = 0; i < 7; i++){
                AudiencePriceVO audiencePriceVO = new AudiencePriceVO();
                Date date = getNumDayAfterDate(startDate, i);
                audiencePriceVO.setDate(date);
                List<AudiencePrice> audiencePriceList = statisticsMapper.selectAudiencePrice(date, getNumDayAfterDate(date, 1));
                double totalPrice = audiencePriceList.stream().mapToDouble(item -> item.getTotalPrice()).sum();
                audiencePriceVO.setPrice(Double.parseDouble(String.format("%.2f", audiencePriceList.size() == 0 ? 0 : totalPrice / audiencePriceList.size())));
                audiencePriceVOList.add(audiencePriceVO);
            }
            return ResponseVO.buildSuccess(audiencePriceVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getMoviePlacingRateByDate(Date date) {
        try{
            /*
            如果传过来的日期是null, 那默认为今天
             */
            Date requireDate = date;
            if(requireDate == null){
                requireDate = new Date();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));

            Date nextDate = getNumDayAfterDate(requireDate, 7);

            List<PlacingRateVO> moviePlacingRateList = new ArrayList<>();

            List<SimpleScheduleItem> simpleScheduleItemList = statisticsMapper.selectSimpleScheduleItemByDate(requireDate, nextDate);

            int Size = simpleScheduleItemList.size();
            int viewPersons = 0;
            int totalSeats = 0;

            for(int i = 0; i < Size; i ++){
                SimpleScheduleItem simpleScheduleItem = simpleScheduleItemList.get(i);
                int numTicket = statisticsMapper.selectTicketNumByScheduleId(simpleScheduleItem.getId());
                Hall hall = statisticsMapper.selectHallByHallId(simpleScheduleItem.getHallId());

                viewPersons += numTicket;
                totalSeats += hall.getColumn() * hall.getRow();

                if(i+1 == Size || simpleScheduleItem.getMovieId().equals(simpleScheduleItemList.get(i+1).getMovieId()) == false){
                    double placingRate = (totalSeats == 0 ? 0 : 1.0 * viewPersons / totalSeats);
                    viewPersons = 0;
                    totalSeats = 0;
                    String placingRateStringFormat = String.format("%.1f", placingRate *100) + "%";
                    moviePlacingRateList.add(new PlacingRateVO(simpleScheduleItem.getMovieId(), placingRateStringFormat, simpleScheduleItem.getName()));
                }
            }
            return ResponseVO.buildSuccess(moviePlacingRateList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
        //要求见接口说明
    }

    @Override
    public ResponseVO getPopularMovies(int days, int movieNum) {
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date startDate = getNumDayAfterDate(today, -days/2);
            Date endDate = getNumDayAfterDate(today, days - days/2);
            List<MovieTotalBoxOffice> movieTotalBoxOffices = statisticsMapper.selectMovieTotalBoxOfficeBetweenStartDateAndEndDate(startDate,endDate);
            return ResponseVO.buildSuccess(numMovieTotalBoxOfficeList2numMovieTotalBoxOfficeList(movieTotalBoxOffices,movieNum));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
        //要求见接口说明
    }


    /**
     * 获得num天后的日期
     * @param oldDate
     * @param num
     * @return
     */
    Date getNumDayAfterDate(Date oldDate, int num){
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }



    private List<MovieScheduleTimeVO> movieScheduleTimeList2MovieScheduleTimeVOList(List<MovieScheduleTime> movieScheduleTimeList){
        List<MovieScheduleTimeVO> movieScheduleTimeVOList = new ArrayList<>();
        for(MovieScheduleTime movieScheduleTime : movieScheduleTimeList){
            movieScheduleTimeVOList.add(new MovieScheduleTimeVO(movieScheduleTime));
        }
        return movieScheduleTimeVOList;
    }


    private List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(List<MovieTotalBoxOffice> movieTotalBoxOfficeList){
        List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeVOList = new ArrayList<>();
        for(MovieTotalBoxOffice movieTotalBoxOffice : movieTotalBoxOfficeList){
            movieTotalBoxOfficeVOList.add(new MovieTotalBoxOfficeVO(movieTotalBoxOffice));
        }
        return movieTotalBoxOfficeVOList;
    }

    private List<MovieTotalBoxOfficeVO> numMovieTotalBoxOfficeList2numMovieTotalBoxOfficeList(List<MovieTotalBoxOffice> movieTotalBoxOfficeList, int movieNum){
        List<MovieTotalBoxOfficeVO> numMovieTotalBoxOfficeVOList = new ArrayList<>();
        for(MovieTotalBoxOffice movieTotalBoxOffice : movieTotalBoxOfficeList){
            if(movieTotalBoxOffice.getBoxOffice() == null)movieTotalBoxOffice.setBoxOffice(0.0);
            numMovieTotalBoxOfficeVOList.add(new MovieTotalBoxOfficeVO(movieTotalBoxOffice));
            movieNum --;
            if(movieNum == 0)break;
        }
        return numMovieTotalBoxOfficeVOList;
    }

}
