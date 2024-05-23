package com.GoShare.codefAPI;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ApiDto {

    private String loginUserName;

    private String identity;

    private String phoneNo;

    private String birthDate;

    private String licenseNo01;

    private String licenseNo02;

    private String licenseNo03;

    private String licenseNo04;

    private String serialNo;

}
