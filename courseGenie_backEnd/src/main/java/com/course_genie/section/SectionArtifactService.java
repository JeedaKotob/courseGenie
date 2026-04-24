package com.course_genie.section;

import com.course_genie.car.Car;
import com.course_genie.car.CarRepository;
import com.course_genie.syllabus.Syllabus;
import com.course_genie.syllabus.SyllabusRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SectionArtifactService {

    private final SectionRepository sectionRepository;
    private final SyllabusRepository syllabusRepository;
    private final CarRepository carRepository;

    public SectionArtifactService(
            SectionRepository sectionRepository,
            SyllabusRepository syllabusRepository,
            CarRepository carRepository
    ) {
        this.sectionRepository = sectionRepository;
        this.syllabusRepository = syllabusRepository;
        this.carRepository = carRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void provisionMissingArtifactsAtStartup() {
        sectionRepository.findAll().forEach(this::ensureArtifactsForSection);
    }

    public void ensureArtifactsForSection(Section section) {
        syllabusRepository.findSyllabusBySection(section).orElseGet(() -> {
            Syllabus syllabus = new Syllabus();
            syllabus.setSection(section);
            syllabus.setSubmitted(false);
            return syllabusRepository.save(syllabus);
        });

        carRepository.findCarBySectionSectionId(section.getSectionId()).orElseGet(() -> {
            Car car = new Car();
            car.setSection(section);
            car.setSubmitted(false);
            return carRepository.save(car);
        });
    }
}
