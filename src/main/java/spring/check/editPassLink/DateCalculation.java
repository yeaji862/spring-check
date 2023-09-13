package spring.check.editPassLink;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalculation {

    public Boolean dateCalculation(Date sand_mail){
        boolean status = false;
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.setTime(sand_mail);
        cal.add(Calendar.HOUR, 24);

        if(format.format(cal.getTime()).compareTo(format.format(now)) > 0){
            status = true;
        }

        cal.clear();

        return status;
    }
}
