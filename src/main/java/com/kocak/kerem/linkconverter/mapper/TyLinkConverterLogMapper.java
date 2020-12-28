package com.kocak.kerem.linkconverter.mapper;

import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.LinkConverterLogNullValueException;
import com.kocak.kerem.linkconverter.model.trendyoltest.TyLinkConverterLog;
import com.kocak.kerem.linkconverter.util.Constants;
import com.kocak.kerem.linkconverter.util.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class TyLinkConverterLogMapper {

    private TyLinkConverterLogMapper() {
    }

    /**
     * Creates and returns a TyLinkConverterLog instance.
     * Apart from the username field, all parameters of this method must be non-null.
     * If username is null, a default value is set as an username.
     * If url or deeplink have asterisk characters, they are omitted.
     */
    public static TyLinkConverterLog mapTyLinkConverterLog(String url, String deeplink, ConversionType conversionType, MatchType matchType, String username) {
        if (Objects.isNull(matchType) || Objects.isNull(conversionType) || Objects.isNull(deeplink) || Objects.isNull(url)) {
            log.error("LinkConverterLogServiceImpl.saveLinkConverterTransaction found null field!");
            throw new LinkConverterLogNullValueException();
        }
        TyLinkConverterLog tyLinkConverterLog = new TyLinkConverterLog();
        tyLinkConverterLog.setDeeplink(deeplink.replace(Constants.ASTERISK, Constants.ASTERISK_REPLACEMENT)); // for security reasons, asterisk is not allowed in db
        tyLinkConverterLog.setUrl(url.replace(Constants.ASTERISK, Constants.ASTERISK_REPLACEMENT)); // for security reasons, asterisk is not allowed in db
        tyLinkConverterLog.setUsername(Objects.isNull(username) ? Constants.NULL_USERNAME : username); // in case the api is exposed to internal use and no security measures are taken, ie no username.
        tyLinkConverterLog.setDatetime(DateUtils.getDateInstance());
        tyLinkConverterLog.setConversionType(conversionType.getType());
        tyLinkConverterLog.setMatchType(matchType.getType());
        return tyLinkConverterLog;
    }
}