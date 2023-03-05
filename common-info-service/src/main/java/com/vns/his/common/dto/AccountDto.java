package com.vns.his.common.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    //Thông tin cơ bản
    private Integer userId;
	private String username;
	private String password;
	private String email;
	private Date createdOn;
	private Date lastLogin;
}
