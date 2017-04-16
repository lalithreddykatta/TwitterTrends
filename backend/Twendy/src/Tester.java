import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.File;

public class Tester {
    public static void main(String[] args){
        try {
            TwitterCli.init();
            System.out.println("Initialized");
            List<Tweet> tweet = TwitterCli.getTweetEfficiently("Rick and Morty");
            System.out.println("Number of tweets: " + tweet.size());
            DataSorter sorter = new DataSorter(tweet);

            ArrayList<ArrayList<Tweet>> tweets = sorter.print();

            PrintWriter out = new PrintWriter(new FileWriter("files/Data1.json", false));

            JSONObject obj = new JSONObject();




            for (int i = 0; i < tweets.size(); i++){
                //out.println((i + 1) + " " + tweets.get(i).size() + " 0.005");
                obj.put("Hour" + (i + 1) + "TweetSize", tweets.get(i).size());
                obj.put("Hour" + (i + 1) + "EmotionalRating", "0.005");
                JSONArray arrayJson = new JSONArray();
                JSONObject item = new JSONObject();
                for (int j = 0 ; j < tweets.get(i).size(); j++){
                    //out.println("    " + tweets.get(i).get(j).getStatus().getId() + "    " + tweets.get(i).get(j).getStatus().getText());
                    item.put(Long.toString(tweets.get(i).get(j).getStatus().getId()), tweets.get(i).get(j).getStatus().getText());
                    arrayJson.put(item);
                }
                obj.put("Hour" + (i + 1) + "Tweets", arrayJson);
            }
            //out.close();
            //System.out.print(obj.toString());
            out.print(obj.toString());
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
