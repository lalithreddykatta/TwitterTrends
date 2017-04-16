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
            List<Tweet> tweet = TwitterCli.getTweets("United");
            System.out.println("Number of tweets: " + tweet.size());
            DataSorter sorter = new DataSorter(tweet);

            ArrayList<ArrayList<Tweet>> tweets = sorter.print();

            PrintWriter out = new PrintWriter(new FileWriter("files/Data1.txt", false));

            for (int i = 0; i < tweets.size(); i++){
                out.println((i + 1) + " " + tweets.get(i).size() + " 0.005");
                for (int j = 0 ; j < tweets.get(i).size(); j++){
                    out.println("    " + tweets.get(i).get(j).getStatus().getText());
                }
            }
            out.close();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
