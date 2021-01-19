package com.kocak.kerem.mavidev.controller.v1;

import com.kocak.kerem.mavidev.domain.dto.request.DeleteUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.GetUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PatchUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.request.PostUserRequestDTO;
import com.kocak.kerem.mavidev.domain.dto.response.GetUserResponseDTO;
import com.kocak.kerem.mavidev.logging.LogExecutionTime;
import com.kocak.kerem.mavidev.service.MdUserService;
import com.kocak.kerem.mavidev.util.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 *
 *
 * @author: Kerem Kocak
 **/
@RestController
@RequestMapping(ApiConstants.MD)
@Validated
@Api(value = "Kullanici operasyonlari icin API")
public class MdUserController {

    @Autowired
    private MdUserService mdUserService;

    @LogExecutionTime
    @ApiOperation(value = "Kullanicilari Getir", notes = "Tum kullanicilari getirir", response = GetUserResponseDTO.class)
    @GetMapping(value = ApiConstants.USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponseDTO> getUser(@Valid @RequestBody GetUserRequestDTO getUserRequestDTO, HttpServletRequest request) {
        GetUserResponseDTO dto = mdUserService.list();
        return ResponseEntity.ok(dto);
    }

    @LogExecutionTime
    @ApiOperation(value = "Kullanici Yarat", notes = "Isim, soyisim, dogum yeri bilgileri ile kullanici yaratir", response = ResponseEntity.class)
    @PostMapping(value = ApiConstants.USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postUser(@Valid @RequestBody PostUserRequestDTO postUserRequestDTO, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        mdUserService.save(postUserRequestDTO, principal.getName());
        return ResponseEntity.ok().build();
    }

    @LogExecutionTime
    @ApiOperation(value = "Kullanici Guncelle", notes = "ID numarasi ile tespit edilen kullanicinin isim, soyisim, dogum yeri bilgilerini gunceller", response = ResponseEntity.class)
    @PatchMapping(value = ApiConstants.USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity patchUser(@Valid @RequestBody PatchUserRequestDTO patchUserRequestDTO, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        mdUserService.update(patchUserRequestDTO, principal.getName());
        return ResponseEntity.ok().build();
    }

    @LogExecutionTime
    @ApiOperation(value = "Kullanici Sil", notes = "ID numarasi ile tespit edilen kullaniciyi siler", response = ResponseEntity.class)
    @DeleteMapping(value = ApiConstants.USER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@Valid @RequestBody DeleteUserRequestDTO deleteUserRequestDTO, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        mdUserService.delete(deleteUserRequestDTO, principal.getName());
        return ResponseEntity.ok().build();
    }

}