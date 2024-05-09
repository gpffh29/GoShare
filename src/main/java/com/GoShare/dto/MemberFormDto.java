package com.GoShare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
public class MemberFormDto {
    private String name;
    private String email;
    private String password;
    private String phone;
}
