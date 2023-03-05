package com.vns.his.user.service.impl;

import com.vns.his.user.converter.AccountConverter;
import com.vns.his.user.dto.AccountDto;
import com.vns.his.user.entity.Account;
import com.vns.his.user.repository.AccountRepo;
import com.vns.his.user.service.interfaces.AccountService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private AccountConverter accountConverter;

	public Account getByIdThrowException(Integer id) {
		return accountRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not exist with id: " + id));
	}

	@Override
	public ResponseEntity<Object> save(AccountDto accountDto) {
		Account account;
		if (accountDto.getId() == null) {
			account = accountConverter.toEntity(accountDto);
			account.setId(getNewId());
		} else {
			account = getByIdThrowException(accountDto.getId());
			account = accountConverter.toEntity(accountDto, account);
		}
		account = accountRepo.save(account);
		return ResponseEntity.ok(accountConverter.toDto(account));
	}

	@Override
	public void delete(Integer id) {
		Account account = getByIdThrowException(id);
		accountRepo.delete(account);
	}

	@Override
	public AccountDto findById(Integer id) {
		Account account = getByIdThrowException(id);
		AccountDto accountDto = accountConverter.toDto(account);
		return accountDto;
	}

	@Override
	public List<AccountDto> findAll(Pageable pageable) {
		List<AccountDto> results = new ArrayList<>();
		List<Account> entities = accountRepo.findAll(pageable).getContent();
		for (Account item : entities) {
			AccountDto accountDto = accountConverter.toDto(item);
			results.add(accountDto);
		}
		return results;
	}

	@Override
	public Integer getNewId() {
		Integer newId = 0;
		Account account = accountRepo.findTopByOrderByIdDesc();
		if (account != null) {
			newId = account.getId() + 1;
		}
		return newId;
	}

	@Override
	public long count() {
		return accountRepo.count();
	}
}
