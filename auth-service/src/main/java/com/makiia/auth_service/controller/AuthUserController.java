package com.makiia.auth_service.controller;

import com.makiia.auth_service.dto.AuthUserDto;
import com.makiia.auth_service.dto.TokenDto;
import com.makiia.auth_service.entity.AuthUser;
import com.makiia.auth_service.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto){
        TokenDto tokenDto = authUserService.login(dto);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }
    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        TokenDto tokenDto = authUserService.validate(token);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }
    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto dto){
        AuthUser authUser = authUserService.save(dto);
        if(authUser == null ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }
}
