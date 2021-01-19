package com.kocak.kerem.mavidev.service.impl;

import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.enums.UserStatus;
import com.kocak.kerem.mavidev.model.mavidev.MdUser;
import com.kocak.kerem.mavidev.repository.MdUserRepository;
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
public class MdUserRepositoryServiceImplTest {

    private static final int ID = 1;
    private static final String USERNAME = "Birisi";
    private static final String SURNAME = "Kocak";
    private static final String NAME = "Kerem";
    private static final String BIRTHPLACE = "Ankara";
    private static final Integer STATUS = UserStatus.AKTIF.getStatus();

    @InjectMocks
    private MdUserRepositoryServiceImpl mdUserRepositoryService;
    @Mock
    private MdUserRepository mdUserRepository;

    @Test
    public void findAll_shouldReturnMdUsers() {
        List<MdUser> mdUsers = new ArrayList<>();
        MdUser mdUser = new MdUser();
        mdUser.setSurname(SURNAME);
        mdUser.setName(NAME);
        mdUser.setBirthCity(BIRTHPLACE);
        mdUser.setId(ID);
        mdUser.setStatus(STATUS);
        mdUsers.add(mdUser);
        Mockito.when(mdUserRepository.findAll()).thenReturn(mdUsers);

        List<MdUser> mdUsers1 = mdUserRepositoryService.findAll();
        assertThat(mdUsers1).isNotNull();
        assertThat(mdUsers1.get(0).getSurname()).isEqualTo(SURNAME);
        assertThat(mdUsers1.get(0).getName()).isEqualTo(NAME);
        assertThat(mdUsers1.get(0).getBirthCity()).isEqualTo(BIRTHPLACE);
        assertThat(mdUsers1.get(0).getStatus()).isEqualTo(STATUS);
        assertThat(mdUsers1.get(0).getId()).isEqualTo(ID);
    }

    @Test
    public void findById_shouldReturnMdUser() {
        MdUser mdUser = new MdUser();
        mdUser.setSurname(SURNAME);
        mdUser.setName(NAME);
        mdUser.setBirthCity(BIRTHPLACE);
        mdUser.setId(ID);
        mdUser.setStatus(STATUS);
        Optional<MdUser> mdUserOptional = Optional.of(mdUser);
        Mockito.when(mdUserRepository.findById(Mockito.anyInt())).thenReturn(mdUserOptional);

        Optional<MdUser> mdUsers1 = mdUserRepositoryService.findById(Mockito.anyInt());
        assertThat(mdUsers1).isNotNull();
        assertThat(mdUsers1).isPresent();
        assertThat(mdUsers1.get().getSurname()).isEqualTo(SURNAME);
        assertThat(mdUsers1.get().getName()).isEqualTo(NAME);
        assertThat(mdUsers1.get().getBirthCity()).isEqualTo(BIRTHPLACE);
        assertThat(mdUsers1.get().getStatus()).isEqualTo(STATUS);
        assertThat(mdUsers1.get().getId()).isEqualTo(ID);
    }

    @Test
    public void update_shouldUpdateOnce() {
        MdUser mdUser = new MdUser();
        mdUserRepositoryService.update(mdUser);
        verify(mdUserRepository, times(1)).save(Mockito.any(MdUser.class));
    }

    @Test
    public void save_shouldUpdateOnce() {
        PostUserRequestDTO postUserRequestDTO = new PostUserRequestDTO();
        mdUserRepositoryService.save(postUserRequestDTO, USERNAME);
        verify(mdUserRepository, times(1)).save(Mockito.any(MdUser.class));
    }

}