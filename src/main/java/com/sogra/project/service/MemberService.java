package com.sogra.project.service;

import com.sogra.project.controller.dto.JoinRequestDto;
import com.sogra.project.model.file.NutritionVoice;
import com.sogra.project.model.file.WorkOutVoice;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void joinFamily(JoinRequestDto joinRequestDto) throws Exception;

    NutritionVoice getNutritionAudio(String familyName, String memberName);

    WorkOutVoice getWorkOutAudio(String familyName, String memberName);

}
