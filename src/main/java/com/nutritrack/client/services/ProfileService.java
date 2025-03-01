package com.nutritrack.client.services;

import com.nutritrack.client.models.Account;
import com.nutritrack.client.models.Profile;
import com.nutritrack.client.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getProfile(String uid) {
        try {
            Optional<Profile> profileOpt = profileRepository.findByAccount_Uid(uid);
            if (profileOpt.isPresent()) {
                return profileOpt.get();
            } else {
                throw new RuntimeException("Profile not found for UID: " + uid);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
