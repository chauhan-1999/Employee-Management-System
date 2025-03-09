package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;

}
