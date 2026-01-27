package com.course_genie.syllabus;
import org.springframework.stereotype.Service;

import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminSyllabusService {

    private final UserRepository userRepository;
    private final SyllabusRepository syllabusRepository;
    private final SectionRepository sectionRepository;

    public AdminSyllabusService(
            UserRepository userRepository,
            SectionRepository sectionRepository,
            SyllabusRepository syllabusRepository
    ){
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.syllabusRepository = syllabusRepository;
    }

    public List<SyllabusProgressDTO> getSyllabusProgressByProfessor(){
        List<User> professors=userRepository.findByRoles("ROLE_PROFESSOR");

        return professors.stream().map(user->{
            List<Section> sections=sectionRepository.findByProfessorUserId(user.getUserId());
            int totalSections=sections.size();
            int submittedCount=(int) sections.stream()
                    .map(syllabusRepository::findSyllabusBySection)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(Syllabus::isSubmitted)
                    .count();
            double progress=totalSections==0 ? 0 :
                    (submittedCount * 100.0)/totalSections;

            return SyllabusProgressDTO.builder()
                    .professorId(user.getUserId())
                    .professorName(user.getFullName())
                    .totalSections(totalSections)
                    .submittedSyllabi(submittedCount)
                    .progressPercentage(progress)
                    .build();
        }).toList();
    }


}
