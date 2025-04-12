package org.example.graduationprojectbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "model_daily_statistic")
public class ModelDailyStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "daily_count")
    private Long usageCount;

    @Column(name = "usage_date")
    private LocalDate usageDate = LocalDate.now();
}
