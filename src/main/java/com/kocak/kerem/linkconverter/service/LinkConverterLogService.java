package com.kocak.kerem.linkconverter.service;

import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;

public interface LinkConverterLogService {

    void save(String url, String deeplink, ConversionType conversionType, MatchType matchType, String username);

}
