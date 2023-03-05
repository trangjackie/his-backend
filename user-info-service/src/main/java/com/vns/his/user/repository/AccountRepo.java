package com.vns.his.user.repository;

import com.vns.his.user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Account findTopByOrderByIdDesc();
}
