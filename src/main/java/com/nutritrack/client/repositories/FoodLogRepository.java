package com.nutritrack.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritrack.client.models.FoodLog;

public interface FoodLogRepository extends JpaRepository<FoodLog, Integer> {
}
