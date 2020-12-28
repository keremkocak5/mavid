package com.kocak.kerem.linkconverter.util.helper;

import com.kocak.kerem.linkconverter.domain.bean.DeeplinkBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeeplinkStringifyHelperTest {

    private static final String BOUTIQUE_ID = "BOUTIQUE";
    private static final String BRAND_OR_CATEGORY_NAME = "BOCN";
    private static final String CONTENT_ID = "CONTENT";
    private static final String MERCHANT = "MERCHANT";
    private static final String PRODUCT = "PRODUCT";
    private static final String SEARCH = "SEARCH";
    private static final MatchType MATCH_TYPE_PRODUCT = MatchType.PRODUCT;
    private static final MatchType MATCH_TYPE_SEARCH = MatchType.SEARCH;
    private static final MatchType MATCH_TYPE_UNCLASSIFIED = MatchType.UNCLASSIFIED;
    private static final String TINY_HOME = "ty://?Page=Home";
    private static final String PRODUCT_URL = "ty://?Page=Product&ContentId=CONTENT&CampaignId=BOUTIQUE&MerchantId=MERCHANT";
    private static final String PRODUCT_URL_NO_ATTRIBUTES = "ty://?Page=Product&ContentId=CONTENT";
    private static final String SEARCH_URL = "ty://?Page=Search&Query=SEARCH";

    @Test
    public void unclassifiedDeeplinkBeanToString_shouldReturnTinyHome() {
        String deepLinkString = DeeplinkStringifyHelper.unclassifiedDeeplinkBeanToString();

        assertThat(deepLinkString).isEqualTo(TINY_HOME);
    }

    @Test
    public void deeplinkBeanToString_shouldReturnDeepLinkString_whenMatchTypeProduct() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).campaignId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_PRODUCT).searchQuery(SEARCH).build();

        String deepLinkString = DeeplinkStringifyHelper.deeplinkBeanToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(PRODUCT_URL);
    }

    @Test
    public void deeplinkBeanToString_shouldReturnDeepLinkString_whenMatchTypeProductNoAttributes() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).contentId(CONTENT_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_PRODUCT).searchQuery(SEARCH).build();

        String deepLinkString = DeeplinkStringifyHelper.deeplinkBeanToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(PRODUCT_URL_NO_ATTRIBUTES);
    }

    @Test
    public void deeplinkBeanToString_shouldReturnDeepLinkString_whenMatchTypeSearch() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).campaignId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_SEARCH).searchQuery(SEARCH).build();

        String deepLinkString = DeeplinkStringifyHelper.deeplinkBeanToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(SEARCH_URL);
    }

    @Test
    public void deeplinkBeanToString_shouldReturnDeepLinkString_whenMatchTypeUnclassified() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).campaignId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_UNCLASSIFIED).searchQuery(SEARCH).build();

        String deepLinkString = DeeplinkStringifyHelper.deeplinkBeanToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(TINY_HOME);
    }
}