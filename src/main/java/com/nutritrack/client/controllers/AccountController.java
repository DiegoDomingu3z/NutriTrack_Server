package com.nutritrack.client.controllers;


import com.nutritrack.client.models.Account;
import com.nutritrack.client.services.AccountService;
import com.nutritrack.client.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nutritrack.client.dto.AccountRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        Account account = new Account();
        account.setUid(accountRequest.getUid());
        account.setEmail(accountRequest.getEmail());
        account.setLoggedIn(true);
//        account.setPasswordHash(accountRequest.getPasswordHash());
        System.out.println(account);
        Account savedAccount = accountService.createAccount(account);
        return ResponseEntity.ok(savedAccount);
    }


    @PutMapping("/logout")
    public ResponseEntity<String> updateAccount(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestBody Map<String, Boolean> requestBody) {
        try {
            // Extract and verify the Firebase token
            String idToken = authorizationHeader.replace("Bearer ", "");
            String uid = FirebaseService.verifyToken(idToken); // Firebase UID

            // Extract the 'loggedIn' status from the request body
            boolean loggedIn = requestBody.get("loggedIn");

            // Update the logged_in field for this user
//            boolean updated = accountService.updateLoggedInStatus(uid, loggedIn);
//
//            if (updated) {
//                return ResponseEntity.ok("Login status updated successfully.");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
//            }
            return ResponseEntity.ok("Login status updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
    }


}
