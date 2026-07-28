package com.myproject.domain.auth.controller;

import com.myproject.domain.auth.dto.RegisterRequest;
import com.myproject.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;

@Tag(name = "API Authentication + Authorized")
@RestController
@RequestMapping("/api/auth/")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @ResponseStatus
    @PostMapping("dang-nhap")
    public ResponseEntity<Boolean> dangNhap(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(this.service.register(request));
    }

}
