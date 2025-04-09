package org.example.graduationprojectbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_daily_statistic")
public class UserDailyStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "usage_data")
    private LocalDate usageData;

    @Column(name = "daily_count")
    private Long usageCount;

    @PrePersist
    protected void onCreate() {
        usageData = LocalDate.now();
    }
}
