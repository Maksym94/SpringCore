package classes;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Maksym_Petrenko on 8/18/2017.
 */
public class Event {
    private int id = new Random().nextInt(1000);
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df=df;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public static boolean isDay(){
      Calendar calendar = Calendar.getInstance();
      int hour = calendar.get(Calendar.HOUR_OF_DAY);
      if(hour>=8&&hour<=17){
          return true;
      }
      return false;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", message='" + msg + '\'' + ", date=" + df.format(date) + '}';
    }
}
