package com.kocak.kerem.linkconverter.controller.v1;

import com.kocak.kerem.linkconverter.domain.dto.request.LinkConverterDeeplinkRequestDTO;
import com.kocak.kerem.linkconverter.domain.dto.request.LinkConverterUrlRequestDTO;
import com.kocak.kerem.linkconverter.domain.dto.response.LinkConverterDeeplinkResponseDTO;
import com.kocak.kerem.linkconverter.domain.dto.response.LinkConverterUrlResponseDTO;
import com.kocak.kerem.linkconverter.logging.LogExecutionTime;
import com.kocak.kerem.linkconverter.service.LinkConverterService;
import com.kocak.kerem.linkconverter.util.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * A project to convert URLs to deeplinks and vice versa.
 *
 * @author: Kerem Kocak
 **/
@RestController
@RequestMapping(ApiConstants.LINK_CONVERTER)
@Validated
@Api(value = "Converts URLs to deeplinks and vice versa.")
public class LinkConverterController {

    @Autowired
    private LinkConverterService linkConverterService;

    /**
     * Converts an URL to deeplink. Each request is persisted into the DB.
     * Exceptions are catched in ControllerAdvisor class globally.
     *
     * @param linkConverterUrlRequestDTO
     * @return linkConverterUrlResponseDTO
     */
    @LogExecutionTime
    @ApiOperation(value = "Converts an Url to an deeplink", notes = "Returns a product or search deeplink, if the input is a valid search or product deeplink. Else, it will return a deeplink to Trendyol Home.", response = LinkConverterUrlResponseDTO.class)
    @GetMapping(value = ApiConstants.DEEPLINK, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinkConverterUrlResponseDTO> getDeeplink(@Valid @RequestBody LinkConverterUrlRequestDTO linkConverterUrlRequestDTO, HttpServletRequest request) {
        LinkConverterUrlResponseDTO linkConverterUrlResponseDTO = new LinkConverterUrlResponseDTO();
        Principal principal = request.getUserPrincipal();
        String deeplink = linkConverterService.convertUrlToDeeplink(linkConverterUrlRequestDTO.getUrl(), principal.getName());
        linkConverterUrlResponseDTO.setDeeplink(deeplink);
        return ResponseEntity.ok(linkConverterUrlResponseDTO);
    }

    /**
     * Converts an URL to deeplink. Each request is persisted into the DB.
     * Exceptions are catched in ControllerAdvisor class globally.
     *
     * @param linkConverterDeeplinkRequestDTO
     * @return linkConverterDeeplinkResponseDTO
     */
    @LogExecutionTime
    @ApiOperation(value = "Converts a deeplink to an Url", notes = "Returns a product or search Url, if the input is a valid search or product Url. Else, it will return an Url to Trendyol Home.", response = LinkConverterDeeplinkResponseDTO.class)
    @GetMapping(value = ApiConstants.URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinkConverterDeeplinkResponseDTO> getUrl(@Valid @RequestBody LinkConverterDeeplinkRequestDTO linkConverterDeeplinkRequestDTO, HttpServletRequest request) {
        LinkConverterDeeplinkResponseDTO linkConverterDeeplinkResponseDTO = new LinkConverterDeeplinkResponseDTO();
        Principal principal = request.getUserPrincipal();
        String url = linkConverterService.convertDeeplinkToUrl(linkConverterDeeplinkRequestDTO.getDeeplink(), principal.getName());
        linkConverterDeeplinkResponseDTO.setUrl(url);
        return ResponseEntity.ok(linkConverterDeeplinkResponseDTO);
    }
}