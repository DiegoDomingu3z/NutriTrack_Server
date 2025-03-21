package com.nutritrack.client.controllers;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.nutritrack.client.dto.CreateAccountRequestDTO;
import com.nutritrack.client.models.Account;
import com.nutritrack.client.models.Profile;
import com.nutritrack.client.services.AccountService;
import com.nutritrack.client.services.FatSecretTokenService;
import com.nutritrack.client.services.ProfileService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Controller for handling account-related operations, including account creation, login, and logout.
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    private final ProfileService profileService;
    private final FatSecretTokenService tokenService;
    private final WebClient webClient;

    /**
     * Constructor for injecting the {@link AccountService}.
     *
     * @param accountService The service responsible for account-related operations.
     */
    @Autowired
    public AccountController(AccountService accountService, ProfileService profileService, FatSecretTokenService tokenService, WebClient.Builder webClientBuilder, @Value("${fatsecret.tokenUrl}") String apiBaseUrl) {
        this.accountService = accountService;
        this.profileService = profileService;
        this.tokenService = tokenService;
        this.webClient = webClientBuilder.baseUrl(apiBaseUrl).build();
    }

    /**
     * @version 1.0
     * @description: Handles the creation of a new user account. This is a public endpoint,
     *               meaning it does not enforce Firebase token authentication or API rate limiting.
     *               The method validates input, creates an Account entity, associates it with a
     *               Profile entity, and persists it in the database.
     *
     * @param request The request body containing user details including UID, email, and profile information.
     * @return ResponseEntity<?> A response containing the created account details or an error message.
     */
    @PostMapping("/public/create")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequestDTO request) {
        // Validate input
        if (request.getUid() == null || request.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UID and Email are required.");
        }
        try {
            // ACCOUNT ENTITY
            Account account = new Account();
            account.setUid(request.getUid());
            account.setEmail(request.getEmail());
            account.setLoggedIn(true);
            System.out.println(account);
            CreateAccountRequestDTO.ProfileDTO profileDTO = request.getProfile();
            // PROFILE ENTITY
            Profile profile = new Profile();
            profile.setFirstName(profileDTO.getFirstName());
            profile.setLastName(profileDTO.getLastName());

            // Convert date_of_birth string to LocalDate.
            LocalDate dob = LocalDate.parse(profileDTO.getDateOfBirth());
            profile.setDateOfBirth(dob);
            profile.setGender(profileDTO.getGender());
            profile.setHeight(profileDTO.getHeight());
            profile.setWeight(profileDTO.getWeight());
            profile.setAccount(account);

            Account savedAccount = accountService.createAccount(account, profile);
            return ResponseEntity.ok(savedAccount);

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating account: " + e.getMessage());
        }
    }

    /**
     * @version 1.0
     * @description: Logs in a user by updating their login status in the database
     *              This is a secure endpoint, since the token will already be generated
     *              first in the front end so i can verify that they successfully logged in
     *              firebase.
     *
     * @param request The HTTP request containing the Firebase-authenticated user's UID.
     * @return ResponseEntity<Profile> A response indicating whether the logout was successful or if an error occurred.
     */
    @PostMapping("/secure/login")
    public ResponseEntity<?> login(HttpServletRequest request) {
        try{
            String uid = (String) request.getAttribute("uid");
            boolean loggedIn = accountService.logUserIn(uid);
            if (loggedIn) {
                Profile userProfile = profileService.getProfile(uid);
                return ResponseEntity.ok(userProfile);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while logging in: " + e.getMessage());
        }
    }
    /**
     * @version 1.0
     * @description: Logs out a user by updating their login status in the database.
     *               This is a secure endpoint, meaning Firebase token authentication
     *               and API rate limiting middleware will be enforced.
     *
     * @param request The HTTP request containing the Firebase-authenticated user's UID.
     * @param requestBody A map containing additional logout parameters (currently unused).
     * @return ResponseEntity<String> A response indicating whether the logout was successful or if an error occurred.
     */
    @PutMapping("/secure/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request) {
        try {
            // Get uid that was set by middleware
            String uid = (String) request.getAttribute("uid");
            // Update the logged_in field for this user
            boolean loggedOut = accountService.logUserOut(uid);
            if (loggedOut) {
                return ResponseEntity.ok("Logout status updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
    }

        /**
     * Handles GET requests to retrieve an account based on the authenticated user's UID.
     *
     * @param request The {@link HttpServletRequest} containing the user's UID as an attribute.
     * @return A {@link ResponseEntity} containing the {@link Account} if found,
     *         {@code 404 NOT FOUND} if the account does not exist, or
     *         {@code 401 UNAUTHORIZED} if an error occurs.
     */
    @GetMapping("/secure")
    public ResponseEntity<Account> getAccount(
            HttpServletRequest request){
                try {
                    String uid = (String) request.getAttribute("uid");
                    Account account = accountService.getAccount(uid);
                    if (account == null){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    }
                    return ResponseEntity.ok(account); 
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                }
            }

}
