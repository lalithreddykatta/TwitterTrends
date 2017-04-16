import twitter4j.*;
import java.lang.Comparable;
import java.util.Date;
import java.util.Calendar;

public class Tweet implements Comparable
{
    private Status data;
    private boolean popular;
    public Tweet(Status s, Boolean pop)
    {
        data = s;
        popular = pop;
    }

    public Status getStatus(){
        return data;
    }

    public int getHour(){
        Date currentDate = data.getCreatedAt();
        int hour = currentDate.getHours();
        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        return currentHour - hour;
    }

    public int compareTo(Object obj){
        Tweet otherStatus = (Tweet) obj;
        int hour = getHour();
        int otherHour = otherStatus.getHour();
        if (hour > otherHour){
            return 1;
        } else if (hour < otherHour){
            return -1;
        } else{
            return 0;
        }
    }
}
