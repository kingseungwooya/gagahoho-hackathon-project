package com.sogra.project.repository;


import com.sogra.project.model.Family;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, String> {
    List<Family> findAllByFamilyIdContainingIgnoreCase(String familyName);
    boolean existsByFamilyId(String familyName);
    Family findByFamilyId(String familyName);
}
