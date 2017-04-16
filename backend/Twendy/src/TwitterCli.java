import com.twitter.hbc.*;
import com.twitter.hbc.httpclient.auth.*;
import com.twitter.hbc.core.*;
import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.conf.*;
import twitter4j.media.*;

import java.util.*;
import java.awt.event.*;

public class TwitterCli {
    static public String consumerKey = "LqFDdgq7SurJdoQeAtBiDmC8p";
    static public String consumerSecret = "GDdnMzYJyLddVFgdeXET9I0sHzQFYMGgozIrcTiJzDcTSflogo";
    static public String accessToken = "729714377562030082-FtbarB7pQ6BbMK8589vPpoiVgFBMV0i";
    static public String accessTokenSecret = "o1GgPnihASydGh7jgMQEFncw7DQp0hGfHC9BcvpXL4A0a";


    //static OAuth1 login = new OAuth1(consumerKey,consumerSecret,accessToken,accessTokenSecret);
    static ConfigurationBuilder cb = new ConfigurationBuilder();
    static private int numTweets = 10;
    static TwitterFactory tf;
    static Twitter twitter;
    static Query nxt = new Query();

    public static void init() {
        cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public static List getTweets(String search) throws Exception {
        Query srch = new Query(search);
        srch.setLang("en");
        Calendar rn = Calendar.getInstance();
        int currentday = rn.get(Calendar.DAY_OF_MONTH);
        currentday--;
        srch.setSince("2017-04-" + currentday);
        List<Tweet> tweets = new LinkedList<>();
        tweets = getRecent(srch);
        QueryResult qr = twitter.search(srch);
        while (tweets.size() < numTweets && qr.hasNext()) {
            Query nsrch = qr.nextQuery();
            tweets.addAll(getRecent(nsrch));
            qr = twitter.search(nsrch);
        }
        srch = new Query(search);
        srch.setLang("en");
        srch.setSince("2017-04-" + currentday);
        qr = twitter.search(srch);
        int i = 0;
        while ((i < 3) && (qr.hasNext())) {
            qr = twitter.search(srch);
            tweets.addAll(getPopular(
                    srch
            ));
            srch = twitter.search(srch).nextQuery();
            i++;
        }
        return tweets;
    }

    public static List getTweetEfficiently(String search) throws Exception {
        List<Tweet> tweets = new LinkedList<>();
        Query srch = new Query(search);
        tweets.addAll(getRecent(srch));
        while (tweets.size() < numTweets && nxt != null) {
            tweets.addAll(getRecent(nxt));
        }
        return tweets;
    }

    public static List getRecent(Query srch) throws Exception {
        srch.setResultType(Query.ResultType.recent);
        boolean pop = false;
        QueryResult qr = twitter.search(srch);
        List twts = new ArrayList<>();
        for (Status s : qr.getTweets()) {
            twts.add(new Tweet(s, pop));
        }
        if (qr.hasNext()) {
            nxt = qr.nextQuery();
        } else {
            nxt = null;
        }
        return twts;
    }

    public static List getPopular(Query srch) throws Exception {
        srch.setResultType(Query.ResultType.popular);
        boolean pop = true;
        QueryResult qr = twitter.search(srch);
        List twts = new ArrayList<>();
        for (Status s : qr.getTweets()) {
            twts.add(new Tweet(s, pop));
        }
        return twts;
    }

    public static List getLive(String search) {
        return new ArrayList<>();
    }
}