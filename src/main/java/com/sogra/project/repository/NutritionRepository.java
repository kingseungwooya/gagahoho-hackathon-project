package com.sogra.project.repository;


import com.sogra.project.model.Todo;
import com.sogra.project.model.file.NutritionVoice;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository extends JpaRepository<NutritionVoice, UUID> {

}
