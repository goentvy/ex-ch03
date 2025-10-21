package com.entvy.student.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @Min(value = 1, message = "나이는 1세 이상이어야 합니다.")
    @Max(value = 120, message = "나이는 120세 이하이어야 합니다.")
    private Integer age;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
