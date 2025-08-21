package com.citizen.job.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"program_title", "program_description", "coordinator_name", "program_date", "program_time"}))
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "program_title", nullable = false)
    private String programTitle;

    @NotNull
    @Column(nullable = false)
    private String location;

    @NotNull
    @Column(name = "program_description", nullable = false)
    private String programDescription;

    @NotNull
    @Column(name = "program_date", nullable = false)
    private LocalDate programDate;

    @NotNull
    @Column(name = "program_time", nullable = false)
    private LocalTime programTime;

    @NotNull
    @Column(nullable = false)
    private String duration;

    @NotNull
    @Column(name = "coordinator_name", nullable = false)
    private String coordinatorName;

    private String coordinatorEmail;

    @NotNull
    @Column(nullable = false)
    private String coordinatorPhone;

    private String coordinatorAddress;

    @Column(
            nullable = false,
            columnDefinition = "boolean default true"
    )
    private Boolean isActive = true;

    @Column(
            nullable = false,
            columnDefinition = "boolean default false"
    )
    private Boolean isDeleted = false;
}
