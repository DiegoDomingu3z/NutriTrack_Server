package com.nutritrack.client.models;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "daily_log", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"account_uid", "log_date"})
})
public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_uid", nullable = false)
    private String accountUid;

    @Column(name = "log_date", nullable = false)
    private OffsetDateTime logDate;

    @Column(name = "updatedOn", nullable = false)
    private OffsetDateTime updatedOn;


    // Optional: if you want to have a relationship with food logs
    @OneToMany(mappedBy = "dailyLog")
    private List<FoodLog> foodLogs;

    public DailyLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public OffsetDateTime getLogDate() {
        return logDate;
    }

    public void setLogDate(OffsetDateTime logDate) {
        this.logDate = logDate;
    }
    public OffsetDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdateeOn(OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<FoodLog> getFoodLogs() {
        return foodLogs;
    }

    public void setFoodLogs(List<FoodLog> foodLogs) {
        this.foodLogs = foodLogs;
    }

    @Override
    public String toString() {
        return "DailyLog{" +
                "id=" + id +
                ", accountUid='" + accountUid + '\'' +
                ", logDate=" + logDate +
                '}';
    }
}