package com.myproject.domain.auth.dto;

import com.myproject.global.annotations.ValueIn;
import com.myproject.global.constants.Regex;
import com.myproject.global.util.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

import static com.myproject.global.util.DateUtils.DATE_TIME_ISO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {

    @Schema(title = "Tên đăng nhập")
    @NotEmpty
    @Size(min= 6, max = 50)
    private String userName;

    @Schema(title = "Email")
    @NotEmpty
    @Size(max = 100)
    @Email(message = "Email không đúng định dạng")
    private String email;

    @Schema(title = "Tên đầy đủ")
    @NotEmpty
    @Size(max = 200)
    private String fullName;

    @Schema(description = "Giới tính")
    @NotNull
    @ValueIn(values = {0, 1}, message = "Giới tính chỉ nhận giá trị 0 hoặc 1")
    private Integer gender;

    @Schema(description = "Ngày sinh")
    @Pattern(regexp = Regex.REGEX_ISO_DATE, message = "Ngày sinh không đúng định dạng (example = 2026-07-24T00:00:00.000Z)")
    @NotNull
    private String birthday;

    public Date getBirthday() {
        return this.birthday != null ? DateUtils.stringToDate(this.birthday, DATE_TIME_ISO) : null;
    }
}
