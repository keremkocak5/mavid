package com.kocak.kerem.linkconverter.controller.v1;

import com.kocak.kerem.linkconverter.domain.dto.request.LinkConverterDeeplinkRequestDTO;
import com.kocak.kerem.linkconverter.domain.dto.request.LinkConverterUrlRequestDTO;
import com.kocak.kerem.linkconverter.domain.dto.response.LinkConverterDeeplinkResponseDTO;
import com.kocak.kerem.linkconverter.domain.dto.response.LinkConverterUrlResponseDTO;
import com.kocak.kerem.linkconverter.service.LinkConverterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LinkConverterControllerTest {

    private static final String DEEPLINK = "deeplink";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String ROLE = "Manager";
    private static final HttpStatus STATUS_OK = HttpStatus.OK;

    @InjectMocks
    private LinkConverterController linkConverterController;
    @Mock
    private LinkConverterService linkConverterService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private Principal principal;

    @Test
    public void getDeeplink_shouldReturnLinkConverterResponseDTO() {
        LinkConverterUrlRequestDTO linkConverterRequestDTO = new LinkConverterUrlRequestDTO();
        linkConverterRequestDTO.setUrl(URL);

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(linkConverterService.convertUrlToDeeplink(URL, USER)).thenReturn(DEEPLINK);

        ResponseEntity<LinkConverterUrlResponseDTO> response = linkConverterController.getDeeplink(linkConverterRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDeeplink()).isEqualTo(DEEPLINK);
    }

    @Test
    public void getUrl_shouldReturnLinkConverterResponseDTO() {
        LinkConverterDeeplinkRequestDTO linkConverterRequestDTO = new LinkConverterDeeplinkRequestDTO();
        linkConverterRequestDTO.setDeeplink(URL);

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(linkConverterService.convertDeeplinkToUrl(URL, USER)).thenReturn(DEEPLINK);

        ResponseEntity<LinkConverterDeeplinkResponseDTO> response = linkConverterController.getUrl(linkConverterRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUrl()).isEqualTo(DEEPLINK);
    }
}