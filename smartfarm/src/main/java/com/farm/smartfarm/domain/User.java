package com.farm.smartfarm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR"
        , sequenceName = "USER_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 70)
    private String pwd;

    @Column(nullable = false)
    private String joinDate;

    @Column(nullable = false, length = 20)
    private String role;

    public boolean isAdmin() {
        return "ADMIN".equals(this.role);
    }
}
