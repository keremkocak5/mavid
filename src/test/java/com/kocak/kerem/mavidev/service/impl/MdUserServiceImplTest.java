package com.kocak.kerem.mavidev.service.impl;

import com.kocak.kerem.mavidev.domain.dto.request.DeleteUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PatchUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDTO;
import com.kocak.kerem.mavidev.enums.UserStatus;
import com.kocak.kerem.mavidev.exception.NoUserFoundException;
import com.kocak.kerem.mavidev.exception.UserNotActiveException;
import com.kocak.kerem.mavidev.model.mavidev.MdUser;
import com.kocak.kerem.mavidev.service.MdUserRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MdUserServiceImplTest {

    private static final int ID = 1;
    private static final String USERNAME = "Birisi";
    private static final String SURNAME = "Kocak";
    private static final String NAME = "Kerem";
    private static final String BIRTHPLACE = "Ankara";
    private static final Integer STATUS = UserStatus.AKTIF.getStatus();

    @InjectMocks
    private MdUserServiceImpl mdUserServiceImpl;
    @Mock
    private MdUserRepositoryService mdUserRepositoryService;

    @Test
    public void save_shouldSaveOnce() {
        mdUserServiceImpl.save(Mockito.any(), Mockito.any());
        verify(mdUserRepositoryService, times(1)).save(Mockito.any(), Mockito.any());
    }

    @Test
    public void update_shouldUpdateOnce_whenActiveUserPresent() {
        PatchUserRequestDTO patchUserRequestDTO = new PatchUserRequestDTO();
        MdUser mdUser = new MdUser();
        mdUser.setId(ID);
        mdUser.setStatus(UserStatus.AKTIF.getStatus());
        Optional<MdUser> mdUserOptional = Optional.of(mdUser);
        Mockito.when(mdUserRepositoryService.findById(Mockito.anyInt())).thenReturn(mdUserOptional);

        mdUserServiceImpl.update(patchUserRequestDTO, USERNAME);
        verify(mdUserRepositoryService, times(1)).update(Mockito.any());
    }

    @Test
    public void update_shouldThrowUserNotActiveException_whenUserNotActive() {
        PatchUserRequestDTO patchUserRequestDTO = new PatchUserRequestDTO();
        MdUser mdUser = new MdUser();
        mdUser.setId(ID);
        mdUser.setStatus(UserStatus.PASIF.getStatus());
        Optional<MdUser> mdUserOptional = Optional.of(mdUser);
        Mockito.when(mdUserRepositoryService.findById(Mockito.anyInt())).thenReturn(mdUserOptional);

        Assertions.assertThrows(UserNotActiveException.class, () ->
            mdUserServiceImpl.update(patchUserRequestDTO, USERNAME)
        );
    }

    @Test
    public void update_shouldThrowNoUserFoundException_whenUserNotPresent() {
        PatchUserRequestDTO patchUserRequestDTO = new PatchUserRequestDTO();
        Mockito.when(mdUserRepositoryService.findById(Mockito.anyInt())).thenReturn(Optional.<MdUser>empty());
        Assertions.assertThrows(NoUserFoundException.class, () ->
            mdUserServiceImpl.update(patchUserRequestDTO, USERNAME)
        );
    }

    @Test
    public void delete_shouldDeleteOnce_whenActiveUserPresent() {
        DeleteUserRequestDTO deleteUserRequestDTO = new DeleteUserRequestDTO();
        MdUser mdUser = new MdUser();
        mdUser.setId(ID);
        mdUser.setStatus(UserStatus.AKTIF.getStatus());
        Optional<MdUser> mdUserOptional = Optional.of(mdUser);
        Mockito.when(mdUserRepositoryService.findById(Mockito.anyInt())).thenReturn(mdUserOptional);

        mdUserServiceImpl.delete(deleteUserRequestDTO, USERNAME);
        verify(mdUserRepositoryService, times(1)).update(Mockito.any());
    }

    @Test
    public void delete_shouldThrowUserNotActiveException_whenUserNotActive() {
        DeleteUserRequestDTO deleteUserRequestDTO = new DeleteUserRequestDTO();
        MdUser mdUser = new MdUser();
        mdUser.setId(ID);
        mdUser.setStatus(UserStatus.PASIF.getStatus());
        Optional<MdUser> mdUserOptional = Optional.of(mdUser);
        Mockito.when(mdUserRepositoryService.findById(Mockito.anyInt())).thenReturn(mdUserOptional);

        Assertions.assertThrows(UserNotActiveException.class, () ->
            mdUserServiceImpl.delete(deleteUserRequestDTO, USERNAME)
        );
    }

    @Test
    public void delete_shouldThrowNoUserFoundException_whenUserNotPresent() {
        DeleteUserRequestDTO deleteUserRequestDTO = new DeleteUserRequestDTO();
        Mockito.when(mdUserRepositoryService.findById(Mockito.anyInt())).thenReturn(Optional.<MdUser>empty());
        Assertions.assertThrows(NoUserFoundException.class, () ->
            mdUserServiceImpl.delete(deleteUserRequestDTO, USERNAME)
        );
    }

    @Test
    public void list_shouldReturnGetUserResponseDTO() {
        List<MdUser> mdUsers = new ArrayList<>();
        MdUser mdUser = new MdUser();
        mdUser.setSurname(SURNAME);
        mdUser.setName(NAME);
        mdUser.setBirthCity(BIRTHPLACE);
        mdUser.setId(ID);
        mdUser.setStatus(STATUS);
        mdUsers.add(mdUser);
        Mockito.when(mdUserRepositoryService.findAll()).thenReturn(mdUsers);

        GetUserResponseDTO getUserResponseDTO = mdUserServiceImpl.list();
        assertThat(getUserResponseDTO).isNotNull();
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs()).isNotNull();
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getSurname()).isEqualTo(SURNAME);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getName()).isEqualTo(NAME);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getBirthCity()).isEqualTo(BIRTHPLACE);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getStatus()).isEqualTo(STATUS);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getId()).isEqualTo(ID);
    }

}