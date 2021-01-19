package com.kocak.kerem.mavidev.service.impl;

import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.mapper.UserMapper;
import com.kocak.kerem.mavidev.model.mavidev.MdUser;
import com.kocak.kerem.mavidev.repository.MdUserRepository;
import com.kocak.kerem.mavidev.service.MdUserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MdUserRepositoryServiceImpl implements MdUserRepositoryService {

    @Autowired
    private MdUserRepository mdUserRepository;

    @Override
    public List<MdUser> findAll() {
        return mdUserRepository.findAll();
    }

    @Override
    public Optional<MdUser> findById(int id) {
        return mdUserRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(PostUserRequestDTO postUserRequestDTO, String createUser) {
        MdUser mdUser = UserMapper.postUserRequestDTOToMdUser(postUserRequestDTO, createUser);
        mdUserRepository.save(mdUser);
    }

    @Override
    @Transactional
    public void update(MdUser mdUser) {
        mdUserRepository.save(mdUser);
    }

}