package com.clover.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDto {

    private String category;

    private String bankName;

    private String branch;

    private String accountNumber;

    private String ifscCode;

    private Boolean accStatus;

    private UserDto user;


}
