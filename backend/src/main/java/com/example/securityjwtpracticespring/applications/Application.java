package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue
    private Integer applicationId;
    private String companyName;
    private String jobTitle;
    private LocalDate date;
    private String country;
    private String comment;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Application(String companyName, String jobTitle, LocalDate date, String country, String comment, Status status, User user) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.date = date;
        this.country = country;
        this.comment = comment;
        this.status = status;
        this.user = user;
    }
}
