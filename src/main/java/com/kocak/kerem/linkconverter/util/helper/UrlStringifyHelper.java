package com.kocak.kerem.linkconverter.util.helper;

import com.kocak.kerem.linkconverter.domain.bean.UrlBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.util.Constants;
import com.kocak.kerem.linkconverter.util.StringUtils;

public class UrlStringifyHelper {

    private UrlStringifyHelper() {
    }

    public static String urlBeanToString(UrlBean urlBean) {
        if (MatchType.PRODUCT.equals(urlBean.getMatchType())) {
            return productUrlToString(urlBean);
        } else if (MatchType.SEARCH.equals(urlBean.getMatchType())) {
            return searchUrlToString(urlBean);
        }
        return unclassifiedUrlBeanToString();
    }

    /**
     * Generates a default link to Trendyol's home page
     */
    public static String unclassifiedUrlBeanToString() {
        return Constants.TRENDYOL_HOME;
    }

    /**
     * Generates a url from an UrlBean, for a Product Page
     */
    private static String productUrlToString(UrlBean urlBean) {
        String urlString = "".concat(Constants.HTTPS_SLASH).concat(Constants.TRENDYOL_HOST).concat(Constants.SLASH).concat(Constants.BRAND).concat(Constants.SLASH).concat(Constants.PRODUCT_NAME).concat(Constants.P).concat(urlBean.getContentId()).concat(Constants.QUESTION_MARK);
        urlString = StringUtils.appendQuery(urlString, Constants.BOUTIQUE_ID, urlBean.getBoutiqueId(), Constants.AND_MARK);
        urlString = StringUtils.appendQuery(urlString, Constants.MERCHANT_ID_S, urlBean.getMerchantId(), Constants.AND_MARK);
        return StringUtils.cleanDelimiters(urlString);
    }

    /**
     * Generates a url from a UrlBean, for a Search Page
     */
    private static String searchUrlToString(UrlBean urlBean) {
        return "".concat(Constants.HTTPS_SLASH).concat(Constants.TRENDYOL_HOST).concat(Constants.SLASH).concat(Constants.TUM_URUNLER).concat(Constants.QUESTION_MARK).concat(Constants.Q).concat(Constants.EQUAL).concat(urlBean.getSearchQuery());
    }
}