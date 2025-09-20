package com.course_genie.student;

import com.course_genie.grade.GradeDTO;
import com.course_genie.grade.GradeDTOMapper;
import com.course_genie.grade.GradeRepository;
import com.course_genie.grade.GradeService;
import com.course_genie.syllabus.SyllabusDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentDTOMapper studentDTOMapper;
    private final GradeRepository gradeRepository;
    private final GradeDTOMapper gradeDTOMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, StudentDTOMapper studentDTOMapper, GradeRepository gradeRepository, GradeDTOMapper gradeDTOMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.studentDTOMapper = studentDTOMapper;
        this.gradeRepository = gradeRepository;
        this.gradeDTOMapper = gradeDTOMapper;
    }

    // Read
    public HashMap<StudentDTO,List<GradeDTO>> getAllStudents(Long sectionId) {
        HashMap<StudentDTO,List<GradeDTO>> result = new HashMap<>();
        List<StudentDTO> studentsDTO = studentRepository.findAll().stream().map(studentDTOMapper).toList();
        for (StudentDTO studentDTO : studentsDTO) {
            List<GradeDTO> gradesDTO = gradeRepository.findGradeByStudentIdAndSectionId(studentDTO.studentId(), sectionId)
                    .orElse(new ArrayList<>()).stream().map(gradeDTOMapper).toList();
            result.put(studentDTO,gradesDTO);
        }
        return result;
    }
}
