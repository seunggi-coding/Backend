package com.farm.smartfarm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "croplist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "CROPLIST_SEQ_GENERATOR"
        , sequenceName = "CROPLIST_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class CropList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CROPLIST_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false, length = 50)
    private String cropName;

    @Column(nullable = false, length = 50)
    private String env_sun;

    @Column(nullable = false, length = 50)
    private String env_water;

    @Column(nullable = false)
    private String write_date;

    @Column(nullable = false, length = 70)
    private String writer;
}
