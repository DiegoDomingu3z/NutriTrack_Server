package com.nutritrack.client.repositories;

import com.nutritrack.client.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository <Account, String> {
    // Custom Query: Find accounts by first name
//    @Query("SELECT a FROM Account a WHERE a.firstName = :firstName")
//    List<Account> findAccountsByFirstName(@Param("firstName") String firstName);

}
