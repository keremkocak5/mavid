package com.kocak.kerem.linkconverter.util;

public class Constants {

    public static final String MASTER_EXCEPTION = "An unknown error has occurred, please contact the system administrator ";

    public static final String TRENDYOL_HOST = "www.trendyol.com";
    public static final String DEEPLINK_HOST = "ty://";
    public static final String HTTPS = "https";
    public static final String HTTPS_SLASH = HTTPS.concat("://");
    public static final String QUESTION_MARK = "?";
    public static final String AND_MARK = "&";
    public static final String EQUAL = "=";
    public static final String PAGE = "Page";
    public static final String PRODUCT = "Product";
    public static final String SEARCH = "Search";
    public static final String CONTENT_ID = "ContentId";
    public static final String CAMPAIGN_ID = "CampaignId";
    public static final String MERCHANT_ID = "MerchantId";
    public static final String MERCHANT_ID_S = "merchantId";
    public static final String BOUTIQUE_ID = "boutiqueId";
    public static final String TUM_URUNLER = "tum--urunler";
    public static final String TINY_HOME = DEEPLINK_HOST.concat(Constants.QUESTION_MARK).concat(Constants.PAGE).concat(Constants.EQUAL).concat("Home");
    public static final String TRENDYOL_HOME = HTTPS_SLASH.concat(TRENDYOL_HOST);
    public static final String QUERY = "Query";
    public static final String Q = "q";
    public static final String P = "-p-";
    public static final String ASTERISK = "*";
    public static final CharSequence ASTERISK_REPLACEMENT = "";
    public static final String NULL_USERNAME = "INTERNAL";
    public static final String BRAND = "brand";
    public static final String PRODUCT_NAME = "name";
    public static final String SLASH = "/";

    private Constants() {
    }
}
