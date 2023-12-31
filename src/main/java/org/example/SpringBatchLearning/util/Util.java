package org.example.SpringBatchLearning.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyy");
    private static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public static boolean isSameDay(Date date1, Date date2) {
        return date1 != null && date2 != null && date1.getTime() == date2.getTime();
    }

    public static String dayFormat(Date date) {
        return dayFormat.format(date);
    }

    public static String hourOfDayFormat(Date date) {
        return hourFormat.format(date);
    }
}
