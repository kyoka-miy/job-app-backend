package com.example.securityjwtpracticespring.applications;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {
    private String companyName;
    @Nullable
    private String jobTitle;
    private LocalDate date;
    @Nullable
    private String comment;
}
