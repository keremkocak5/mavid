package com.kocak.kerem.mavidev.service;

import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.model.mavidev.MdUser;

import java.util.List;
import java.util.Optional;

public interface MdUserRepositoryService {

    List<MdUser> findAll();

    Optional<MdUser> findById(int id);

    void save(PostUserRequestDTO postUserRequestDTO, String createUser);

    void update(MdUser mdUser);

}
