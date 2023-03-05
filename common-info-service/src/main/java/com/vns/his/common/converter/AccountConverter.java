package com.vns.his.common.converter;

import com.vns.his.common.dto.AccountDto;
import com.vns.his.common.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    
	
    public Account toEntity (AccountDto dto){
        Account entity = new Account();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setCreatedOn(dto.getCreatedOn());
        entity.setLastLogin(dto.getLastLogin());
        return entity;
    }

    public Account toEntity (AccountDto dto, Account entity){
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setCreatedOn(dto.getCreatedOn());
        entity.setLastLogin(dto.getLastLogin());
        return entity;
    }

    public AccountDto toDto (Account entity){
        AccountDto dto = new AccountDto();
		if (entity.getUserId() != null) {
			dto.setUserId(entity.getUserId());
		}
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setLastLogin(entity.getLastLogin());
        return dto;
    }
}
