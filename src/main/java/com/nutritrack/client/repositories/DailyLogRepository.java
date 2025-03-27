package com.nutritrack.client.repositories;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritrack.client.models.DailyLog;

public interface DailyLogRepository extends JpaRepository<DailyLog, Integer> {
    Optional<DailyLog> findByAccountUidAndLogDate(String accountUid, OffsetDateTime logDate);
}