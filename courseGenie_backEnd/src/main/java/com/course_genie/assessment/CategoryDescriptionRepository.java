package com.course_genie.assessment;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDescriptionRepository extends JpaRepository<CategoryDescription, Long> {

    Optional<CategoryDescription> findFirstBySection_SectionIdAndCategoryNameIgnoreCase(
            Long sectionId,
            String categoryName
    );

    // Return a strongly typed list, not List<Object>.
    List<CategoryDescription> findBySection_SectionId(@NonNull Long sectionId);
}