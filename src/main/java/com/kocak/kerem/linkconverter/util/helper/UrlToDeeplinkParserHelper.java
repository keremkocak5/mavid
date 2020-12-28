package com.kocak.kerem.linkconverter.util.helper;

import com.kocak.kerem.linkconverter.domain.bean.UrlBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.UrlParseException;
import com.kocak.kerem.linkconverter.util.Constants;
import com.kocak.kerem.linkconverter.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Objects;

@Slf4j
public class UrlToDeeplinkParserHelper {

    private UrlToDeeplinkParserHelper() {
    }

    /**
     * Accepts an Url as a String, and returns its parsed state in a UrlBean.
     * Steps: Parse the URL, if the URL is invalid throw a UrlParseException.
     * Check if the url is secure (https) and if the host is www.trendyol.com.
     * Check if the url is a search or product page:
     * If not so, return an UrlBean for a Unclassified page.
     * If so, parse the url string, and return the corresponding UrlBean, either for a search or product page.
     */
    public static UrlBean parseUrl(String urlString) throws UrlParseException {
        try {
            URL url = new URL(urlString);
            String[] paths = url.getPath().split("/");
            String[] querys = null;
            if (Objects.nonNull(url.getQuery())) {
                querys = url.getQuery().split("[=&]");
            }
            if (!isHttps(url.getProtocol())) {
                return getUnclassifiedUrlBean();
            } else if (!isHostTrendyol(url.getHost())) {
                return getUnclassifiedUrlBean();
            } else if (isProductPage(paths)) {
                return parseProductUrl(url.getHost(), paths, querys);
            } else if (isSearchPage(paths, querys)) {
                return parseSearchUrl(url.getHost(), querys);
            } else {
                return getUnclassifiedUrlBean();
            }
        } catch (Exception e) {
            log.error("Parse error at UrlDeepLinkParser.parseUrl {}", urlString); // logging a web service input is a security issue, and this log should be changed if the web service is public.
            throw new UrlParseException();
        }
    }

    /**
     * Create a UrlBean for unclassified requests.
     */
    public static UrlBean getUnclassifiedUrlBean() {
        return UrlBean.builder().matchType(MatchType.UNCLASSIFIED).build();
    }

    /**
     * Parse the search page's query.
     */
    private static UrlBean parseSearchUrl(String host, String[] querys) {
        String query = StringUtils.getContextValue(Constants.Q, querys, true);
        if (Objects.isNull(query)) {
            return getUnclassifiedUrlBean();
        }
        return UrlBean.builder().host(host).searchQuery(query).matchType(MatchType.SEARCH).build();
    }

    /**
     * Parse the product page's boutiqueId, merchantId, contentId, productId, and brandOrCategoryName.
     */
    private static UrlBean parseProductUrl(String host, String[] paths, String[] querys) {
        String[] productContent = splitProductQuery(paths);
        if (productContent.length < 2) {
            return getUnclassifiedUrlBean();
        }
        String boutique = StringUtils.getContextValue(Constants.BOUTIQUE_ID, querys, true);
        String merchant = StringUtils.getContextValue(Constants.MERCHANT_ID, querys, true);
        if (!isQueriesAdjacent(querys)) {
            return getUnclassifiedUrlBean();
        }
        return UrlBean.builder().host(host).boutiqueId(boutique).brandOrCategoryName(paths[1]).productName(productContent[0]).contentId(productContent[1]).merchantId(merchant).matchType(MatchType.PRODUCT).build();
    }

    /**
     * check against cases such as ..?MerchantId&CampaignId=105064 , where a merchantId may seem like it is set to CampaignId.
     * This recursion tendency invalidates the deeplink.
     */
    private static boolean isQueriesAdjacent(String[] querys) {
        int boutiqueCount = StringUtils.getContextIndex(Constants.BOUTIQUE_ID, querys, true);
        int merchantCount = StringUtils.getContextIndex(Constants.MERCHANT_ID, querys, true);
        return ((boutiqueCount - merchantCount) % 2 == 0);
    }


    /**
     * Validate that the url matches patterns of a search page
     */
    private static boolean isSearchPage(String[] paths, String[] querys) {
        return paths.length == 2 && paths[1].toLowerCase().contains(Constants.TUM_URUNLER) && querys.length > 1;
    }

    /**
     * Validate that the url matches patterns of a product page
     */
    private static boolean isProductPage(String[] paths) {
        return paths.length > 2 && paths[2].toLowerCase().contains(Constants.P);
    }

    private static boolean isHttps(String protocol) {
        return Constants.HTTPS.equalsIgnoreCase(protocol);
    }

    private static boolean isHostTrendyol(String host) {
        return Constants.TRENDYOL_HOST.equalsIgnoreCase(host);
    }

    private static String[] splitProductQuery(String[] paths) {
        return paths[2].split(Constants.P);
    }

}