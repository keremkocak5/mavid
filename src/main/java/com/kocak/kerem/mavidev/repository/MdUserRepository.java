package com.kocak.kerem.mavidev.repository;

import com.kocak.kerem.mavidev.model.mavidev.MdUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MdUserRepository extends CrudRepository<MdUser, Integer> {

    Optional<MdUser> findById(int id);

    List<MdUser> findAll();

}