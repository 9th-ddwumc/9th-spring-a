package com.example.umc9th3.domain.member.entity;

import com.example.umc9th3.domain.member.entity.mapping.MemberFood;
import com.example.umc9th3.domain.member.enums.Gender;
//import com.example.umc9th3.domain.store.enums.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Getter
@Table (name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "gender")
    @Enumerated (EnumType.STRING)
    private Gender gender;
}
