package com.kocak.kerem.linkconverter.util.helper;

import com.kocak.kerem.linkconverter.domain.bean.DeeplinkBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.UrlParseException;
import com.kocak.kerem.linkconverter.util.Constants;
import com.kocak.kerem.linkconverter.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class DeeplinkToUrlParserHelper {

    /**
     * Accepts an Deeplink as a String, and returns its parsed state in a DeeplinkBean.
     * Steps: Parse the Deeplink, if the Deeplink is invalid throw a UrlParseException.
     * Check if the prefix of the Deeplink is ty://?Page.
     * Check if the Deeplink is a search or product page:
     * If not so, return an DeeplinkBean for a Unclassified page.
     * If so, parse the Deeplink string, and return the corresponding DeeplinkBean, either for a search or product page.
     */
    private DeeplinkToUrlParserHelper() {
    }

    public static DeeplinkBean parseDeeplink(String deeplinkString) throws UrlParseException {
        try {
            if (!isValidPrefix(deeplinkString)) {
                return getUnclassifiedDeeplinkBean();
            }
            deeplinkString = deeplinkString.replace(Constants.DEEPLINK_HOST.concat(Constants.QUESTION_MARK), "");
            String[] paths = deeplinkString.split("[=&/?]");
            if (isProductPage(paths)) {
                return parseProductDeeplink(paths);
            } else if (isSearchPage(paths)) {
                return parseSearchDeeplink(paths);
            } else {
                return getUnclassifiedDeeplinkBean();
            }
        } catch (Exception e) {
            log.error("Parse error at DeeplinkToUrlParserHelper.parseDeeplink {}", deeplinkString); // logging a web service input is a security issue, and this log should be changed if the web service is public.
            throw new UrlParseException();
        }
    }

    /**
     * Create a UrlBean for unclassified requests.
     */
    public static DeeplinkBean getUnclassifiedDeeplinkBean() {
        return DeeplinkBean.builder().matchType(MatchType.UNCLASSIFIED).build();
    }

    /**
     * Parse the search page's query.
     */
    private static DeeplinkBean parseSearchDeeplink(String[] paths) {
        String query = StringUtils.getContextValue(Constants.QUERY, paths, false);
        if (Objects.isNull(query)) {
            return getUnclassifiedDeeplinkBean();
        }
        return DeeplinkBean.builder().matchType(MatchType.SEARCH).searchQuery(query).build();
    }

    /**
     * Parse the product page's boutiqueId, merchantId, contentId, productId, and brandOrCategoryName.
     */
    private static DeeplinkBean parseProductDeeplink(String[] paths) {
        String contentId = StringUtils.getContextValue(Constants.CONTENT_ID, paths, false);
        String campaignId = StringUtils.getContextValue(Constants.CAMPAIGN_ID, paths, false);
        String merchantId = StringUtils.getContextValue(Constants.MERCHANT_ID, paths, false);
        if (!isQueriesAdjacent(paths)) {
            return getUnclassifiedDeeplinkBean();
        }
        return DeeplinkBean.builder().matchType(MatchType.PRODUCT).brandOrCategoryName(Constants.BRAND).campaignId(campaignId).contentId(contentId).merchantId(merchantId).productName(Constants.PRODUCT_NAME).build();
    }

    /**
     * check against cases such as ..?MerchantId&CampaignId=105064 , where a merchantId may seem like it is set to CampaignId.
     * This recursion tendency invalidates the deeplink.
     */
    private static boolean isQueriesAdjacent(String[] paths) {
        int boutiqueCount = StringUtils.getContextIndex(Constants.BOUTIQUE_ID, paths, false);
        int merchantCount = StringUtils.getContextIndex(Constants.MERCHANT_ID, paths, false);
        int campaignCount = StringUtils.getContextIndex(Constants.CAMPAIGN_ID, paths, false);
        return ((boutiqueCount - merchantCount) % 2 == 0 && (campaignCount - merchantCount) % 2 == 0 && (campaignCount - boutiqueCount) % 2 == 0);
    }

    /**
     * Validate that the url matches patterns of a search page
     */
    private static boolean isSearchPage(String[] paths) {
        return Constants.SEARCH.equals(StringUtils.getContextValue(Constants.PAGE, paths, false));
    }

    /**
     * Validate that the url matches patterns of a product page
     */
    private static boolean isProductPage(String[] paths) {
        return Constants.PRODUCT.equals(StringUtils.getContextValue(Constants.PAGE, paths, false));
    }

    private static boolean isValidPrefix(String deeplinkString) {
        return (Objects.nonNull(deeplinkString) && deeplinkString.startsWith(Constants.DEEPLINK_HOST.concat(Constants.QUESTION_MARK.concat(Constants.PAGE))));
    }
}