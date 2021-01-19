package com.kocak.kerem.mavidev.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetUserResponseDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String surname;

    @JsonProperty
    private String birthCity;

    @JsonProperty
    private Integer status;
}
