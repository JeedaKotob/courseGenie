package com.course_genie.car;

import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import com.course_genie.user.User;
import com.course_genie.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminCarService {

    private final UserRepository userRepository;
    private final SectionRepository sectionRepository;
    private final CarRepository carRepository;

    public AdminCarService(UserRepository userRepository,
                           SectionRepository sectionRepository,
                           CarRepository carRepository) {
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.carRepository = carRepository;
    }

    public Map<String, List<CarProgressDTO>> getCarProgressByDepartment() {
        List<User> professors = userRepository.findByRoles("ROLE_PROFESSOR");

        List<CarProgressDTO> flatList = professors.stream().map(user -> {
            List<Section> sections = sectionRepository.findByProfessorUserId(user.getUserId());
            int totalSections = sections.size();

            List<CarDetailDTO> sectionDetails = sections.stream()
                    .map(this::toCarDetailDTO)
                    .toList();

            int submittedCount = (int) sections.stream()
                    .map(section -> carRepository.findCarBySectionSectionId(section.getSectionId()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(Car::isSubmitted)
                    .count();

            double progress = totalSections == 0 ? 0 : (submittedCount * 100.0) / totalSections;
            String deptName = (user.getDepartment() != null) ? user.getDepartment().getDepartmentName() : "Unassigned";

            return CarProgressDTO.builder()
                    .professorId(user.getUserId())
                    .professorName(user.getFullName())
                    .departmentName(deptName)
                    .totalSections(totalSections)
                    .submittedCars(submittedCount)
                    .progressPercentage(progress)
                    .sections(sectionDetails)
                    .build();
        }).toList();

        return flatList.stream()
                .collect(Collectors.groupingBy(CarProgressDTO::departmentName));
    }

    private CarDetailDTO toCarDetailDTO(Section section) {
        Optional<Car> optionalCar = carRepository.findCarBySectionSectionId(section.getSectionId());
        LocalDate dueDate = section.getSemester() != null ? section.getSemester().getCarDueDate() : null;
        LocalDate comparisonDate = optionalCar.isPresent() && optionalCar.get().isSubmitted()
                ? optionalCar.get().getSubmissionDate()
                : LocalDate.now();

        long overdueBy = 0;
        if (dueDate != null && comparisonDate != null && comparisonDate.isAfter(dueDate)) {
            overdueBy = ChronoUnit.DAYS.between(dueDate, comparisonDate);
        }

        return CarDetailDTO.builder()
                .sectionId(section.getSectionId())
                .courseName(section.getCourse().getName())
                .courseCode(section.getCourse().getCode())
                .sectionCode(section.getCode())
                .submitted(optionalCar.map(Car::isSubmitted).orElse(false))
                .submissionDate(optionalCar.map(Car::getSubmissionDate).orElse(null))
                .carDueDate(dueDate)
                .overdueBy(overdueBy)
                .build();
    }
}
