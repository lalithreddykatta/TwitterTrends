import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.http.HttpHeaders;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.http.RequestBuilder;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.Tone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.developer_cloud.util.RequestUtils;
import com.ibm.watson.developer_cloud.util.ResponseConverterUtils;
import com.ibm.watson.developer_cloud.util.Validator;

import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.File;

public class Tester {
    public static void main(String[] args) {
        final int ANGER = 0;
        final int DISGUST = 1;
        final int FEAR = 2;
        final int JOY = 3;
        final int SADNESS = 4;
        try {


            String username = "eab5f7f9-1d61-4c82-9461-e7937b236c4d";
            String password = "rClT0ABCpotl";


            String searchTerm = "Trump";
            TwitterCli.init();
            System.out.println("Initialized");
            List<Tweet> tweet = TwitterCli.getTweetEfficiently(searchTerm);
            System.out.println("Number of tweets: " + tweet.size());
            DataSorter sorter = new DataSorter(tweet);

            ArrayList<ArrayList<Tweet>> tweets = sorter.print();

            PrintWriter out = new PrintWriter(new FileWriter("files/Data1.json", false));

            ToneOptions.Builder builder = new ToneOptions.Builder();
            builder = builder.addTone(Tone.EMOTION);
            ToneAnalyzer tA = new ToneAnalyzer("2017-04-15", username, password);
            ToneOptions options = builder.build();


            JSONObject mainJSONObject = new JSONObject();
            JSONArray mainJSONArray = new JSONArray();

            for (int i = 0; i < tweets.size(); i++) {
                //double sumTone = 0;
                //out.println((i + 1) + " " + tweets.get(i).size() + " 0.005");
                JSONObject obj = new JSONObject();
                obj.put("time", (i + 1));

                //obj.put("Hour" + (i + 1) + "TweetSize", tweets.get(i).size());
                //obj.put("Hour" + (i + 1) + "EmotionalRating", "0.005");
                JSONArray arrayJson = new JSONArray();
                //JSONObject item = new JSONObject();
                String bigTweet = "";
                for (int j = 0; j < tweets.get(i).size(); j++) {
                    //out.println("    " + tweets.get(i).get(j).getStatus().getId() + "    " + tweets.get(i).get(j).getStatus().getText());
                    //item.put(Long.toString(tweets.get(i).get(j).getStatus().getId()), tweets.get(i).get(j).getStatus().getText());
                    bigTweet += tweets.get(i).get(j).getStatus().getText();
                    //sumTone += toneScore;
                    //System.out.println(toneScore + " " + tweets.get(i).get(j).getStatus().getText());
                    arrayJson.put(Long.toString(tweets.get(i).get(j).getStatus().getId()));
                }
                obj.put("tweets", arrayJson);
                if (bigTweet != "") {
                    ToneAnalysis anal = tA.getTone(bigTweet, options).execute();
                    double toneScore = anal.getDocumentTone().getTones().get(0).getTones().get(JOY).getScore();
                    //obj.put("emotionalrating", (sumTone / tweets.get(i).size()) * 2);
                    double tone = ((double) ((int) (((toneScore * 2) * 1000))) / 1000);
                    obj.put("emotionalrating", tone);
                } else {
                    obj.put("emotionalrating", -1);
                }
                mainJSONArray.put(obj);
                //out.println(obj.toString());
            }
            mainJSONObject.put(searchTerm, mainJSONArray);
            //out.close();
            //System.out.print(obj.toString());


            //out.print(mainJSONObject.toString());
            out.print(mainJSONArray.toString());

            //out.print("Test");
            out.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
