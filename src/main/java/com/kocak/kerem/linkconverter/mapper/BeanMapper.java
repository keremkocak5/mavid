package com.kocak.kerem.linkconverter.mapper;

import com.kocak.kerem.linkconverter.domain.bean.DeeplinkBean;
import com.kocak.kerem.linkconverter.domain.bean.UrlBean;

public class BeanMapper {

    private BeanMapper() {
    }

    /**
     * Converts an UrlBean into a DeeplinkBean.
     */
    public static DeeplinkBean urlBeanToDeeplinkBean(UrlBean urlBean) {
        return DeeplinkBean.builder().brandOrCategoryName(urlBean.getBrandOrCategoryName()).campaignId(urlBean.getBoutiqueId()).contentId(urlBean.getContentId()).merchantId(urlBean.getMerchantId()).productName(urlBean.getProductName()).matchType(urlBean.getMatchType()).searchQuery(urlBean.getSearchQuery()).build();
    }

    /**
     * Converts a DeeplinkBean into an UrlBean.
     */
    public static UrlBean deeplinkBeanToUrlBean(DeeplinkBean deeplinkBean) {
        return UrlBean.builder().brandOrCategoryName(deeplinkBean.getBrandOrCategoryName()).boutiqueId(deeplinkBean.getCampaignId()).contentId(deeplinkBean.getContentId()).merchantId(deeplinkBean.getMerchantId()).productName(deeplinkBean.getProductName()).matchType(deeplinkBean.getMatchType()).searchQuery(deeplinkBean.getSearchQuery()).build();
    }

}