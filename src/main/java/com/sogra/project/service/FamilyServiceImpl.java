package com.sogra.project.service;

import com.sogra.project.config.CustomPasswordEncoder;
import com.sogra.project.controller.dto.FamilyDto;
import com.sogra.project.controller.dto.FamilyInfoDto;
import com.sogra.project.model.Family;
import com.sogra.project.model.file.NutritionVoice;
import com.sogra.project.model.file.WorkOutVoice;
import com.sogra.project.repository.FamilyRepository;
import com.sogra.project.repository.MemberRepository;
import com.sogra.project.repository.NutritionRepository;
import com.sogra.project.repository.WorkoutRepository;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Transactional
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final MemberRepository memberRepository;
    private final CustomPasswordEncoder passwordEncoder;
    private final WorkoutRepository workoutRepository;
    private final NutritionRepository nutritionRepository;

    @Override
    public void createFamily(FamilyDto familyDto) throws IOException {
        Family family = familyRepository.save(
                familyDto.toFamily(passwordEncoder.encode(familyDto.getPassword())));
        MultipartFile nutFile = familyDto.getNutritionFile();
        String nutFileName = StringUtils.cleanPath(nutFile.getOriginalFilename());
        NutritionVoice nutritionVoice = new NutritionVoice(nutFileName, nutFile.getContentType(), nutFile.getBytes());
        nutritionRepository.save(nutritionVoice);
        MultipartFile workFile = familyDto.getWorkoutFile();
        String workFileName = StringUtils.cleanPath(workFile.getOriginalFilename());
        WorkOutVoice workOutVoice = new WorkOutVoice(workFileName, workFile.getContentType(), workFile.getBytes());
        workoutRepository.save(workOutVoice);
        memberRepository.save(familyDto.toMember(family, nutritionVoice, workOutVoice));
    }


    @Override
    public List<FamilyInfoDto> search(String familyName) {
        return familyRepository.findAllByFamilyIdContainingIgnoreCase(familyName)
                .stream()
                .map(entity -> entity.toDto(
                        memberRepository.countAllByFamily(entity)))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isDuplicated(String familyName) {
        return familyRepository.existsByFamilyId(familyName);
    }

    @Override
    public Boolean isEnter(String familyName, String password) {
        String encodedPassword = familyRepository.findByFamilyId(familyName).getPassword();
        return passwordEncoder.matches(password, encodedPassword);
    }
}
