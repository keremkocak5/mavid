package com.kocak.kerem.mavidev.service;

import com.kocak.kerem.mavidev.domain.dto.request.DeleteUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PatchUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDTO;

public interface MdUserService {

    GetUserResponseDTO list();

    void save(PostUserRequestDTO postUserRequestDTO, String createUser);

    void update(PatchUserRequestDTO patchUserRequestDTO, String updateUser);

    void delete(DeleteUserRequestDTO deleteUserRequestDTO, String deleteUser);

}
