package com.kocak.kerem.mavidev.mapper;

import com.kocak.kerem.mavidev.domain.dto.request.PatchUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDetailDTO;
import com.kocak.kerem.mavidev.enums.UserStatus;
import com.kocak.kerem.mavidev.model.mavidev.MdUser;
import com.kocak.kerem.mavidev.util.DateUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static GetUserResponseDTO mdUsersToGetUserResponseDTO(List<MdUser> mdUsers) {
        GetUserResponseDTO getUserResponseDTO = new GetUserResponseDTO();
        if (Objects.nonNull(mdUsers)) {
            List<GetUserResponseDetailDTO> getUserResponseDetailDTOs = mdUsers.stream().map(user -> {
                GetUserResponseDetailDTO getUserResponseDetailDTO = new GetUserResponseDetailDTO();
                getUserResponseDetailDTO.setBirthCity(user.getBirthCity());
                getUserResponseDetailDTO.setId(user.getId());
                getUserResponseDetailDTO.setName(user.getName());
                getUserResponseDetailDTO.setSurname(user.getSurname());
                getUserResponseDetailDTO.setStatus(user.getStatus());
                return getUserResponseDetailDTO;
            }).collect(Collectors.toList());
            getUserResponseDTO.setUserResponseDetailDTOs(getUserResponseDetailDTOs);
        }
        return getUserResponseDTO;
    }

    public static MdUser postUserRequestDTOToMdUser(PostUserRequestDTO postUserRequestDTO, String createUser) {
        MdUser mdUser = new MdUser();
        mdUser.setName(postUserRequestDTO.getName());
        mdUser.setSurname(postUserRequestDTO.getSurname());
        mdUser.setBirthCity(postUserRequestDTO.getBirthCity());
        mdUser.setCreateDate(DateUtils.getDateInstance());
        mdUser.setCreateUser(createUser);
        mdUser.setStatus(UserStatus.AKTIF.getStatus());
        return mdUser;
    }

    public static MdUser patchMdUserToUpdatedToMdUser(PatchUserRequestDTO patchUserRequestDTO, MdUser mdUser, String updateUser) {
        mdUser.setName(patchUserRequestDTO.getName());
        mdUser.setSurname(patchUserRequestDTO.getSurname());
        mdUser.setBirthCity(patchUserRequestDTO.getBirthCity());
        mdUser.setUpdateDate(DateUtils.getDateInstance());
        mdUser.setUpdateUser(updateUser);
        return mdUser;
    }

    public static MdUser mdUserToDeletedMdUser(MdUser mdUser, String deleteUser) {
        mdUser.setStatus(UserStatus.PASIF.getStatus());
        mdUser.setDeleteDate(DateUtils.getDateInstance());
        mdUser.setDeleteUser(deleteUser);
        return mdUser;
    }

}