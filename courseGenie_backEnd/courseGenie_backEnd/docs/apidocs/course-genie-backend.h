#import "enunciate-common.h"

@class COURSE_GENIE_BACKENDNS0BenchmarkDTO;
@class COURSE_GENIE_BACKENDNS0CLODTO;
@class COURSE_GENIE_BACKENDNS0GradeDTO;
@class COURSE_GENIE_BACKENDNS0AuthQuery;
@class COURSE_GENIE_BACKENDNS0SyllabusDTO;
@class COURSE_GENIE_BACKENDNS0StudentDTO;
@class COURSE_GENIE_BACKENDNS0SectionDTO;
@class COURSE_GENIE_BACKENDNS0CourseDTO;
@class COURSE_GENIE_BACKENDNS0BookDTO;
@class COURSE_GENIE_BACKENDNS0AssessmentDTO;
@class COURSE_GENIE_BACKENDNS0Assessment;

#ifndef DEF_COURSE_GENIE_BACKENDNS0BenchmarkDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0BenchmarkDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0BenchmarkDTO : NSObject
{
  @private
    NSString *_description;
    int _percentage;
    NSString *_benchmarkType;
    long long _benchmarkId;
    int _threshold;
}

/**
 * (no documentation provided)
 */
- (NSString *) description;

/**
 * (no documentation provided)
 */
- (void) setDescription: (NSString *) newDescription;

/**
 * (no documentation provided)
 */
- (int) percentage;

/**
 * (no documentation provided)
 */
- (void) setPercentage: (int) newPercentage;

/**
 * (no documentation provided)
 */
- (NSString *) benchmarkType;

/**
 * (no documentation provided)
 */
- (void) setBenchmarkType: (NSString *) newBenchmarkType;

/**
 * (no documentation provided)
 */
- (long long) benchmarkId;

/**
 * (no documentation provided)
 */
- (void) setBenchmarkId: (long long) newBenchmarkId;

/**
 * (no documentation provided)
 */
- (int) threshold;

/**
 * (no documentation provided)
 */
- (void) setThreshold: (int) newThreshold;
@end /* interface COURSE_GENIE_BACKENDNS0BenchmarkDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0BenchmarkDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0CLODTO_H
#define DEF_COURSE_GENIE_BACKENDNS0CLODTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0CLODTO : NSObject
{
  @private
    NSString *_name;
    NSString *_description;
    long long _courseId;
    long long _cloId;
}

/**
 * (no documentation provided)
 */
- (NSString *) name;

/**
 * (no documentation provided)
 */
- (void) setName: (NSString *) newName;

/**
 * (no documentation provided)
 */
- (NSString *) description;

/**
 * (no documentation provided)
 */
- (void) setDescription: (NSString *) newDescription;

/**
 * (no documentation provided)
 */
- (long long) courseId;

/**
 * (no documentation provided)
 */
- (void) setCourseId: (long long) newCourseId;

/**
 * (no documentation provided)
 */
- (long long) cloId;

/**
 * (no documentation provided)
 */
- (void) setCloId: (long long) newCloId;
@end /* interface COURSE_GENIE_BACKENDNS0CLODTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0CLODTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0GradeDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0GradeDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0GradeDTO : NSObject
{
  @private
    long long _assessmentId;
    double _score;
    NSString *_studentId;
    long long _gradeId;
}

/**
 * (no documentation provided)
 */
- (long long) assessmentId;

/**
 * (no documentation provided)
 */
- (void) setAssessmentId: (long long) newAssessmentId;

/**
 * (no documentation provided)
 */
- (double) score;

/**
 * (no documentation provided)
 */
- (void) setScore: (double) newScore;

/**
 * (no documentation provided)
 */
- (NSString *) studentId;

/**
 * (no documentation provided)
 */
- (void) setStudentId: (NSString *) newStudentId;

/**
 * (no documentation provided)
 */
- (long long) gradeId;

/**
 * (no documentation provided)
 */
- (void) setGradeId: (long long) newGradeId;
@end /* interface COURSE_GENIE_BACKENDNS0GradeDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0GradeDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0AuthQuery_H
#define DEF_COURSE_GENIE_BACKENDNS0AuthQuery_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0AuthQuery : NSObject
{
  @private
    NSString *_username;
    NSString *_password;
}

/**
 * (no documentation provided)
 */
- (NSString *) username;

/**
 * (no documentation provided)
 */
- (void) setUsername: (NSString *) newUsername;

/**
 * (no documentation provided)
 */
- (NSString *) password;

/**
 * (no documentation provided)
 */
- (void) setPassword: (NSString *) newPassword;
@end /* interface COURSE_GENIE_BACKENDNS0AuthQuery */

#endif /* DEF_COURSE_GENIE_BACKENDNS0AuthQuery_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0SyllabusDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0SyllabusDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0SyllabusDTO : NSObject
{
  @private
    long long _sectionId;
    NSString *_content;
    long long _syllabusId;
}

/**
 * (no documentation provided)
 */
- (long long) sectionId;

/**
 * (no documentation provided)
 */
- (void) setSectionId: (long long) newSectionId;

/**
 * (no documentation provided)
 */
- (NSString *) content;

/**
 * (no documentation provided)
 */
- (void) setContent: (NSString *) newContent;

/**
 * (no documentation provided)
 */
- (long long) syllabusId;

/**
 * (no documentation provided)
 */
- (void) setSyllabusId: (long long) newSyllabusId;
@end /* interface COURSE_GENIE_BACKENDNS0SyllabusDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0SyllabusDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0StudentDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0StudentDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0StudentDTO : NSObject
{
  @private
    NSString *_lastName;
    NSString *_firstName;
    NSString *_email;
    NSString *_studentId;
}

/**
 * (no documentation provided)
 */
- (NSString *) lastName;

/**
 * (no documentation provided)
 */
- (void) setLastName: (NSString *) newLastName;

/**
 * (no documentation provided)
 */
- (NSString *) firstName;

/**
 * (no documentation provided)
 */
- (void) setFirstName: (NSString *) newFirstName;

/**
 * (no documentation provided)
 */
- (NSString *) email;

/**
 * (no documentation provided)
 */
- (void) setEmail: (NSString *) newEmail;

/**
 * (no documentation provided)
 */
- (NSString *) studentId;

/**
 * (no documentation provided)
 */
- (void) setStudentId: (NSString *) newStudentId;
@end /* interface COURSE_GENIE_BACKENDNS0StudentDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0StudentDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0SectionDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0SectionDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0SectionDTO : NSObject
{
  @private
    NSString *_class_number;
    NSString *_code;
    long long _professorId;
    BOOL _configured;
    NSArray *_assessments;
    NSString *_term;
    NSString *_teachingMethodology;
    long long _courseId;
    long long _sectionId;
}

/**
 * (no documentation provided)
 */
- (NSString *) class_number;

/**
 * (no documentation provided)
 */
- (void) setClass_number: (NSString *) newClass_number;

/**
 * (no documentation provided)
 */
- (NSString *) code;

/**
 * (no documentation provided)
 */
- (void) setCode: (NSString *) newCode;

/**
 * (no documentation provided)
 */
- (long long) professorId;

/**
 * (no documentation provided)
 */
- (void) setProfessorId: (long long) newProfessorId;

/**
 * (no documentation provided)
 */
- (BOOL) configured;

/**
 * (no documentation provided)
 */
- (void) setConfigured: (BOOL) newConfigured;

/**
 * (no documentation provided)
 */
- (NSArray *) assessments;

/**
 * (no documentation provided)
 */
- (void) setAssessments: (NSArray *) newAssessments;

/**
 * (no documentation provided)
 */
- (NSString *) term;

/**
 * (no documentation provided)
 */
- (void) setTerm: (NSString *) newTerm;

/**
 * (no documentation provided)
 */
- (NSString *) teachingMethodology;

/**
 * (no documentation provided)
 */
- (void) setTeachingMethodology: (NSString *) newTeachingMethodology;

/**
 * (no documentation provided)
 */
- (long long) courseId;

/**
 * (no documentation provided)
 */
- (void) setCourseId: (long long) newCourseId;

/**
 * (no documentation provided)
 */
- (long long) sectionId;

/**
 * (no documentation provided)
 */
- (void) setSectionId: (long long) newSectionId;
@end /* interface COURSE_GENIE_BACKENDNS0SectionDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0SectionDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0CourseDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0CourseDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0CourseDTO : NSObject
{
  @private
    NSArray *_clos;
    NSArray *_sections;
    NSString *_description;
    NSString *_credits;
    NSString *_code;
    long long _courseId;
    NSString *_name;
}

/**
 * (no documentation provided)
 */
- (NSArray *) clos;

/**
 * (no documentation provided)
 */
- (void) setClos: (NSArray *) newClos;

/**
 * (no documentation provided)
 */
- (NSArray *) sections;

/**
 * (no documentation provided)
 */
- (void) setSections: (NSArray *) newSections;

/**
 * (no documentation provided)
 */
- (NSString *) description;

/**
 * (no documentation provided)
 */
- (void) setDescription: (NSString *) newDescription;

/**
 * (no documentation provided)
 */
- (NSString *) credits;

/**
 * (no documentation provided)
 */
- (void) setCredits: (NSString *) newCredits;

/**
 * (no documentation provided)
 */
- (NSString *) code;

/**
 * (no documentation provided)
 */
- (void) setCode: (NSString *) newCode;

/**
 * (no documentation provided)
 */
- (long long) courseId;

/**
 * (no documentation provided)
 */
- (void) setCourseId: (long long) newCourseId;

/**
 * (no documentation provided)
 */
- (NSString *) name;

/**
 * (no documentation provided)
 */
- (void) setName: (NSString *) newName;
@end /* interface COURSE_GENIE_BACKENDNS0CourseDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0CourseDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0BookDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0BookDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0BookDTO : NSObject
{
  @private
    long long *_identifier;
    NSString *_isbn;
    NSString *_title;
    NSString *_url;
    NSArray *_sectionIds;
    BOOL _requiredReading;
    BOOL _suggestedReading;
}

/**
 * (no documentation provided)
 */
- (long long *) identifier;

/**
 * (no documentation provided)
 */
- (void) setIdentifier: (long long *) newIdentifier;

/**
 * (no documentation provided)
 */
- (NSString *) isbn;

/**
 * (no documentation provided)
 */
- (void) setIsbn: (NSString *) newIsbn;

/**
 * (no documentation provided)
 */
- (NSString *) title;

/**
 * (no documentation provided)
 */
- (void) setTitle: (NSString *) newTitle;

/**
 * (no documentation provided)
 */
- (NSString *) url;

/**
 * (no documentation provided)
 */
- (void) setUrl: (NSString *) newUrl;

/**
 * (no documentation provided)
 */
- (NSArray *) sectionIds;

/**
 * (no documentation provided)
 */
- (void) setSectionIds: (NSArray *) newSectionIds;

/**
 * (no documentation provided)
 */
- (BOOL) requiredReading;

/**
 * (no documentation provided)
 */
- (void) setRequiredReading: (BOOL) newRequiredReading;

/**
 * (no documentation provided)
 */
- (BOOL) suggestedReading;

/**
 * (no documentation provided)
 */
- (void) setSuggestedReading: (BOOL) newSuggestedReading;
@end /* interface COURSE_GENIE_BACKENDNS0BookDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0BookDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0AssessmentDTO_H
#define DEF_COURSE_GENIE_BACKENDNS0AssessmentDTO_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0AssessmentDTO : NSObject
{
  @private
    int _week;
    NSString *_category;
    NSArray *_clos;
    NSString *_shortName;
    long long _sectionId;
    NSString *_name;
    int _maxPoints;
    long long _assessmentId;
}

/**
 * (no documentation provided)
 */
- (int) week;

/**
 * (no documentation provided)
 */
- (void) setWeek: (int) newWeek;

/**
 * (no documentation provided)
 */
- (NSString *) category;

/**
 * (no documentation provided)
 */
- (void) setCategory: (NSString *) newCategory;

/**
 * (no documentation provided)
 */
- (NSArray *) clos;

/**
 * (no documentation provided)
 */
- (void) setClos: (NSArray *) newClos;

/**
 * (no documentation provided)
 */
- (NSString *) shortName;

/**
 * (no documentation provided)
 */
- (void) setShortName: (NSString *) newShortName;

/**
 * (no documentation provided)
 */
- (long long) sectionId;

/**
 * (no documentation provided)
 */
- (void) setSectionId: (long long) newSectionId;

/**
 * (no documentation provided)
 */
- (NSString *) name;

/**
 * (no documentation provided)
 */
- (void) setName: (NSString *) newName;

/**
 * (no documentation provided)
 */
- (int) maxPoints;

/**
 * (no documentation provided)
 */
- (void) setMaxPoints: (int) newMaxPoints;

/**
 * (no documentation provided)
 */
- (long long) assessmentId;

/**
 * (no documentation provided)
 */
- (void) setAssessmentId: (long long) newAssessmentId;
@end /* interface COURSE_GENIE_BACKENDNS0AssessmentDTO */

#endif /* DEF_COURSE_GENIE_BACKENDNS0AssessmentDTO_H */
#ifndef DEF_COURSE_GENIE_BACKENDNS0Assessment_H
#define DEF_COURSE_GENIE_BACKENDNS0Assessment_H

/**
 * (no documentation provided)
 */
@interface COURSE_GENIE_BACKENDNS0Assessment : NSObject
{
  @private
}
@end /* interface COURSE_GENIE_BACKENDNS0Assessment */

#endif /* DEF_COURSE_GENIE_BACKENDNS0Assessment_H */
