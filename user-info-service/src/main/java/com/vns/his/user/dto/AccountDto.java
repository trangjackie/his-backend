package com.vns.his.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    //Thông tin cơ bản
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
}
