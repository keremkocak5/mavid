package com.kocak.kerem.mavidev.controller.v1;


import com.kocak.kerem.mavidev.domain.dto.request.DeleteUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.GetUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PatchUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDetailDTO;
import com.kocak.kerem.mavidev.service.MdUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MdUserControllerTest {

    private static final String CITY = "Ankara";
    private static final String NAME = "Kerem";
    private static final String SURNAME = "Kocak";
    private static final String USER = "user";
    private static final String ROLE = "Manager";
    private static final HttpStatus STATUS_OK = HttpStatus.OK;
    private static final HttpStatus STATUS_CONFLICT = HttpStatus.CONFLICT;


    @InjectMocks
    private MdUserController mdUserController;
    @Mock
    private MdUserService mdUserService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private Principal principal;

    @Test
    public void getUser_shouldReturnGetUserResponseDTO() {
        GetUserRequestDTO getUserRequestDTO = new GetUserRequestDTO();
        GetUserResponseDTO getUserResponseDTO = new GetUserResponseDTO();
        ArrayList<GetUserResponseDetailDTO> getUserResponseDetailDTOS = new ArrayList<>();
        GetUserResponseDetailDTO getUserResponseDetailDTO = new GetUserResponseDetailDTO();
        getUserResponseDetailDTO.setBirthCity(CITY);
        getUserResponseDetailDTO.setSurname(SURNAME);
        getUserResponseDetailDTO.setName(NAME);
        getUserResponseDetailDTOS.add(getUserResponseDetailDTO);
        getUserResponseDTO.setUserResponseDetailDTOs(getUserResponseDetailDTOS);

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(mdUserService.list()).thenReturn(getUserResponseDTO);

        ResponseEntity<GetUserResponseDTO> response = mdUserController.getUser(getUserRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUserResponseDetailDTOs()).isNotNull();
        assertThat(response.getBody().getUserResponseDetailDTOs().get(0).getBirthCity()).isEqualTo(CITY);
        assertThat(response.getBody().getUserResponseDetailDTOs().get(0).getName()).isEqualTo(NAME);
        assertThat(response.getBody().getUserResponseDetailDTOs().get(0).getSurname()).isEqualTo(SURNAME);
    }

    @Test
    public void postUser_shouldReturnResponseEntity() {
        PostUserRequestDTO postUserRequestDTO = new PostUserRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.doNothing().when(mdUserService).save(Mockito.any(), Mockito.any());

        ResponseEntity<GetUserResponseDTO> response = mdUserController.postUser(postUserRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void patchUser_shouldReturnResponseEntity() {
        PatchUserRequestDTO patchUserRequestDTO = new PatchUserRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.doNothing().when(mdUserService).save(Mockito.any(), Mockito.any());

        ResponseEntity<GetUserResponseDTO> response = mdUserController.patchUser(patchUserRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void deleteUser_shouldReturnResponseEntity() {
        DeleteUserRequestDTO deleteUserRequestDTO = new DeleteUserRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.doNothing().when(mdUserService).save(Mockito.any(), Mockito.any());

        ResponseEntity<GetUserResponseDTO> response = mdUserController.deleteUser(deleteUserRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }

}