package com.clover.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Account_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acc_Id;

    private String category;

    private String bankName;

    private String branch;

    private String accountNumber;

    private String ifscCode;

    private Boolean accStatus;
    @OneToOne
    private User user;


}
