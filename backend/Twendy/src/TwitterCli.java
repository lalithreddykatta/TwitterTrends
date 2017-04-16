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
    static public String consumerKey = "yuSQAz9haXiSSkBBrm5achcjb";
    static public String consumerSecret = "5HFyNvlitJpzHgMiVv1iHDbQhZpigPh2pa8VoSc0m5FxMWni1z";
    static public String accessToken = "1489329019-zEesacq1spp8uaSWWqmPwix6zA72IRWB5NhS9oD";
    static public String accessTokenSecret = "lbI23qG8E3F0TVWKuhTZB3RN4AFcslTEF9UhlRsRADto4";
    //static OAuth1 login = new OAuth1(consumerKey,consumerSecret,accessToken,accessTokenSecret);
    static ConfigurationBuilder cb = new ConfigurationBuilder();
    static TwitterFactory tf;
    static Twitter twitter;

    public static void init() {
        cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public static List getTweets(String search) throws Exception {
        Query srch = new Query(search);
        srch.setLang("en");
        Calendar rn = Calendar.getInstance();
        int currentday = rn.get(Calendar.DAY_OF_MONTH);;
        srch.setSince("2017-04-" + currentday);
        List<Tweet> tweets = new LinkedList<>();
        tweets = getRecent(srch);
        QueryResult qr = twitter.search(srch);
        while (tweets.size() < 1000 && qr.hasNext()) {
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

    public static List getRecent(Query srch) throws Exception {
        srch.setResultType(Query.ResultType.recent);
        boolean pop = false;
        QueryResult qr = twitter.search(srch);
        List twts = new ArrayList<>();
        for (Status s : qr.getTweets()) {
            twts.add(new Tweet(s, pop));
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

    public static List getLive(String search)
    {
     return new ArrayList<>();
    }
}