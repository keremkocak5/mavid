package com.kocak.kerem.linkconverter.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private DateUtils() {
    }

    public static Date getDateInstance() {
        return Calendar.getInstance().getTime();
    }
}
