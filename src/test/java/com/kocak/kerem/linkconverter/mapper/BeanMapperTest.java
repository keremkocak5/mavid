package com.kocak.kerem.linkconverter.mapper;

import com.kocak.kerem.linkconverter.domain.bean.DeeplinkBean;
import com.kocak.kerem.linkconverter.domain.bean.UrlBean;
import com.kocak.kerem.linkconverter.enums.MatchType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BeanMapperTest {

    private static final String BOUTIQUE_ID = "BOUTIQUE";
    private static final String BRAND_OR_CATEGORY_NAME = "BOCN";
    private static final String CONTENT_ID = "CONTENT";
    private static final String MERCHANT = "MERCHANT";
    private static final String PRODUCT = "PRODUCT";
    private static final String SEARCH = "SEARCH";
    private static final MatchType MATCH_TYPE_PRODUCT = MatchType.PRODUCT;

    @Test
    public void urlBeanToDeeplinkBean_shouldReturnDeeplinkBean() {
        UrlBean urlBean = UrlBean.builder().boutiqueId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).contentId(CONTENT_ID).matchType(MATCH_TYPE_PRODUCT).merchantId(MERCHANT).productName(PRODUCT).searchQuery(SEARCH).build();

        DeeplinkBean bean = BeanMapper.urlBeanToDeeplinkBean(urlBean);

        assertThat(bean.getBrandOrCategoryName()).isEqualTo(BRAND_OR_CATEGORY_NAME);
        assertThat(bean.getContentId()).isEqualTo(CONTENT_ID);
        assertThat(bean.getMerchantId()).isEqualTo(MERCHANT);
        assertThat(bean.getProductName()).isEqualTo(PRODUCT);
        assertThat(bean.getSearchQuery()).isEqualTo(SEARCH);
        assertThat(bean.getMatchType()).isEqualTo(MATCH_TYPE_PRODUCT);
        assertThat(bean.getCampaignId()).isEqualTo(BOUTIQUE_ID);
    }

    @Test
    public void deeplinkBeanToUrlBean_shouldReturnUrlBean() {
        DeeplinkBean urlBean = DeeplinkBean.builder().campaignId(BOUTIQUE_ID).brandOrCategoryName(BRAND_OR_CATEGORY_NAME).contentId(CONTENT_ID).matchType(MATCH_TYPE_PRODUCT).merchantId(MERCHANT).productName(PRODUCT).searchQuery(SEARCH).build();

        UrlBean bean = BeanMapper.deeplinkBeanToUrlBean(urlBean);

        assertThat(bean.getBrandOrCategoryName()).isEqualTo(BRAND_OR_CATEGORY_NAME);
        assertThat(bean.getContentId()).isEqualTo(CONTENT_ID);
        assertThat(bean.getMerchantId()).isEqualTo(MERCHANT);
        assertThat(bean.getProductName()).isEqualTo(PRODUCT);
        assertThat(bean.getSearchQuery()).isEqualTo(SEARCH);
        assertThat(bean.getMatchType()).isEqualTo(MATCH_TYPE_PRODUCT);
        assertThat(bean.getBoutiqueId()).isEqualTo(BOUTIQUE_ID);
    }

}