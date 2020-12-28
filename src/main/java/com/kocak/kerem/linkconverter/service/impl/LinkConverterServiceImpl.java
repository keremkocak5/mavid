package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.domain.bean.DeeplinkBean;
import com.kocak.kerem.linkconverter.domain.bean.UrlBean;
import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.LinkConverterLogNullValueException;
import com.kocak.kerem.linkconverter.exception.UrlParseException;
import com.kocak.kerem.linkconverter.mapper.BeanMapper;
import com.kocak.kerem.linkconverter.mapper.TyLinkConverterLogMapper;
import com.kocak.kerem.linkconverter.service.LinkConverterLogService;
import com.kocak.kerem.linkconverter.service.LinkConverterService;
import com.kocak.kerem.linkconverter.util.helper.DeeplinkStringifyHelper;
import com.kocak.kerem.linkconverter.util.helper.DeeplinkToUrlParserHelper;
import com.kocak.kerem.linkconverter.util.helper.UrlStringifyHelper;
import com.kocak.kerem.linkconverter.util.helper.UrlToDeeplinkParserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkConverterServiceImpl implements LinkConverterService {

    @Autowired
    private LinkConverterLogService linkConverterService;

    /**
     * An url is first validated and parsed. It is then converted into a deeplink. Later, deeplink's string value is generated, and the request
     * parameter, generated deeplink, their match type are all persisted into the db. In case the url cannot be parsed or validated,
     * a deeplink to trendyol's home page is returned as a response.
     */
    @Override
    public String convertUrlToDeeplink(String url, String username) {
        String deeplink;
        try {
            UrlBean urlBean = UrlToDeeplinkParserHelper.parseUrl(url);
            DeeplinkBean deeplinkBean = BeanMapper.urlBeanToDeeplinkBean(urlBean);
            deeplink = DeeplinkStringifyHelper.deeplinkBeanToString(deeplinkBean);
            linkConverterService.save(TyLinkConverterLogMapper.mapTyLinkConverterLog(url, deeplink, ConversionType.TO_DEEPLINK, deeplinkBean.getMatchType(), username));
        } catch (UrlParseException | LinkConverterLogNullValueException e) {
            deeplink = DeeplinkStringifyHelper.unclassifiedDeeplinkBeanToString();
            linkConverterService.save(TyLinkConverterLogMapper.mapTyLinkConverterLog(url, deeplink, ConversionType.TO_DEEPLINK, MatchType.UNCLASSIFIED, username));
        }
        return deeplink;
    }

    /**
     * A deeplink is first validated and parsed. It is then converted into a Url. Later,Url's string value is generated, and the request
     * parameter, generated url, their match type are all persisted into the db. In case the deeplink cannot be parsed or validated,
     * an Url to trendyol's home page is returned as a response.
     */
    @Override
    public String convertDeeplinkToUrl(String deeplink, String username) {
        String url;
        try {
            DeeplinkBean deeplinkBean = DeeplinkToUrlParserHelper.parseDeeplink(deeplink);
            UrlBean urlBean = BeanMapper.deeplinkBeanToUrlBean(deeplinkBean);
            url = UrlStringifyHelper.urlBeanToString(urlBean);
            linkConverterService.save(TyLinkConverterLogMapper.mapTyLinkConverterLog(url, deeplink, ConversionType.TO_URL, deeplinkBean.getMatchType(), username));
        } catch (UrlParseException | LinkConverterLogNullValueException e) {
            url = UrlStringifyHelper.unclassifiedUrlBeanToString();
            linkConverterService.save(TyLinkConverterLogMapper.mapTyLinkConverterLog(url, deeplink, ConversionType.TO_URL, MatchType.UNCLASSIFIED, username));
        }
        return url;
    }
}