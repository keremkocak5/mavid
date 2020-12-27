package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.enums.ConversionType;
import com.kocak.kerem.linkconverter.enums.MatchType;
import com.kocak.kerem.linkconverter.exception.LinkConverterLogPersistenceException;
import com.kocak.kerem.linkconverter.model.trendyoltest.TyLinkConverterLog;
import com.kocak.kerem.linkconverter.repository.TyLinkConverterLogRepository;
import com.kocak.kerem.linkconverter.service.LinkConverterLogService;
import com.kocak.kerem.linkconverter.util.Constants;
import com.kocak.kerem.linkconverter.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
public class LinkConverterLogServiceImpl implements LinkConverterLogService {

    @Autowired
    private TyLinkConverterLogRepository tyLinkConverterLogRepository;

    /**
     * Persists the given parameters into the TY_LINK_CONVERTER_LOG table. Remember that apart from the username field,
     * all parameters of this method should be non-null. In the DB layer, the columns corresponding to those parameters are
     * also protected against null values.
     * In case of a persistence failure, the execution of the request is not halted.
     */
    @Transactional
    public void save(String url, String deeplink, ConversionType conversionType, MatchType matchType, String username) {
        if (Objects.isNull(matchType) || Objects.isNull(conversionType) || Objects.isNull(deeplink) || Objects.isNull(url)) {
            log.error("LinkConverterLogServiceImpl.saveLinkConverterTransaction found null field!");
            throw new LinkConverterLogPersistenceException();
        }
        TyLinkConverterLog tyLinkConverterLog = new TyLinkConverterLog();
        tyLinkConverterLog.setDeeplink(deeplink.replace(Constants.ASTERISK, Constants.ASTERISK_REPLACEMENT)); // for security reasons, asterisk is not allowed in db
        tyLinkConverterLog.setUrl(url.replace(Constants.ASTERISK, Constants.ASTERISK_REPLACEMENT)); // for security reasons, asterisk is not allowed in db
        tyLinkConverterLog.setUsername(Objects.isNull(username) ? Constants.NULL_USERNAME : username); // in case the api is exposed to internal use and no security measures are taken, ie no username.
        tyLinkConverterLog.setDatetime(DateUtils.getDateInstance());
        tyLinkConverterLog.setConversionType(conversionType.getType());
        tyLinkConverterLog.setMatchType(matchType.getType());

        try {
            tyLinkConverterLogRepository.save(tyLinkConverterLog);
        } catch (Exception e) {
            log.error("LinkConverterLogServiceImpl.saveLinkConverterTransaction : {}", e.getMessage());
        }
    }
}