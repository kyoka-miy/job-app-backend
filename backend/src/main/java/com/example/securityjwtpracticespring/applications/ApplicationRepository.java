package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.cfg.AvailableSettings.USER;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByUser(User user);

    @Query("SELECT app FROM Application app WHERE app.user = :user AND (LOWER(app.companyName) LIKE LOWER(concat('%', :searchText, '%')) OR LOWER(app.jobTitle) LIKE LOWER(concat('%', :searchText, '%')))")
    List<Application> findBySearchText(@Param("user") User user, @Param("searchText") String searchText);

    @Query("SELECT app FROM Application app WHERE app.user = :user AND (app.status = :status)")
    List<Application> findByStatus(@Param("user") User user, @Param("status") Status status);
}
