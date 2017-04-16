import java.util.List;

public class Tester {
    public static void main(String[] args){
        try {
            TwitterCli.init();
            System.out.println("Initialized");
            List<Tweet> tweets = TwitterCli.getTweets("United");
            System.out.println("Number of tweets: " + tweets.size());
            DataSorter sorter = new DataSorter(tweets);

            sorter.print();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
