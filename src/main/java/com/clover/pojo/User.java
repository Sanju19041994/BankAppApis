package com.clover.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "User_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;

    private String lastName;

    private String mobile;

    private String panNumber;

    private String aadharNumber;

    private LocalDate dob;

    private String city;

    private String state;

    private String email;

    private String password;


}
