package com.kocak.kerem.mavidev.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostUserRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    @Size(min = 1)
    @Size(max = 45)
    private String name;

    @JsonProperty
    @Size(min = 1)
    @Size(max = 45)
    private String surname;

    @JsonProperty
    @Size(min = 2)
    @Size(max = 45)
    private String birthCity;
}