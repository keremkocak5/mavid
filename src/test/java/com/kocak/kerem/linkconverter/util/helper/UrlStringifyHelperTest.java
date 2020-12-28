package com.kocak.kerem.linkconverter.util.helper;

import com.kocak.kerem.linkconverter.domain.bean.UrlBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlStringifyHelperTest {

    private static final String BOUTIQUE_ID = "BOUTIQUE";
    private static final String BRAND_OR_CATEGORY_NAME = "BOCN";
    private static final String CONTENT_ID = "CONTENT";
    private static final String MERCHANT = "MERCHANT";
    private static final String PRODUCT = "PRODUCT";
    private static final String SEARCH = "SEARCH";
    private static final MatchType MATCH_TYPE_PRODUCT = MatchType.PRODUCT;
    private static final MatchType MATCH_TYPE_SEARCH = MatchType.SEARCH;
    private static final MatchType MATCH_TYPE_UNCLASSIFIED = MatchType.UNCLASSIFIED;
    private static final String TRENDYOL_HOME = "https://www.trendyol.com";
    private static final String PRODUCT_URL = "https://www.trendyol.com/brand/name-p-CONTENT?boutiqueId=BOUTIQUE&merchantId=MERCHANT";
    private static final String PRODUCT_URL_NO_ATTRIBUTES = "https://www.trendyol.com/brand/name-p-CONTENT";
    private static final String SEARCH_URL = "https://www.trendyol.com/tum--urunler?q=SEARCH";

    @Test
    public void unclassifiedUrlBeanToString_shouldReturnTinyHome() {
        String urlString = UrlStringifyHelper.unclassifiedUrlBeanToString();

        assertThat(urlString).isEqualTo(TRENDYOL_HOME);
    }

    @Test
    public void urlBeanToString_shouldReturnUrlString_whenMatchTypeProduct() {
        UrlBean urlBean = UrlBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).boutiqueId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_PRODUCT).searchQuery(SEARCH).build();

        String urlString = UrlStringifyHelper.urlBeanToString(urlBean);

        assertThat(urlString).isEqualTo(PRODUCT_URL);
    }

    @Test
    public void urlBeanToString_shouldReturnUrlString_whenMatchTypeProductNoAttributes() {
        UrlBean urlBean = UrlBean.builder().productName(PRODUCT).contentId(CONTENT_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_PRODUCT).searchQuery(SEARCH).build();

        String urlString = UrlStringifyHelper.urlBeanToString(urlBean);

        assertThat(urlString).isEqualTo(PRODUCT_URL_NO_ATTRIBUTES);
    }

    @Test
    public void urlBeanToString_shouldReturnUrlString_whenMatchTypeSearch() {
        UrlBean urlBean = UrlBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).boutiqueId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_SEARCH).searchQuery(SEARCH).build();

        String urlString = UrlStringifyHelper.urlBeanToString(urlBean);

        assertThat(urlString).isEqualTo(SEARCH_URL);
    }

    @Test
    public void urlBeanToString_shouldReturnUrlString_whenMatchTypeUnclassified() {
        UrlBean urlBean = UrlBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).boutiqueId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).matchType(MATCH_TYPE_UNCLASSIFIED).searchQuery(SEARCH).build();

        String urlString = UrlStringifyHelper.urlBeanToString(urlBean);

        assertThat(urlString).isEqualTo(TRENDYOL_HOME);
    }
}