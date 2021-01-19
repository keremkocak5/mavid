package com.kocak.kerem.mavidev.service.impl;

import com.kocak.kerem.mavidev.domain.dto.request.DeleteUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PatchUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDTO;
import com.kocak.kerem.mavidev.enums.UserStatus;
import com.kocak.kerem.mavidev.exception.NoUserFoundException;
import com.kocak.kerem.mavidev.exception.UserNotActiveException;
import com.kocak.kerem.mavidev.mapper.UserMapper;
import com.kocak.kerem.mavidev.model.mavidev.MdUser;
import com.kocak.kerem.mavidev.service.MdUserRepositoryService;
import com.kocak.kerem.mavidev.service.MdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MdUserServiceImpl implements MdUserService {

    @Autowired
    private MdUserRepositoryService mdUserRepositoryService;

    @Override
    public void save(PostUserRequestDTO postUserRequestDTO, String createUser) {
        mdUserRepositoryService.save(postUserRequestDTO, createUser);
    }

    @Override
    public void update(PatchUserRequestDTO patchUserRequestDTO, String updateUser) {
        Optional<MdUser> mdUserOptional = mdUserRepositoryService.findById(patchUserRequestDTO.getId());
        if (mdUserOptional.isPresent() && UserStatus.AKTIF.getStatus() == (mdUserOptional.get().getStatus())) {
            MdUser mdUser = UserMapper.patchMdUserToUpdatedToMdUser(patchUserRequestDTO, mdUserOptional.get(), updateUser);
            mdUserRepositoryService.update(mdUser);
        } else if (mdUserOptional.isPresent() && UserStatus.PASIF.getStatus() == (mdUserOptional.get().getStatus())) {
            throw new UserNotActiveException();
        } else {
            throw new NoUserFoundException();
        }
    }

    @Override
    public void delete(DeleteUserRequestDTO deleteUserRequestDTO, String deleteUser) {
        Optional<MdUser> mdUserOptional = mdUserRepositoryService.findById(deleteUserRequestDTO.getId());
        if (mdUserOptional.isPresent() && UserStatus.AKTIF.getStatus() == (mdUserOptional.get().getStatus())) {
            MdUser mdUser = UserMapper.mdUserToDeletedMdUser(mdUserOptional.get(), deleteUser);
            mdUserRepositoryService.update(mdUser);
        } else if (mdUserOptional.isPresent() && UserStatus.PASIF.getStatus() == (mdUserOptional.get().getStatus())) {
            throw new UserNotActiveException();
        } else {
            throw new NoUserFoundException();
        }
    }

    @Override
    public GetUserResponseDTO list() {
        List<MdUser> mdUsers = mdUserRepositoryService.findAll();
        return UserMapper.mdUsersToGetUserResponseDTO(mdUsers);
    }
}