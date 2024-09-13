package org.ryanchoi.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.ryanchoi.user_service.vo.ResponseOrder;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String email;

    private String name;

    private String pwd;

    private String userId;

    private Date createdAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;

}
