import com.twitter.hbc.*;
import com.twitter.hbc.httpclient.auth.*;
import com.twitter.hbc.core.*;
import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.conf.*;
import twitter4j.media.*;
import java.util.*;
import java.awt.event.*;

public class TwitterCli
{
    static String consumerKey = "yuSQAz9haXiSSkBBrm5achcjb";
    static String consumerSecret = "5HFyNvlitJpzHgMiVv1iHDbQhZpigPh2pa8VoSc0m5FxMWni1z";
    static String accessToken = "1489329019-zEesacq1spp8uaSWWqmPwix6zA72IRWB5NhS9oD";
    static String accessTokenSecret = "lbI23qG8E3F0TVWKuhTZB3RN4AFcslTEF9UhlRsRADto4";
    //static OAuth1 login = new OAuth1(consumerKey,consumerSecret,accessToken,accessTokenSecret);
    static ConfigurationBuilder cb = new ConfigurationBuilder();
    static TwitterFactory tf;
    static Twitter twitter;
    
    public static void init()
    {
        cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }
    
    public static List getTweets(String search) throws Exception
    {
        Query srch = new Query(search);
        boolean pop = (Query.ResultType.popular == srch.getResultType());
        QueryResult qr = twitter.search(srch);
        List twts = new ArrayList<>();
        for(Status s : qr.getTweets())
        {
            twts.add(new Tweet(s,pop));
        }
        return twts;
    }
    
    public static List getRecent(String search) throws Exception
    {
        Query srch = new Query(search);
        srch.setResultType(Query.ResultType.recent);
        boolean pop = false;
        QueryResult qr = twitter.search(srch);
        List twts = new ArrayList<>();
        for(Status s : qr.getTweets())
        {
            twts.add(new Tweet(s,pop));
        }
        return twts;
    }
    
    public static List getPopular(String search) throws Exception
    {
        Query srch = new Query(search);
        srch.setResultType(Query.ResultType.popular);
        boolean pop = true;
        QueryResult qr = twitter.search(srch);
        List twts = new ArrayList<>();
        for(Status s : qr.getTweets())
        {
            twts.add(new Tweet(s,pop));
        }
        return twts;
    }
    
    public static List getLive(String search)
    {
        return new ArrayList<>();
    }
}