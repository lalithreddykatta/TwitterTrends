import twitter4j.*;

public class Tweet
{
    private Status data;
    private boolean popular;
    public Tweet(Status s, Boolean pop)
    {
        data = s;
        popular = pop;
    }
}
