package com.kocak.kerem.mavidev.mapper;


import com.kocak.kerem.mavidev.domain.dto.request.PatchUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDTO;
import com.kocak.kerem.mavidev.enums.UserStatus;
import com.kocak.kerem.mavidev.model.mavidev.MdUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMapperTest {

    private static final int ID = 1;
    private static final String USERNAME = "Birisi";
    private static final String SURNAME = "Kocak";
    private static final String NAME = "Kerem";
    private static final String BIRTHPLACE = "Ankara";
    private static final String SURNAME1 = "Kocak1";
    private static final String NAME1 = "Kerem1";
    private static final String BIRTHPLACE1 = "Ankara1";
    private static final Integer STATUS = UserStatus.AKTIF.getStatus();

    @Test
    public void mdUsersToGetUserResponseDTO_shouldReturnGetUserResponseDTO() {
        List<MdUser> mdUsers = new ArrayList<>();
        MdUser mdUser = new MdUser();
        mdUser.setSurname(SURNAME);
        mdUser.setName(NAME);
        mdUser.setBirthCity(BIRTHPLACE);
        mdUser.setId(ID);
        mdUser.setStatus(STATUS);
        mdUsers.add(mdUser);

        GetUserResponseDTO getUserResponseDTO = UserMapper.mdUsersToGetUserResponseDTO(mdUsers);

        assertThat(getUserResponseDTO).isNotNull();
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs()).isNotNull();
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getStatus()).isEqualTo(STATUS);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getName()).isEqualTo(NAME);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getSurname()).isEqualTo(SURNAME);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getBirthCity()).isEqualTo(BIRTHPLACE);
        assertThat(getUserResponseDTO.getUserResponseDetailDTOs().get(0).getId()).isEqualTo(ID);
    }

    @Test
    public void postUserRequestDTOToMdUser_shouldReturnMdUser() {
        PostUserRequestDTO postUserRequestDTO = new PostUserRequestDTO();
        postUserRequestDTO.setBirthCity(BIRTHPLACE);
        postUserRequestDTO.setName(NAME);
        postUserRequestDTO.setSurname(SURNAME);

        MdUser mdUser = UserMapper.postUserRequestDTOToMdUser(postUserRequestDTO, USERNAME);

        assertThat(mdUser).isNotNull();
        assertThat(mdUser.getName()).isEqualTo(NAME);
        assertThat(mdUser.getStatus()).isEqualTo(UserStatus.AKTIF.getStatus());
        assertThat(mdUser.getSurname()).isEqualTo(SURNAME);
        assertThat(mdUser.getBirthCity()).isEqualTo(BIRTHPLACE);
        assertThat(mdUser.getCreateDate()).isNotNull();
        assertThat(mdUser.getCreateUser()).isEqualTo(USERNAME);
        assertThat(mdUser.getUpdateDate()).isNull();
        assertThat(mdUser.getUpdateUser()).isNull();
        assertThat(mdUser.getDeleteDate()).isNull();
        assertThat(mdUser.getDeleteUser()).isNull();
    }

    @Test
    public void patchMdUserToUpdatedToMdUser_shouldReturnMdUser() {
        PatchUserRequestDTO patchUserRequestDTO = new PatchUserRequestDTO();
        patchUserRequestDTO.setBirthCity(BIRTHPLACE1);
        patchUserRequestDTO.setName(NAME1);
        patchUserRequestDTO.setSurname(SURNAME1);
        MdUser mdUser = new MdUser();
        mdUser.setSurname(SURNAME);
        mdUser.setName(NAME);
        mdUser.setBirthCity(BIRTHPLACE);
        mdUser.setId(ID);
        mdUser.setStatus(STATUS);

        MdUser mdUser1 = UserMapper.patchMdUserToUpdatedToMdUser(patchUserRequestDTO, mdUser, USERNAME);

        assertThat(mdUser1).isNotNull();
        assertThat(mdUser1.getName()).isEqualTo(NAME1);
        assertThat(mdUser1.getSurname()).isEqualTo(SURNAME1);
        assertThat(mdUser1.getBirthCity()).isEqualTo(BIRTHPLACE1);
        assertThat(mdUser1.getStatus()).isEqualTo(STATUS);
        assertThat(mdUser1.getId()).isEqualTo(ID);
        assertThat(mdUser.getUpdateDate()).isNotNull();
        assertThat(mdUser.getUpdateUser()).isEqualTo(USERNAME);
    }

    @Test
    public void mdUserToDeletedMdUser_shouldReturnMdUser() {
        MdUser mdUser = new MdUser();
        mdUser.setSurname(SURNAME);
        mdUser.setName(NAME);
        mdUser.setBirthCity(BIRTHPLACE);
        mdUser.setId(ID);
        mdUser.setStatus(STATUS);

        MdUser mdUser1 = UserMapper.mdUserToDeletedMdUser(mdUser, USERNAME);

        assertThat(mdUser1).isNotNull();
        assertThat(mdUser1.getName()).isEqualTo(NAME);
        assertThat(mdUser1.getSurname()).isEqualTo(SURNAME);
        assertThat(mdUser1.getBirthCity()).isEqualTo(BIRTHPLACE);
        assertThat(mdUser1.getStatus()).isEqualTo(UserStatus.PASIF.getStatus());
        assertThat(mdUser1.getId()).isEqualTo(ID);
        assertThat(mdUser.getDeleteDate()).isNotNull();
        assertThat(mdUser.getDeleteUser()).isEqualTo(USERNAME);
    }
}