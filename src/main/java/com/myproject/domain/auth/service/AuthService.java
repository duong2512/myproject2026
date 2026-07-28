package com.myproject.domain.auth.service;

import com.myproject.domain.auth.dto.RegisterRequest;

public interface AuthService {
    Boolean register(RegisterRequest mail);
}
