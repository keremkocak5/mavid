package com.kocak.kerem.linkconverter.domain.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LinkConverterResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String deeplink;

}
