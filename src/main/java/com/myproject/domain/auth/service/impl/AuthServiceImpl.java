package com.myproject.domain.auth.service.impl;

import com.myproject.domain.auth.dto.RegisterRequest;
import com.myproject.domain.auth.model.AuthUser;
import com.myproject.domain.auth.repository.AuthRepository;
import com.myproject.domain.auth.service.AuthService;
import com.myproject.global.exception.BadRequestException;
import com.myproject.global.util.MailEx;
import com.myproject.global.util.PasswordGeneratorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    private final MailEx mailEx;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Boolean register(RegisterRequest request) {
        // validate account
        this.validateRegister(request);
        // Temp password
        String tempPassword = PasswordGeneratorUtils.generateTempPassword(10);
        // insert account
        AuthUser result = this.authRepository.register(request, this.passwordEncoder.encode(tempPassword));
        if (result == null) {
            throw new BadRequestException("Không thêm mới được vào bảng auth_user");
        }

        // send mail temp password
        this.mailEx.sendTempPasswordEmail(request.getEmail(), request.getFullName(), tempPassword);

        return true;
    }

    private void validateRegister(RegisterRequest request) {
        if (this.authRepository.checkUsername(request.getUserName())) {
            throw new BadRequestException("Tên đăng nhập đã được sử dụng. Hãy thử tên đăng nhập khác.");
        }
        if (this.authRepository.checkMailExists(request.getEmail())) {
            throw new BadRequestException("Email đã được sử dụng. Hãy thử email khác.");
        }
    }
}
