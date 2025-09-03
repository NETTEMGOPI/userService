package com.gopi.EventDrivenArch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Find by company name (for duplicate checking)
    boolean existsByCompanyName(String companyName);

    // Find by contact email (for duplicate checking)
    boolean existsByContactEmail(String contactEmail);

    // Find by company name (case insensitive)
    Optional<Company> findByCompanyNameIgnoreCase(String companyName);

    // Find by contact email
    Optional<Company> findByContactEmail(String contactEmail);

    // Find companies by state (for regional processing)
    List<Company> findByState(String state);

    // Find companies by country
    List<Company> findByCountry(String country);

    // Custom query to find companies by partial company name match
    @Query("SELECT c FROM Company c WHERE LOWER(c.companyName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Company> findByCompanyNameContaining(@Param("name") String name);
}