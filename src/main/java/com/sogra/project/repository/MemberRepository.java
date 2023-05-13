package com.sogra.project.repository;


import com.sogra.project.model.Family;
import com.sogra.project.model.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    long countAllByFamily(Family family);
    boolean existsByFamilyAndName(Family family, String name);
    List<Member> findAllByFamily(Family family);
    Member findByNameAndFamily(String name, Family family);
}
