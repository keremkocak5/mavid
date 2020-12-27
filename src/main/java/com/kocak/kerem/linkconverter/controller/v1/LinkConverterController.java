package com.kocak.kerem.linkconverter.controller.v1;

import com.kocak.kerem.linkconverter.domain.request.dto.LinkConverterRequestDTO;
import com.kocak.kerem.linkconverter.domain.response.dto.LinkConverterResponseDTO;
import com.kocak.kerem.linkconverter.logging.LogExecutionTime;
import com.kocak.kerem.linkconverter.service.LinkConverterService;
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
@RequestMapping("/api/v1/linkconverter")
@Validated
@Api(value = "Converts URLs to deeplinks and vice versa.")
public class LinkConverterController {

    @Autowired
    private LinkConverterService linkConverterService;

    /**
     * This web service converts an URL to deeplink. Each request is persisted into the DB.
     * Exceptions are catched in ControllerAdvisor class globally.
     *
     * @param linkConverterRequestDTO
     * @return linkConverterResponseDTO
     */
    @LogExecutionTime
    @ApiOperation(value = "Converts an URL to Deeplink", notes = "Returns a product or search deeplink, if the input is a valid search or product URL. Else, it will return a deeplink to Trendyol Home.", response = LinkConverterResponseDTO.class)
    @GetMapping(value = "/deeplink", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinkConverterResponseDTO> getDeeplink(@Valid @RequestBody LinkConverterRequestDTO linkConverterRequestDTO, HttpServletRequest request) {
        LinkConverterResponseDTO linkConverterResponseDTO = new LinkConverterResponseDTO();
        Principal principal = request.getUserPrincipal();
        String deeplink = linkConverterService.convertUrlToDeeplink(linkConverterRequestDTO.getUrl(), principal.getName());
        linkConverterResponseDTO.setDeeplink(deeplink);
        return ResponseEntity.ok(linkConverterResponseDTO);
    }
}