package com.ltp.gradesubmission.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Student {

    private Long id;
    private String name;
    private LocalDate birthDate;
}
