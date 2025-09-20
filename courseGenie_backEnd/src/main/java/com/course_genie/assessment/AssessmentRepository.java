package com.course_genie.assessment;

import com.course_genie.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    Optional<List<Assessment>> findAssessmentBySectionSectionId(Long sectionId);

    @Query(value = "SELECT DISTINCT a.* FROM assessment a " +
            "INNER JOIN assessment_clo ac on ac.assessment_id = a.assessment_id " +
            "WHERE  ac.clo_id = :cloId AND a.section_id = :sectionId", nativeQuery = true)
    Optional<List<Assessment>> findAssessmentsByCloIdAndSectionId(@Param("cloId")Long cloId, @Param("sectionId") Long sectionId);

    @Query("SELECT c.description FROM CategoryDescription c WHERE c.section.sectionId = :sectionId AND c.categoryName = :categoryName")
    Optional<String> findCategoryDescription(@Param("sectionId") Long sectionId, @Param("categoryName") String categoryName);

    @Modifying
    @Transactional
    @Query("UPDATE CategoryDescription c SET c.description = :description WHERE c.section.sectionId = :sectionId AND c.categoryName = :categoryName")
    void updateCategoryDescription(@Param("sectionId") Long sectionId, @Param("categoryName") String categoryName, @Param("description") String description);

    @Modifying
    @Transactional
    @Query("DELETE FROM CategoryDescription c WHERE c.section.sectionId = :sectionId AND c.categoryName = :categoryName")
    void deleteCategoryDescription(@Param("sectionId") Long sectionId, @Param("categoryName") String categoryName);


}
