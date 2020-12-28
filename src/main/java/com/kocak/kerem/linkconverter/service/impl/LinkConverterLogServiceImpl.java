package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.model.trendyoltest.TyLinkConverterLog;
import com.kocak.kerem.linkconverter.repository.TyLinkConverterLogRepository;
import com.kocak.kerem.linkconverter.service.LinkConverterLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LinkConverterLogServiceImpl implements LinkConverterLogService {

    @Autowired
    private TyLinkConverterLogRepository tyLinkConverterLogRepository;

    /**
     * Persists the given parameter into the TY_LINK_CONVERTER_LOG table.
     */
    @Override
    @Transactional
    public void save(TyLinkConverterLog tyLinkConverterLog) {
        try {
            tyLinkConverterLogRepository.save(tyLinkConverterLog);
        } catch (Exception e) {
            log.error("LinkConverterLogServiceImpl.save : {}", e.getMessage());
        }
    }
}