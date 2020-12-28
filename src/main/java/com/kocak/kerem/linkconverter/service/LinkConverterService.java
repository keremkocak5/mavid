package com.kocak.kerem.linkconverter.service;

public interface LinkConverterService {

    String convertUrlToDeeplink(String url, String username);

    String convertDeeplinkToUrl(String deeplink, String username);
}
