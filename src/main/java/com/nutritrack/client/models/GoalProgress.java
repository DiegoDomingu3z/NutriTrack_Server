package com.nutritrack.client.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "goal_progress")
public class GoalProgress {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private UserGoals goal;

    @Column(name = "recorded_on", updatable = false)
    private LocalDateTime recordedOn = LocalDateTime.now();

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal weight;

    @Column(name = "image_urls", columnDefinition = "TEXT") // Store as comma-separated string
    private String imageUrls;

    public GoalProgress() {}

    public GoalProgress(UserGoals goal, BigDecimal weight) {
        this.goal = goal;
        this.weight = weight;
    }

    // Convert list to comma-separated string before saving
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = String.join(",", imageUrls);
    }

    // Convert comma-separated string back to list
    public List<String> getImageUrls() {
        return imageUrls != null ? Arrays.asList(imageUrls.split(",")) : List.of();
    }

    // Getters & Setters
    public String getId() { return id; }
    public UserGoals getGoal() { return goal; }
    public void setGoal(UserGoals goal) { this.goal = goal; }
    public LocalDateTime getRecordedOn() { return recordedOn; }
    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
}