package com.vns.his.user.controller;

import com.vns.his.user.dto.AccountDto;
import com.vns.his.user.entity.Account;
import com.vns.his.user.service.interfaces.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@SecurityRequirement(name = "bearerAuth")
// @Tag(name = "Account APIs for web")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// get Accounts by id
	@GetMapping("/accounts/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Integer id) {
		AccountDto AccountDto = accountService.findById(id);
		return ResponseEntity.ok(AccountDto);
	}

	// get list Accounts
	@GetMapping("/accounts")
	public ResponseEntity<Map<String, Object>> getAllAccounts(
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
		List<AccountDto> listResult;
		Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("dateCreate").descending());
		listResult = accountService.findAll(pageable);
		long total = accountService.count();
		Map<String, Object> response = new HashMap<>();
		response.put("page", page);
		response.put("limit", limit);
		response.put("data", listResult);
		response.put("total", total);
		return ResponseEntity.ok(response);
	}

	// create Account rest api
	@PostMapping(value = "/accounts")
	public ResponseEntity<Object> createAccount(@RequestBody AccountDto model) {
		model.setId(null);
		return accountService.save(model);
	}

	// update Account rest api
	@PutMapping(value = "/accounts/{id}")
	public ResponseEntity<Object> updateAccount(@RequestBody AccountDto model, @PathVariable("id") Integer id) {
		model.setId(id);
		return accountService.save(model);
	}

	// delete Accounts rest api
	@DeleteMapping(value = "/accounts/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAccount(@PathVariable("id") Integer id) {
		accountService.delete(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
