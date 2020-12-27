package com.kocak.kerem.linkconverter.mapper;

import com.kocak.kerem.linkconverter.domain.DeeplinkBean;
import com.kocak.kerem.linkconverter.domain.UrlBean;

public class UrlDeepLinkMapper {

    /**
     * Converts an UrlBean into a DeeplinkBean.
     */
    public static DeeplinkBean urlBeanToDeeplinkBean(UrlBean urlBean) {
        return DeeplinkBean.builder().host(urlBean.getHost()).brandOrCategoryName(urlBean.getBrandOrCategoryName()).campaignId(urlBean.getBoutiqueId()).contentId(urlBean.getContentId()).merchantId(urlBean.getMerchantId()).productName(urlBean.getProductName()).matchType(urlBean.getMatchType()).searchQuery(urlBean.getSearchQuery()).build();
    }
}