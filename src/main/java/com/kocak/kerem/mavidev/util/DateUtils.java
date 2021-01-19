package com.kocak.kerem.mavidev.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private DateUtils() {
    }

    public static Date getDateInstance() {
        return Calendar.getInstance().getTime();
    }

}
