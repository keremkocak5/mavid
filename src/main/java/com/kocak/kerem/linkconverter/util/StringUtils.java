package com.kocak.kerem.linkconverter.util;

import java.util.Objects;

public class StringUtils {

    private StringUtils() {
    }

    public static String getContextValue(String context, String[] querys, boolean ignoreCase) {
        for (int i = 0; i < (Objects.nonNull(querys) ? querys.length : 0); i++) {
            if (((querys[i].equals(context) && !ignoreCase) || querys[i].equalsIgnoreCase(context) && ignoreCase) && i < querys.length - 1) {
                return querys[i + 1];
            }
        }
        return null;
    }

    public static int getContextIndex(String context, String[] querys, boolean ignoreCase) {
        for (int i = 0; i < (Objects.nonNull(querys) ? querys.length : 0); i++) {
            if (((querys[i].equals(context) && !ignoreCase) || querys[i].equalsIgnoreCase(context) && ignoreCase) && i < querys.length - 1) {
                return i;
            }
        }
        return 0;
    }

    public static String appendQuery(String query, String header, String append, String delimiter) {
        if (Objects.isNull(append))
            return query;
        return query.concat(header).concat(Constants.EQUAL).concat(append).concat(delimiter);
    }

    public static String cleanDelimiters(String query) {
        if (query.endsWith(Constants.AND_MARK) || query.endsWith(Constants.QUESTION_MARK)) {
            return query.substring(0, query.length() - 1);
        }
        return query;
    }
}