package com.kocak.kerem.linkconverter.mapper;

import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.LinkConverterLogNullValueException;
import com.kocak.kerem.linkconverter.model.trendyoltest.TyLinkConverterLog;
import com.kocak.kerem.linkconverter.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TyLinkConverterLogMapperTest {

    private static final MatchType MATCH_TYPE_PRODUCT = MatchType.PRODUCT;
    private static final String DEEPLINK = "deeplink";
    private static final String URL = "url";
    private static final String USER = "USER";
    private static final String URL_ASTERISK = "ur*l";
    private static final String DEEPLINK_ASTERISK = "deepli*nk";
    private static final String DEFAULT_USER = Constants.NULL_USERNAME;
    private static final ConversionType CONVERSION_TYPE = ConversionType.TO_DEEPLINK;

    @Test
    public void mapTyLinkConverterLog_shouldReturnTyLinkConverterLog() {
        TyLinkConverterLog tyLinkConverterLog = TyLinkConverterLogMapper.mapTyLinkConverterLog(URL, DEEPLINK, CONVERSION_TYPE, MATCH_TYPE_PRODUCT, USER);
        assertThat(tyLinkConverterLog).isNotNull();
        assertThat(tyLinkConverterLog.getConversionType()).isEqualTo(CONVERSION_TYPE.getType());
        assertThat(tyLinkConverterLog.getDatetime()).isNotNull();
        assertThat(tyLinkConverterLog.getDeeplink()).isEqualTo(DEEPLINK);
        assertThat(tyLinkConverterLog.getUrl()).isEqualTo(URL);
        assertThat(tyLinkConverterLog.getUsername()).isEqualTo(USER);
        assertThat(tyLinkConverterLog.getMatchType()).isEqualTo(MATCH_TYPE_PRODUCT.getType());
    }

    @Test
    public void mapTyLinkConverterLog_shouldReturnTyLinkConverterLog_whenDataIsDirty() {
        TyLinkConverterLog tyLinkConverterLog = TyLinkConverterLogMapper.mapTyLinkConverterLog(URL_ASTERISK, DEEPLINK_ASTERISK, CONVERSION_TYPE, MATCH_TYPE_PRODUCT, null);
        assertThat(tyLinkConverterLog).isNotNull();
        assertThat(tyLinkConverterLog.getConversionType()).isEqualTo(CONVERSION_TYPE.getType());
        assertThat(tyLinkConverterLog.getDatetime()).isNotNull();
        assertThat(tyLinkConverterLog.getDeeplink()).isEqualTo(DEEPLINK);
        assertThat(tyLinkConverterLog.getUrl()).isEqualTo(URL);
        assertThat(tyLinkConverterLog.getUsername()).isEqualTo(DEFAULT_USER);
        assertThat(tyLinkConverterLog.getMatchType()).isEqualTo(MATCH_TYPE_PRODUCT.getType());
    }

    @Test
    public void mapTyLinkConverterLog_shouldThrowExceptionWhenUrlNull() {
        Assertions.assertThrows(LinkConverterLogNullValueException.class, () -> TyLinkConverterLogMapper.mapTyLinkConverterLog(null, DEEPLINK, CONVERSION_TYPE, MATCH_TYPE_PRODUCT, USER));
    }

    @Test
    public void mapTyLinkConverterLog_shouldThrowExceptionWhenDeeplinkNull() {
        Assertions.assertThrows(LinkConverterLogNullValueException.class, () -> TyLinkConverterLogMapper.mapTyLinkConverterLog(URL, null, CONVERSION_TYPE, MATCH_TYPE_PRODUCT, USER));
    }

    @Test
    public void mapTyLinkConverterLog_shouldThrowExceptionWhenConversionTypeNull() {
        Assertions.assertThrows(LinkConverterLogNullValueException.class, () -> TyLinkConverterLogMapper.mapTyLinkConverterLog(URL, DEEPLINK, null, MATCH_TYPE_PRODUCT, USER));
    }

    @Test
    public void mapTyLinkConverterLog_shouldThrowExceptionWhenMatchTypeNull() {
        Assertions.assertThrows(LinkConverterLogNullValueException.class, () -> TyLinkConverterLogMapper.mapTyLinkConverterLog(URL, DEEPLINK, CONVERSION_TYPE, null, USER));
    }

}