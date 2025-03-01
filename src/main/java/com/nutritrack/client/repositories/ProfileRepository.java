package com.nutritrack.client.repositories;
import com.nutritrack.client.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository <Profile, UUID> {
    Optional<Profile> findByAccount_Uid(String uid);
}
