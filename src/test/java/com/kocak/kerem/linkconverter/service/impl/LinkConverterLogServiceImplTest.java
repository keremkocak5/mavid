package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.LinkConverterLogPersistenceException;
import com.kocak.kerem.linkconverter.repository.TyLinkConverterLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LinkConverterLogServiceImplTest {
    private static final String DEEPLINK = "deeplink";
    private static final String URL = "url";
    private static final String USER = "USER";
    private static final MatchType MATCH_TYPE_PRODUCT = MatchType.PRODUCT;
    private static final ConversionType CONVERSION_TYPE = ConversionType.TO_DEEPLINK;

    @InjectMocks
    private LinkConverterLogServiceImpl linkConverterLogService;
    @Mock
    private TyLinkConverterLogRepository tyLinkConverterLogRepository;

    @Test
    public void saveLinkConverterTransaction_shouldPersistOnce() {
        linkConverterLogService.save(URL, DEEPLINK, CONVERSION_TYPE, MATCH_TYPE_PRODUCT, USER);
        verify(tyLinkConverterLogRepository, times(1)).save(any());
    }

    @Test
    public void saveLinkConverterTransaction_shouldThrowExceptionWhenUrlNull() {
        Assertions.assertThrows(LinkConverterLogPersistenceException.class, () -> linkConverterLogService.save(null, DEEPLINK, CONVERSION_TYPE, MATCH_TYPE_PRODUCT, USER));
    }

    @Test
    public void saveLinkConverterTransaction_shouldThrowExceptionWhenDeeplinkNull() {
        Assertions.assertThrows(LinkConverterLogPersistenceException.class, () -> linkConverterLogService.save(URL, null, CONVERSION_TYPE, MATCH_TYPE_PRODUCT, USER));
    }

    @Test
    public void saveLinkConverterTransaction_shouldThrowExceptionWhenConversionTypeNull() {
        Assertions.assertThrows(LinkConverterLogPersistenceException.class, () -> linkConverterLogService.save(URL, DEEPLINK, null, MATCH_TYPE_PRODUCT, USER));
    }

    @Test
    public void saveLinkConverterTransaction_shouldThrowExceptionWhenMatchTypeNull() {
        Assertions.assertThrows(LinkConverterLogPersistenceException.class, () -> linkConverterLogService.save(URL, DEEPLINK, CONVERSION_TYPE, null, USER));
    }
}