package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.model.trendyoltest.TyLinkConverterLog;
import com.kocak.kerem.linkconverter.service.LinkConverterLogService;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(JUnitParamsRunner.class)
public class LinkConverterServiceImplTest {

    private static final String USER = "USER";
    private static final String TINY_HOME = "ty://?Page=Home";
    private static final String TRENDYOL_HOME = "https://www.trendyol.com";

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
    private static final String URL24 = "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064";
    private static final String URL25 = "https://www.trendyol.com/brand/name-p-1925865";
    private static final String URL26 = "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892";
    private static final String URL27 = "https://www.trendyol.com/brand/name-p-1925865?merchantId=105064";
    private static final String URL28 = "https://www.trendyol.com/tum--urunler?q=222";
    private static final String URL29 = "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=105064&merchantId=439892";

    private static final String DEEPLINK1 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
    private static final String DEEPLINK2 = "ty://?Page=Product&ContentId=1925865";
    private static final String DEEPLINK3 = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";
    private static final String DEEPLINK4 = "ty://?Page=Product&ContentId=1925865&MerchantId=105064";
    private static final String DEEPLINK5 = "ty://?Page=Search&Query=elbise";
    private static final String DEEPLINK6 = "ty://?Page=Search&Query=%C3%BCt%C3%BC";
    private static final String DEEPLINK7 = "ty://?Page=Product&ContentId=1925865&CampaignId=&MerchantId=105064";
    private static final String DEEPLINK8 = "ty://?Page=Search&Query=ğüşiöçĞÜŞİÖÇ";
    private static final String DEEPLINK9 = "ty://?Page=Search&Query=1234";
    private static final String DEEPLINK10 = "ty://?Page=Orders";
    private static final String DEEPLINK11 = "ty://?Page=Orders";
    private static final String DEEPLINK12 = "ty://?Page=Search&QuEry=elbise";
    private static final String DEEPLINK13 = "ty://?PAge=Search&Query=elbise";
    private static final String DEEPLINK14 = "t://?Page=Search&Query=elbise";
    private static final String DEEPLINK15 = "deneme1234";
    private static final String DEEPLINK16 = "ty://?Page=Search";
    private static final String DEEPLINK17 = "ty://?Page=Search&";
    private static final String DEEPLINK18 = "ty://?Page=Search&Query";
    private static final String DEEPLINK19 = "ty://?Page=Search&Query=";
    private static final String DEEPLINK20 = "ty://?Page=Search&deneme=312&Query=222&kerem=333";
    private static final String DEEPLINK21 = "ty://?Page=Product&ContentId=1925865&CampaignId&MerchantId=10506";
    private static final String DEEPLINK22 = "ty://?Page=Product&ContentId&CampaignId=439892&MerchantId=10506";
    private static final String DEEPLINK23 = "ty://?Page=Product&ContentId=1925865&MerchantId&CampaignId=10506";
    private static final String DEEPLINK24 = "ty://?Page=Product&ContentId=1925865&MerchantId=439892&CampaignId=105064";
    private static final String DEEPLINK25 = "ty://?Page=Product&xxx=123&ContentId=1925865&MerchantId=439892&CampaignId=105064&yyy=10";

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

    private static Object[] deeplinkMatcher() {
        return new Object[]{
                new Object[]{DEEPLINK1, URL24},
                new Object[]{DEEPLINK2, URL25},
                new Object[]{DEEPLINK3, URL26},
                new Object[]{DEEPLINK4, URL27},
                new Object[]{DEEPLINK5, URL5},
                new Object[]{DEEPLINK6, URL6},
                new Object[]{DEEPLINK8, URL16},
                new Object[]{DEEPLINK10, TRENDYOL_HOME},
                new Object[]{DEEPLINK11, TRENDYOL_HOME},
                new Object[]{DEEPLINK12, TRENDYOL_HOME},
                new Object[]{DEEPLINK13, TRENDYOL_HOME},
                new Object[]{DEEPLINK14, TRENDYOL_HOME},
                new Object[]{DEEPLINK15, TRENDYOL_HOME},
                new Object[]{DEEPLINK16, TRENDYOL_HOME},
                new Object[]{DEEPLINK17, TRENDYOL_HOME},
                new Object[]{DEEPLINK18, TRENDYOL_HOME},
                new Object[]{DEEPLINK19, TRENDYOL_HOME},
                new Object[]{DEEPLINK20, URL28},
                new Object[]{DEEPLINK21, TRENDYOL_HOME},
                new Object[]{DEEPLINK22, TRENDYOL_HOME},
                new Object[]{DEEPLINK23, TRENDYOL_HOME},
                new Object[]{DEEPLINK24, URL29},
                new Object[]{DEEPLINK25, URL29},
        };
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("urlMatcher")
    public void convertUrlToDeeplink_shouldReturnCorrectDeeplinkForAllUrls(String url, String targetDeeplink) {
        Mockito.doNothing().when(linkConverterLogService).save(Mockito.any(TyLinkConverterLog.class));
        String deeplink = linkConverterService.convertUrlToDeeplink(url, USER);
        assertThat(deeplink).isEqualTo(targetDeeplink);
    }

    @ParameterizedTest
    @MethodSource("deeplinkMatcher")
    public void convertDeeplinkToUrl_shouldReturnCorrectUrlForAllDeeplinks(String deeplink, String targetedUrl) {
        Mockito.doNothing().when(linkConverterLogService).save(Mockito.any(TyLinkConverterLog.class));
        String url = linkConverterService.convertDeeplinkToUrl(deeplink, USER);
        assertThat(url).isEqualTo(targetedUrl);
    }

}