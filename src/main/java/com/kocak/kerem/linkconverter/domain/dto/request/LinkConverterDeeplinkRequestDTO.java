package com.kocak.kerem.linkconverter.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkConverterDeeplinkRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    @NotNull()
    @Size(min = 10)
    @Size(max = 450)
    private String deeplink;

}