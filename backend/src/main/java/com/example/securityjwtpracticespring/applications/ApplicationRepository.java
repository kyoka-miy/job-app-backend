package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.hibernate.cfg.AvailableSettings.USER;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByUser(User user);
    @Query("SELECT app FROM Application app WHERE app.user.userId = :userId")
    List<Application> findByUserId(Integer userId);

    @Query("SELECT app FROM Application app WHERE app.user = :user AND (app.companyName LIKE %:searchText% OR app.jobTitle LIKE %:searchText%)")
    List<Application> findBySearchText(@Param("user") User user, @Param("searchText") String searchText);
}
