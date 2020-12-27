package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.domain.DeeplinkBean;
import com.kocak.kerem.linkconverter.domain.UrlBean;
import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.LinkConverterLogPersistenceException;
import com.kocak.kerem.linkconverter.exception.UrlParseException;
import com.kocak.kerem.linkconverter.mapper.UrlDeepLinkMapper;
import com.kocak.kerem.linkconverter.service.LinkConverterLogService;
import com.kocak.kerem.linkconverter.service.LinkConverterService;
import com.kocak.kerem.linkconverter.util.UrlDeepLinkParser;
import com.kocak.kerem.linkconverter.util.UrlDeepLinkStringifyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class LinkConverterServiceImpl implements LinkConverterService {

    @Autowired
    private LinkConverterLogService linkConverterService;

    /**
     * An url is first parsed, and then converted into a deeplink. Later, deeplink's string value is generated, and the request
     * parameter, generated deeplink, their match type are all persisted into the db. In case the url cannot be parsed or validated,
     * a link to trendyol's home page is returned as a response.
     */
    @Override
    public String convertUrlToDeeplink(String url, String username) {
        String deeplink = null;
        try {
            UrlBean urlBean = UrlDeepLinkParser.parseUrl(url);
            DeeplinkBean deeplinkBean = UrlDeepLinkMapper.urlBeanToDeeplinkBean(urlBean);
            deeplink = UrlDeepLinkStringifyer.deeplinkToString(deeplinkBean);
            linkConverterService.save(url, deeplink, ConversionType.TO_DEEPLINK, deeplinkBean.getMatchType(), username);
        } catch (MalformedURLException | UrlParseException | LinkConverterLogPersistenceException e) {
            deeplink = UrlDeepLinkStringifyer.unclassifiedDeepLinkToString();
            linkConverterService.save(url, deeplink, ConversionType.TO_DEEPLINK, MatchType.UNCLASSIFIED, username);
        }
        return deeplink;
    }
}