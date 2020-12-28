package com.kocak.kerem.linkconverter.domain.bean;

import com.kocak.kerem.linkconverter.enums.MatchType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UrlBean {

    private String host;
    private String brandOrCategoryName;
    private String productName;
    private String contentId;
    private String boutiqueId;
    private String merchantId;
    private String searchQuery;
    private MatchType matchType;

}
