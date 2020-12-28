package com.kocak.kerem.linkconverter.service.impl;

import com.kocak.kerem.linkconverter.model.trendyoltest.TyLinkConverterLog;
import com.kocak.kerem.linkconverter.repository.TyLinkConverterLogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LinkConverterLogServiceImplTest {

    @InjectMocks
    private LinkConverterLogServiceImpl linkConverterLogService;
    @Mock
    private TyLinkConverterLogRepository tyLinkConverterLogRepository;

    @Test
    public void save_shouldPersistOnce() {
        TyLinkConverterLog tyLinkConverterLog = new TyLinkConverterLog();
        linkConverterLogService.save(tyLinkConverterLog);
        verify(tyLinkConverterLogRepository, times(1)).save(any());
    }

}