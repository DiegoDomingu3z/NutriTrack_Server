package com.nutritrack.client.services;
import com.nutritrack.client.models.Account;
import com.nutritrack.client.models.Profile;
import com.nutritrack.client.repositories.AccountRepository;
import com.nutritrack.client.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Service class responsible for managing user accounts and profiles, including
 *               core account functionality.
 */
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;

    /**
     * Constructor for AccountService.
     *
     * @param accountRepository The repository for Account entities.
     * @param profileRepository The repository for Profile entities.
     */
    public AccountService(AccountRepository accountRepository, ProfileRepository profileRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
    }

    /**
     * Creates a new user account and profile, saving them in the database.
     *
     * @param account The account entity to be created.
     * @param profile The profile entity associated with the account.
     * @return Account The saved account entity.
     * @throws Exception If an error occurs during the account creation process.
     */
    public Account createAccount(Account account, Profile profile) {
        try{
        Account savedAccount =  (Account) accountRepository.save(account);
        // NOTE: MAYBE PUT THIS IN REPO
        profileRepository.save(profile);
        return savedAccount;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Logs out a user by updating their login status in the database.
     *
     * @param uid The unique identifier of the user account.
     * @return boolean True if the logout was successful, false if the account was not found.
     */
    public boolean logUserOut(String uid){
        Optional<Account> userAccount = accountRepository.findById(uid);
        if (userAccount.isPresent()) {
            Account account = userAccount.get();

                account.setLoggedIn(false);

            // NOTE: MAYBE PUT THIS IN REPO
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public boolean logUserIn(String uid){
        Optional<Account> userAccount = accountRepository.findById(uid);
        if (userAccount.isPresent()) {
            Account account = userAccount.get();
            account.setLoggedIn(true);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

}
