package com.citizen.job.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "Department cannot be null")
    @Column( nullable = false, length = 100)
    private String department;

    @NotNull(message = "Location cannot be null")
    @Column(nullable = false)
    private String location;

    @NotNull(message = "Description cannot be null")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private List<String> requirements =  new ArrayList<>();

    @NotNull(message = "Deadline cannot be null")
    @Column(nullable = false)
    private String contactPersonName;

    @NotNull
    @Column(nullable = false)
    private String contactPhoneNumber;

    private String contactEmail;

    private String contactAddress;

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


    @NotNull(message = "Deadline cannot be null")
    @Column(nullable = false)
    private LocalDateTime deadline;

}
