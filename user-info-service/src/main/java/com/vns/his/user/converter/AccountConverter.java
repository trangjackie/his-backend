package com.vns.his.user.converter;

import com.vns.his.user.dto.AccountDto;
import com.vns.his.user.entity.Account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    
	
    public Account toEntity (AccountDto dto){
        Account entity = new Account();
        entity.setUsername(dto.getUsername());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }

    public Account toEntity (AccountDto dto, Account entity){
        entity.setUsername(dto.getUsername());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName()); 
        return entity;
    }

    public AccountDto toDto (Account entity){
        AccountDto dto = new AccountDto();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
        dto.setUsername(entity.getUsername());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}
