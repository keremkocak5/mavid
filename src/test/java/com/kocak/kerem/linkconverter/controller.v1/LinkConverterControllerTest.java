package com.kocak.kerem.linkconverter.controller.v1;

import com.kocak.kerem.linkconverter.domain.request.dto.LinkConverterRequestDTO;
import com.kocak.kerem.linkconverter.domain.response.dto.LinkConverterResponseDTO;
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
        LinkConverterRequestDTO linkConverterRequestDTO = new LinkConverterRequestDTO();
        linkConverterRequestDTO.setUrl(URL);

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(linkConverterService.convertUrlToDeeplink(URL, USER)).thenReturn(DEEPLINK);

        ResponseEntity<LinkConverterResponseDTO> response = linkConverterController.getDeeplink(linkConverterRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
        assertThat(response.getBody().getDeeplink()).isEqualTo(DEEPLINK);
    }
}
