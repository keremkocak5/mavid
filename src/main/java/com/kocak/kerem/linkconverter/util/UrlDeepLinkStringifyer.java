package com.kocak.kerem.linkconverter.util;

import com.kocak.kerem.linkconverter.domain.DeeplinkBean;
import com.kocak.kerem.linkconverter.enums.MatchType;

public class UrlDeepLinkStringifyer {

    public static String deeplinkToString(DeeplinkBean deeplinkBean) {
        if (MatchType.PRODUCT.equals(deeplinkBean.getMatchType())) {
            return productDeepLinkToString(deeplinkBean);
        } else if (MatchType.SEARCH.equals(deeplinkBean.getMatchType())) {
            return searchDeepLinkToString(deeplinkBean);
        }
        return unclassifiedDeepLinkToString();
    }

    /**
     * Generates a default link to Trendyol's home page
     */
    public static String unclassifiedDeepLinkToString() {
        return Constants.TINY_HOME;
    }

    /**
     * Generates a deeplink from a DeepLinkBean, for a Product Page
     */
    private static String productDeepLinkToString(DeeplinkBean deeplinkBean) {
        String deeplinkString = "".concat(Constants.DEEPLINK_HOST).concat(Constants.QUESTION_MARK).concat(Constants.PAGE).concat(Constants.EQUALS).concat(Constants.PRODUCT).concat(Constants.AND_MARK);
        if (deeplinkBean.getContentId() != null) {
            deeplinkString = deeplinkString.concat(Constants.CONTENT_ID).concat(Constants.EQUALS).concat(deeplinkBean.getContentId()).concat(Constants.AND_MARK);
        }
        if (deeplinkBean.getCampaignId() != null) {
            deeplinkString = deeplinkString.concat(Constants.CAMPAIGN_ID).concat(Constants.EQUALS).concat(deeplinkBean.getCampaignId()).concat(Constants.AND_MARK);
        }
        if (deeplinkBean.getMerchantId() != null) {
            deeplinkString = deeplinkString.concat(Constants.MERCHANT_ID).concat(Constants.EQUALS).concat(deeplinkBean.getMerchantId()).concat(Constants.AND_MARK);
        }
        if (deeplinkString.endsWith(Constants.AND_MARK)) {
            return deeplinkString.substring(0, deeplinkString.length() - 1);
        }
        return deeplinkString;
    }

    /**
     * Generates a deeplink from a DeepLinkBean, for a Search Page
     */
    private static String searchDeepLinkToString(DeeplinkBean deeplinkBean) {
        return "".concat(Constants.DEEPLINK_HOST).concat(Constants.QUESTION_MARK).concat(Constants.PAGE).concat(Constants.EQUALS).concat(Constants.SEARCH).concat(Constants.AND_MARK).concat(Constants.QUERY).concat(Constants.EQUALS).concat(deeplinkBean.getSearchQuery());
    }
}