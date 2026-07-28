package com.myproject.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.myproject.global.annotations.CompareField;
import com.myproject.global.annotations.EqualField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualField(baseField = "oldPassword", matchField = "newPassword", isNot = true, message = "[Mật khẩu mới] và [Mật khẩu cũ] không được trùng nhau")
@EqualField(baseField = "newPassword", matchField = "confirmPassword", message = "[Xác nhận mật khẩu] không khớp [Mật khẩu mới]")
@CompareField(baseField = "newPassword", matchField = "username", isNot = true, message = "[Mật khẩu mới] không được có phần trùng với [Tên tài khoản]")
public class ChangePasswordRequestDto {

    @Schema(title = "Tên đăng nhập")
    @NotBlank
    @Size(min = 6, max = 50)
    private String username;

    @Schema(title = "Mật khẩu")
    @NotBlank
    @Size(min = 8, max = 50)
    private String oldPassword;

    @Schema(title = "Mật khẩu mới")
    @NotBlank
    @Size(min = 8, max = 50)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!*()_\\-]).*$",
            message = "Mật khẩu phải có chữ hoa, chữ thường, số và ký tự đặc biệt"
    )
    private String newPassword;

    @Schema(title = "Xác nhận mật khẩu")
    @NotBlank
    @Size(min = 8, max = 50)
    private String confirmPassword;
}
