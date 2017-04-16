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
    private void amountOfTweets(){
        int size = 0;
        for (int i = 0; i < tweets.size(); i++){
            size += tweets.get(i).size();
            System.out.println("Inner Array " + (i + 1) + ": " + tweets.get(i).size());
        }
        System.out.println("Number of Tweets during Print: " + size);
    }

    public void print(){
        amountOfTweets();
        System.out.println("Outer Array: " + tweets.size());
        for (int i = 0; i < tweets.size(); i++){
            System.out.println("Inner Array " + (i + 1) + ": " + tweets.get(i).size());
            for (int j = 0 ; j < tweets.get(i).size(); j++){
                System.out.println(i + ": " + tweets.get(i).get(j).getStatus().getText());
            }
        }
    }
}