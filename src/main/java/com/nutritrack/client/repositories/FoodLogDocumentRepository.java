package com.nutritrack.client.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nutritrack.client.documents.FoodLogDocument;

public interface FoodLogDocumentRepository extends MongoRepository<FoodLogDocument, Integer> {
    // Custom finder method to retrieve a document by dailyLogId
    FoodLogDocument findByDailyLogId(Integer dailyLogId);

    @Query("{'created': { $gte: ?0, $lte: ?1 }, 'accountId': ?2 }")
    List<FoodLogDocument> findByCreatedBetweenAndAccountId(Date startDate, Date endDate, String accountId);




}