package com.myproject.domain.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AuthUser implements Serializable {
    @Column(name = "username")
    @Schema(description = "Tên đăng nhập")
    private String userName;

    @Column(name = "email")
    @Schema(description = "Email")
    private String email;

    @Column(name = "fullname")
    @Schema(description = "Tên đầy đủ")
    private String fullName;

    @Column(name = "status")
    @Schema(description = "Trạng thái")
    private Integer status;

    @Column(name = "must_change_password")
    @Schema(description = "Mật khẩu mặc định đã được đổi chưa")
    private String mustChangePassword;

    @Column(name = "gender")
    @Schema(description = "Giới tính (0: Nữ, 1: Nam)")
    private Integer gender;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @Schema(description = "Sinh nhật")
    private Date birthday;
}
