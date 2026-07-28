package com.myproject.domain.auth.repository;

import com.myproject.domain.auth.dto.RegisterRequest;
import com.myproject.domain.auth.model.AuthUser;
import com.myproject.global.dao.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class AuthRepository extends BaseRepositoryImpl<Object, Object> {

    public Boolean checkMailExists(String mail) {
        Integer result = excuteReturnDataUsingSp(
                0,
                "pkg__sp_check_email_exists",
                mail
        );
        return Objects.equals(result, 1);
    }

    public Boolean checkUsername(String username) {
        Integer result = excuteReturnDataUsingSp(
                0,
                "pkg__sp_check_username_exists",
                username
        );
        return Objects.equals(result, 1);
    }

    public AuthUser register(RegisterRequest request, String passwordDefault) {
        return excuteObjectUsingSp(
                AuthUser.class,
                "pkg__sp_insert_auth_user",
                request.getUserName(),
                request.getFullName(),
                request.getEmail(),
                request.getBirthday(),
                request.getGender(),
                passwordDefault
        );
    }
}
