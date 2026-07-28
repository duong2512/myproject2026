package com.myproject.global.util;

import com.myproject.global.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailEx {
    @Autowired
    private JavaMailSender mailSender;

    public void sendTempPasswordEmail(String toEmail, String fullName, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Cấp mật khẩu đăng nhập tài khoản");
        message.setText(
                "Xin chào " + fullName + ",\n\n" +
                        "Tài khoản của bạn đã được tạo thành công.\n" +
                        "Mật khẩu tạm thời của bạn là: " + tempPassword + "\n\n" +
                        "Vui lòng đăng nhập và đổi mật khẩu ngay trong lần đăng nhập đầu tiên.\n" +
                        "Không chia sẻ mật khẩu này cho bất kỳ ai.\n\n" +
                        "Trân trọng."
        );
        try {
            this.mailSender.send(message);
        } catch (Exception ex) {
            log.error("Send mail error: {}", toEmail, ex);
            throw new BadRequestException("Gửi email thất bại, vui lòng thử đăng ký lại sau.");
        }

    }
}
