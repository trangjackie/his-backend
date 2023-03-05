package com.vns.his.user.service.interfaces;

import com.vns.his.user.dto.AccountDto;
import com.vns.his.user.entity.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface AccountService {
	List<AccountDto> findAll(Pageable pageable);

	AccountDto findById(Integer id);

	ResponseEntity<Object> save(AccountDto AccountDto);

	void delete(Integer id);	

	long count();

	Integer getNewId();
}
