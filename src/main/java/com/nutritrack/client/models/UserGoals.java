package com.nutritrack.client.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_goals")
public class UserGoals {

    @Id
    private String id = UUID.randomUUID().toString(); // Generates a unique UUID

    @ManyToOne
    @JoinColumn(name = "account_uid", nullable = false)
    private Account account; // Links to Account entity

    @Column(name = "isActive", columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isActive = true;

    @Column(precision = 6, scale = 2)
    private BigDecimal bmr; // Basal Metabolic Rate

    @Column(name = "goal_weight", precision = 5, scale = 2)
    private BigDecimal goalWeight; // Target weight

    @Column(name = "goal_timeline")
    private Integer goalTimeline; // Number of weeks for goal

    @Enumerated(EnumType.STRING)
    @Column(name = "goal", nullable = false)
    private GoalType goal;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn = LocalDateTime.now(); // Auto-set on creation

    @Column(name = "completed_on")
    private LocalDateTime completedOn; // Null unless goal is completed

    // Constructors
    public UserGoals() {}

    public UserGoals(Account account, BigDecimal bmr, BigDecimal goalWeight, Integer goalTimeline, GoalType goal) {
        this.account = account;
        this.bmr = bmr;
        this.goalWeight = goalWeight;
        this.goalTimeline = goalTimeline;
        this.goal = goal;
    }

    // Getters & Setters
    public String getId() { return id; }
    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public BigDecimal getBmr() { return bmr; }
    public void setBmr(BigDecimal bmr) { this.bmr = bmr; }
    public BigDecimal getGoalWeight() { return goalWeight; }
    public void setGoalWeight(BigDecimal goalWeight) { this.goalWeight = goalWeight; }
    public Integer getGoalTimeline() { return goalTimeline; }
    public void setGoalTimeline(Integer goalTimeline) { this.goalTimeline = goalTimeline; }
    public GoalType getGoal() { return goal; }
    public void setGoal(GoalType goal) { this.goal = goal; }
    public LocalDateTime getCreatedOn() { return createdOn; }
    public LocalDateTime getCompletedOn() { return completedOn; }
    public void setCompletedOn(LocalDateTime completedOn) { this.completedOn = completedOn; }
}

