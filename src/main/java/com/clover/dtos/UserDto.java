package com.clover.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private Integer userId;

    @NotEmpty @NotNull @Size(min = 2, max = 30, message = "firstName should be in rage of 2 to 30")
    private String firstName;

    @NotEmpty @NotNull @Size(min = 2, max = 30, message = "lastName should be in rage of 2 to 30")
    private String lastName;

    @NotEmpty @NotNull @Pattern(regexp = "^[6789]\\d{9}$",message = "enter valid mobile number (10 digit number starts with 6/7/8/9")
    private String mobile;

    @NotEmpty @NotNull @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "enter valid pan card like : BACBA1256D")
    private String panNumber;

    @NotEmpty @NotNull @Pattern(regexp = "[0-9]{12}", message = "enter 12 digit valid aadhar number")
    private String aadharNumber;

    private LocalDate dob;

    @NotEmpty @NotNull @Size(min = 2, max = 50, message = "city should be in rage of 2 to 50")
    private String city;

    @NotEmpty @NotNull @Size(min = 2, max = 20, message = "firstName should be in rage of 2 to 20")
    private String state;

    @NotEmpty @NotNull @Pattern(regexp = "^(.+)@(\\S+)$",message = "enter valid email address like : username@domain.com")
    private String email;

    @NotEmpty @NotNull
    private String password;
}
