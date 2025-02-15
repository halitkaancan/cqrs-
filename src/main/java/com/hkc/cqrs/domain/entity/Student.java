package com.hkc.cqrs.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {

    @Column(name = "student_no")
    private String studentNo;
    @OneToOne(mappedBy = "student")
    private Cart cart;

    @OneToMany(mappedBy = "")
    private Set<Order> orders;



}
