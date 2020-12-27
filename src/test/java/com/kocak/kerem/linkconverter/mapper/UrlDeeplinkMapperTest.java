package com.kocak.kerem.linkconverter.mapper;

import com.kocak.kerem.linkconverter.domain.DeeplinkBean;
import com.kocak.kerem.linkconverter.domain.UrlBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.util.UrlDeepLinkStringifyer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UrlDeeplinkMapperTest {

    private static final String BOUTIQUE_ID = "BOUTIQUE";
    private static final String BRAND_OR_CATEGORY_NAME = "BOCN";
    private static final String CONTENT_ID = "CONTENT";
    private static final String HOST = "WWW";
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
    public void urlBeanToDeeplinkBean_shouldReturnDeeplinkBean() {
        UrlBean urlBean = UrlBean.builder().boutiqueId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).contentId(CONTENT_ID).host(HOST).matchType(MATCH_TYPE_PRODUCT).merchantId(MERCHANT).productName(PRODUCT).searchQuery(SEARCH).build();

        DeeplinkBean bean = UrlDeepLinkMapper.urlBeanToDeeplinkBean(urlBean);

        assertThat(bean.getBrandOrCategoryName()).isEqualTo(BRAND_OR_CATEGORY_NAME);
        assertThat(bean.getContentId()).isEqualTo(CONTENT_ID);
        assertThat(bean.getHost()).isEqualTo(HOST);
        assertThat(bean.getMerchantId()).isEqualTo(MERCHANT);
        assertThat(bean.getProductName()).isEqualTo(PRODUCT);
        assertThat(bean.getSearchQuery()).isEqualTo(SEARCH);
        assertThat(bean.getMatchType()).isEqualTo(MATCH_TYPE_PRODUCT);
        assertThat(bean.getCampaignId()).isEqualTo(BOUTIQUE_ID);
    }

    @Test
    public void unclassifiedDeepLinkToString_shouldReturnTinyHome() {
        String deepLinkString = UrlDeepLinkStringifyer.unclassifiedDeepLinkToString();

        assertThat(deepLinkString).isEqualTo(TINY_HOME);
    }

    @Test
    public void deeplinkToString_shouldReturnDeepLinkString_whenMatchTypeProduct() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).campaignId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).host(HOST).matchType(MATCH_TYPE_PRODUCT).searchQuery(SEARCH).build();

        String deepLinkString = UrlDeepLinkStringifyer.deeplinkToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(PRODUCT_URL);
    }

    @Test
    public void deeplinkToString_shouldReturnDeepLinkString_whenMatchTypeProductNoAttributes() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).contentId(CONTENT_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).host(HOST).matchType(MATCH_TYPE_PRODUCT).searchQuery(SEARCH).build();

        String deepLinkString = UrlDeepLinkStringifyer.deeplinkToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(PRODUCT_URL_NO_ATTRIBUTES);
    }

    @Test
    public void deeplinkToString_shouldReturnDeepLinkString_whenMatchTypeSearch() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).campaignId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).host(HOST).matchType(MATCH_TYPE_SEARCH).searchQuery(SEARCH).build();

        String deepLinkString = UrlDeepLinkStringifyer.deeplinkToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(SEARCH_URL);
    }

    @Test
    public void deeplinkToString_shouldReturnDeepLinkString_whenMatchTypeUnclassified() {
        DeeplinkBean deeplinkBean = DeeplinkBean.builder().productName(PRODUCT).merchantId(MERCHANT).contentId(CONTENT_ID).campaignId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).host(HOST).matchType(MATCH_TYPE_UNCLASSIFIED).searchQuery(SEARCH).build();

        String deepLinkString = UrlDeepLinkStringifyer.deeplinkToString(deeplinkBean);

        assertThat(deepLinkString).isEqualTo(TINY_HOME);
    }
}