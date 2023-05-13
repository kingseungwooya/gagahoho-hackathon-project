package com.sogra.project.service;

import com.sogra.project.controller.dto.FamilyDto;
import com.sogra.project.controller.dto.FamilyInfoDto;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface FamilyService {

    void createFamily(FamilyDto familyForm) throws IOException;

    List<FamilyInfoDto> search(String familyName);

    Boolean isDuplicated(String familyName);

    Boolean isEnter(String familyName, String password);
}
