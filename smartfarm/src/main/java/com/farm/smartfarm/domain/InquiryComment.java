package com.farm.smartfarm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "inquirycomment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "INQUIRYCOMMENT_SEQ_GENERATOR"
        , sequenceName = "INQUIRYCOMMENT_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class InquiryComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INQUIRYCOMMENT_SEQ_GENERATOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @Column(nullable = false, length = 50)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String creationDate;

}
