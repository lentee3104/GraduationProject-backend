package org.example.graduationprojectbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "model_total_statistic")
public class ModelTotalStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "total_count")
    private Long usageCount;
}
