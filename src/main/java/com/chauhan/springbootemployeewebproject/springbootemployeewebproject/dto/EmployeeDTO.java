package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.dto;

import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Name of the employee can not be blank")
    @Size(min = 3, max = 10, message = "Number of characters in the name should be in the range: [3,10]")
    private String name;

    @NotBlank(message = "Email of the employee cannot be blank")
    @Email(message = "Email should a valid email")
    private String email;

//    @NotBlank(message = "age of the employee cannot be blank")
    @Max(value = 80, message = "Age of the employee cannot be greater than 80")
    @Min(value = 18, message = "Age of the employee cannot be less than 18")
    private Integer age;

//    @NotBlank(message = "Role of the employee cannot be blank")
//    @EmployeeRoleValidation
//    private String role;

//    @NotNull(message = "Salary of the employee should not be null")
//    @Positive(message = "Salary should be positive")
//    @Digits(integer = 6,fraction = 2,message = "The salary can be in the form of XXXXXX.YY")
//    @DecimalMax(value = "100000.99")
//    @DecimalMin(value = "100.50")
//    private Double salary;

    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future")
    private LocalDate dateOfJoining;

    private Boolean isActive;

}
