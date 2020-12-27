package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.LinkConverterLogPersistenceException;
import com.kocak.kerem.linkconverter.service.LinkConverterLogService;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(JUnitParamsRunner.class)
public class LinkConverterServiceImplTest {

    private static final String USER = "USER";
    private static final String TINY_HOME = "ty://?Page=Home";

    private static final String URL1 = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";
    private static final String URL2 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865";
    private static final String URL3 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892";
    private static final String URL4 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064";
    private static final String URL5 = "https://www.trendyol.com/tum--urunler?q=elbise";
    private static final String URL6 = "https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC";
    private static final String URL7 = "https://www.trendyol.com/Hesabim/Favoriler";
    private static final String URL8 = "https://www.trendyol.com/Hesabim/#/Siparislerim";
    private static final String URL9 = "https://www.trend-yol.com/Hesabim/#/Siparislerim";
    private static final String URL10 = "http://www.trendyol.com/Hesabim/#/Siparislerim";
    private static final String URL11 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId";
    private static final String URL12 = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=";
    private static final String URL13 = "abcd";
    private static final String URL14 = "https://www.trendyol.com";
    private static final String URL15 = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&yyyy=twertgrgwr&merchantId=105064&xxx=5555";
    private static final String URL16 = "https://www.trendyol.com/tum--urunler?q=ğüşiöçĞÜŞİÖÇ";
    private static final String URL17 = "https://www.trendyol.com/tum-urunler?q=1234";
    private static final String URL18 = "https://www.trendyol.com/casio/saat-p-?boutiqueId=439892&merchantId=105064";
    private static final String URL19 = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=&merchantId=105064";
    private static final String URL20 = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId&merchantId=105064";
    private static final String URL21 = "https://www.trendyol.com/casio/saat-p-1925865?kerem=321&boutiqueId=439892&kocak=321&merchantId=105064&deneme=999";
    private static final String URL22 = "https://www.trenDyol.com/tUm--urUnler?Q=1234";
    private static final String URL23 = "https://www.trenDyol.com/tUm--urUnler?xxx=123&Q=1234&deneme=321";

    private static final String DEEPLINK1 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
    private static final String DEEPLINK2 = "ty://?Page=Product&ContentId=1925865";
    private static final String DEEPLINK3 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";
    private static final String DEEPLINK4 = "ty://?Page=Product&ContentId=1925865&MerchantId=105064";
    private static final String DEEPLINK5 = "ty://?Page=Search&Query=elbise";
    private static final String DEEPLINK6 = "ty://?Page=Search&Query=%C3%BCt%C3%BC";
    private static final String DEEPLINK7 = "ty://?Page=Product&ContentId=1925865&CampaignId=&MerchantId=105064";
    private static final String DEEPLINK8 = "ty://?Page=Search&Query=ğüşiöçĞÜŞİÖÇ";
    private static final String DEEPLINK9 = "ty://?Page=Search&Query=1234";

    @InjectMocks
    private LinkConverterServiceImpl linkConverterService;
    @Mock
    private LinkConverterLogService linkConverterLogService;

    private static Object[] urlMatcher() {
        return new Object[]{
                new Object[]{URL1, DEEPLINK1},
                new Object[]{URL2, DEEPLINK2},
                new Object[]{URL3, DEEPLINK3},
                new Object[]{URL4, DEEPLINK4},
                new Object[]{URL5, DEEPLINK5},
                new Object[]{URL6, DEEPLINK6},
                new Object[]{URL7, TINY_HOME},
                new Object[]{URL8, TINY_HOME},
                new Object[]{URL9, TINY_HOME},
                new Object[]{URL10, TINY_HOME},
                new Object[]{URL11, DEEPLINK2},
                new Object[]{URL12, DEEPLINK2},
                new Object[]{URL13, TINY_HOME},
                new Object[]{URL14, TINY_HOME},
                new Object[]{URL15, DEEPLINK1},
                new Object[]{URL16, DEEPLINK8},
                new Object[]{URL17, TINY_HOME},
                new Object[]{URL18, TINY_HOME},
                new Object[]{URL19, DEEPLINK7},
                new Object[]{URL20, TINY_HOME},
                new Object[]{URL21, DEEPLINK1},
                new Object[]{URL22, DEEPLINK9},
                new Object[]{URL23, DEEPLINK9},
        };
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("urlMatcher")
    public void convertUrlToDeeplink_shouldReturnCorrectDeeplinkForAllUrls(String url, String targetdeeplink) {
        Mockito.doNothing().when(linkConverterLogService).save(Mockito.anyString(), Mockito.anyString(), any(ConversionType.class), any(MatchType.class), Mockito.anyString());
        String deeplink = linkConverterService.convertUrlToDeeplink(url, USER);
        assertThat(deeplink).isEqualTo(targetdeeplink);
    }

    @Test
    public void convertUrlToDeeplink_shouldHandleLinkConverterLogPersistenceException() {
        Mockito.doNothing().when(linkConverterLogService).save(Mockito.anyString(), Mockito.anyString(), any(ConversionType.class), any(MatchType.class), Mockito.anyString());
        Mockito.doThrow(LinkConverterLogPersistenceException.class).when(linkConverterLogService).save(URL1, "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", ConversionType.TO_DEEPLINK, MatchType.PRODUCT, USER);
        String deeplink = linkConverterService.convertUrlToDeeplink(URL1, USER);
        assertThat(deeplink).isEqualTo(TINY_HOME);
    }
}