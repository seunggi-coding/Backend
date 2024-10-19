package com.farm.smartfarm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "notice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "NOTICE_SEQ_GENERATOR"
        , sequenceName = "NOTICE_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GENERATOR")
    private Long noticeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private String creationDate;

    @Column(nullable = false)
    private Long viewCount;

    public void increaseViewCount() {
        this.viewCount += 1;
    }
}
