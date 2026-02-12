package com.course_genie.syllabus;
import org.springframework.stereotype.Service;

import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminSyllabusService {

    private final UserRepository userRepository;
    private final SyllabusRepository syllabusRepository;
    private final SectionRepository sectionRepository;
    private final SyllabusDetailDTOMapper syllabusDetailDTOMapper;

    public AdminSyllabusService(
            UserRepository userRepository,
            SectionRepository sectionRepository,
            SyllabusRepository syllabusRepository,
            SyllabusDetailDTOMapper syllabusDetailDTOMapper
    ){
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.syllabusRepository = syllabusRepository;
        this.syllabusDetailDTOMapper = syllabusDetailDTOMapper;
    }

    public Map<String, List<SyllabusProgressDTO>> getSyllabusProgressByDepartment() {
        List<User> professors = userRepository.findByRoles("ROLE_PROFESSOR");

        List<SyllabusProgressDTO> flatList = professors.stream().map(user -> {
            List<Section> sections = sectionRepository.findByProfessorUserId(user.getUserId());
            int totalSections = sections.size();
            List<SyllabusDetailDTO> sectionDetails = sections.stream()
                    .map(section -> syllabusRepository.findSyllabusBySection(section)
                            .map(syllabusDetailDTOMapper)
                            .orElse(
                                    new SyllabusDetailDTO(
                                            section.getSectionId(),
                                            section.getCourse().getName(),
                                            section.getCourse().getCode(),
                                            section.getCode(),
                                            false
                                    )

                            )
                    )
                    .toList();
            int submittedCount = (int) sections.stream()
                    .map(syllabusRepository::findSyllabusBySection)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(Syllabus::isSubmitted)
                    .count();

            double progress = totalSections == 0 ? 0 : (submittedCount * 100.0) / totalSections;

            String deptName = (user.getDepartment() != null) ? user.getDepartment().getDepartmentName() : "Unassigned";

            return SyllabusProgressDTO.builder()
                    .professorId(user.getUserId())
                    .professorName(user.getFullName())
                    .departmentName(deptName)
                    .totalSections(totalSections)
                    .submittedSyllabi(submittedCount)
                    .progressPercentage(progress)
                    .sections(sectionDetails)
                    .build();
        }).toList();

        return flatList.stream()
                .collect(Collectors.groupingBy(SyllabusProgressDTO::departmentName));
    }
}
