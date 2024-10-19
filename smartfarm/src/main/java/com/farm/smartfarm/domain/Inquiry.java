package com.farm.smartfarm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "inquiry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "INQUIRY_SEQ_GENERATOR"
        , sequenceName = "INQUIRY_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INQUIRY_SEQ_GENERATOR")
    private Long inquiryId;

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

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InquiryComment> comments = new ArrayList<>();

    public void increaseViewCount() {
        this.viewCount += 1;
    }
}
