package com.kocak.kerem.linkconverter.util.helper;

import com.kocak.kerem.linkconverter.domain.bean.DeeplinkBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.util.Constants;
import com.kocak.kerem.linkconverter.util.StringUtils;

public class DeeplinkStringifyHelper {

    private DeeplinkStringifyHelper() {
    }

    public static String deeplinkBeanToString(DeeplinkBean deeplinkBean) {
        if (MatchType.PRODUCT.equals(deeplinkBean.getMatchType())) {
            return productDeeplinkBeanToString(deeplinkBean);
        } else if (MatchType.SEARCH.equals(deeplinkBean.getMatchType())) {
            return searchDeeplinkBeanToString(deeplinkBean);
        }
        return unclassifiedDeeplinkBeanToString();
    }

    /**
     * Generates a default link to Trendyol's home page
     */
    public static String unclassifiedDeeplinkBeanToString() {
        return Constants.TINY_HOME;
    }

    /**
     * Generates a deeplink from a DeeplinkBean, for a Product Page
     */
    private static String productDeeplinkBeanToString(DeeplinkBean deeplinkBean) {
        String deeplinkString = "".concat(Constants.DEEPLINK_HOST).concat(Constants.QUESTION_MARK).concat(Constants.PAGE).concat(Constants.EQUAL).concat(Constants.PRODUCT).concat(Constants.AND_MARK);
        deeplinkString = StringUtils.appendQuery(deeplinkString, Constants.CONTENT_ID, deeplinkBean.getContentId(), Constants.AND_MARK);
        deeplinkString = StringUtils.appendQuery(deeplinkString, Constants.CAMPAIGN_ID, deeplinkBean.getCampaignId(), Constants.AND_MARK);
        deeplinkString = StringUtils.appendQuery(deeplinkString, Constants.MERCHANT_ID, deeplinkBean.getMerchantId(), Constants.AND_MARK);
        return StringUtils.cleanDelimiters(deeplinkString);
    }

    /**
     * Generates a deeplink from a DeeplinkBean, for a Search Page
     */
    private static String searchDeeplinkBeanToString(DeeplinkBean deeplinkBean) {
        return "".concat(Constants.DEEPLINK_HOST).concat(Constants.QUESTION_MARK).concat(Constants.PAGE).concat(Constants.EQUAL).concat(Constants.SEARCH).concat(Constants.AND_MARK).concat(Constants.QUERY).concat(Constants.EQUAL).concat(deeplinkBean.getSearchQuery());
    }
}