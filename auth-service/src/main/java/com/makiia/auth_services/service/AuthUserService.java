package com.makiia.auth_services.service;
import com.makiia.auth_services.dto.AuthUserDto;
import com.makiia.auth_services.dto.NewUserDto;
import com.makiia.auth_services.dto.RequestDto;
import com.makiia.auth_services.dto.TokenDto;
import com.makiia.auth_services.entity.AuthUser;
import com.makiia.auth_services.repository.AuthUserRepository;
import com.makiia.auth_services.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthUserService {
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    public AuthUser save(NewUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(dto.getUserName())
                .password(password)
                .role(dto.getRole())
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if(!user.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token, RequestDto dto) {
        if(!jwtProvider.validate(token,dto))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUserName(username).isPresent())
            return null;
        return new TokenDto(token);
    }
}
