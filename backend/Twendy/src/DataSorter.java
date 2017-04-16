import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DataSorter {
    private List<Tweet> statusList;
    private ArrayList<ArrayList<Tweet>> tweets = new ArrayList<ArrayList<Tweet>>(); //hour by tweets

    public DataSorter(){
    }

    public DataSorter(List<Tweet> statusList){
        this.statusList = statusList;
        sort();
        createDataArray();
    }

    private void sort(){
        Collections.sort(statusList);
    }

    private void createDataArray(){
        for (int i = 0; i < 24; i++){

            tweets.add(new ArrayList<Tweet>());
        }
        int hourToIndex = statusList.get(0).getHour() * -1;
        for (int i = 0; i < statusList.size(); i++){
            Tweet t = statusList.get(i);
            int hour = t.getHour();
            int hourIndex = hour + hourToIndex;
            tweets.get(hourIndex).add(t);
        }
    }
}