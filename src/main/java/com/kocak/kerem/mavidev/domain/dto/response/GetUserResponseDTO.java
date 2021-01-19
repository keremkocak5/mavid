package com.kocak.kerem.mavidev.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GetUserResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<GetUserResponseDetailDTO> userResponseDetailDTOs;
}
