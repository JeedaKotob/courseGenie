#include <enunciate-common.c>
#ifndef DEF_course_genie_backend_ns0_assessment_H
#define DEF_course_genie_backend_ns0_assessment_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_assessment {

};

/**
 * Reads a Assessment from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Assessment, or NULL in case of error.
 */
static struct course_genie_backend_ns0_assessment *xmlTextReaderReadNs0AssessmentType(xmlTextReaderPtr reader);

/**
 * Writes a Assessment to XML.
 *
 * @param writer The XML writer.
 * @param _assessment The Assessment to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0AssessmentType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_assessment *_assessment);

/**
 * Frees the elements of a Assessment.
 *
 * @param _assessment The Assessment to free.
 */
static void freeNs0AssessmentType(struct course_genie_backend_ns0_assessment *_assessment);

#endif /* DEF_course_genie_backend_ns0_assessment_H */
#ifndef DEF_course_genie_backend_ns0_assessmentDTO_H
#define DEF_course_genie_backend_ns0_assessmentDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_assessmentDTO {


  /**
   * (no documentation provided)
   */
  int week;

  /**
   * (no documentation provided)
   */
  xmlChar *category;

  /**
   * (no documentation provided)
   */
  struct course_genie_backend_ns0_CLODTO *clos;

  /**
   * Size of the clos array.
   */
  int _sizeof_clos;

  /**
   * (no documentation provided)
   */
  xmlChar *shortName;

  /**
   * (no documentation provided)
   */
  long long sectionId;

  /**
   * (no documentation provided)
   */
  xmlChar *name;

  /**
   * (no documentation provided)
   */
  int maxPoints;

  /**
   * (no documentation provided)
   */
  long long assessmentId;
};

/**
 * Reads a AssessmentDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The AssessmentDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_assessmentDTO *xmlTextReaderReadNs0AssessmentDTOType(xmlTextReaderPtr reader);

/**
 * Writes a AssessmentDTO to XML.
 *
 * @param writer The XML writer.
 * @param _assessmentDTO The AssessmentDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0AssessmentDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_assessmentDTO *_assessmentDTO);

/**
 * Frees the elements of a AssessmentDTO.
 *
 * @param _assessmentDTO The AssessmentDTO to free.
 */
static void freeNs0AssessmentDTOType(struct course_genie_backend_ns0_assessmentDTO *_assessmentDTO);

#endif /* DEF_course_genie_backend_ns0_assessmentDTO_H */
#ifndef DEF_course_genie_backend_ns0_benchmarkDTO_H
#define DEF_course_genie_backend_ns0_benchmarkDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_benchmarkDTO {


  /**
   * (no documentation provided)
   */
  xmlChar *description;

  /**
   * (no documentation provided)
   */
  int percentage;

  /**
   * (no documentation provided)
   */
  xmlChar *benchmarkType;

  /**
   * (no documentation provided)
   */
  long long benchmarkId;

  /**
   * (no documentation provided)
   */
  int threshold;
};

/**
 * Reads a BenchmarkDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The BenchmarkDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_benchmarkDTO *xmlTextReaderReadNs0BenchmarkDTOType(xmlTextReaderPtr reader);

/**
 * Writes a BenchmarkDTO to XML.
 *
 * @param writer The XML writer.
 * @param _benchmarkDTO The BenchmarkDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0BenchmarkDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_benchmarkDTO *_benchmarkDTO);

/**
 * Frees the elements of a BenchmarkDTO.
 *
 * @param _benchmarkDTO The BenchmarkDTO to free.
 */
static void freeNs0BenchmarkDTOType(struct course_genie_backend_ns0_benchmarkDTO *_benchmarkDTO);

#endif /* DEF_course_genie_backend_ns0_benchmarkDTO_H */
#ifndef DEF_course_genie_backend_ns0_bookDTO_H
#define DEF_course_genie_backend_ns0_bookDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_bookDTO {


  /**
   * (no documentation provided)
   */
  long long *id;

  /**
   * (no documentation provided)
   */
  xmlChar *isbn;

  /**
   * (no documentation provided)
   */
  xmlChar *title;

  /**
   * (no documentation provided)
   */
  xmlChar *url;

  /**
   * (no documentation provided)
   */
  long long *sectionIds;

  /**
   * Size of the sectionIds array.
   */
  int _sizeof_sectionIds;

  /**
   * (no documentation provided)
   */
  int requiredReading;

  /**
   * (no documentation provided)
   */
  int suggestedReading;
};

/**
 * Reads a BookDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The BookDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_bookDTO *xmlTextReaderReadNs0BookDTOType(xmlTextReaderPtr reader);

/**
 * Writes a BookDTO to XML.
 *
 * @param writer The XML writer.
 * @param _bookDTO The BookDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0BookDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_bookDTO *_bookDTO);

/**
 * Frees the elements of a BookDTO.
 *
 * @param _bookDTO The BookDTO to free.
 */
static void freeNs0BookDTOType(struct course_genie_backend_ns0_bookDTO *_bookDTO);

#endif /* DEF_course_genie_backend_ns0_bookDTO_H */
#ifndef DEF_course_genie_backend_ns0_CLODTO_H
#define DEF_course_genie_backend_ns0_CLODTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_CLODTO {


  /**
   * (no documentation provided)
   */
  xmlChar *name;

  /**
   * (no documentation provided)
   */
  xmlChar *description;

  /**
   * (no documentation provided)
   */
  long long courseId;

  /**
   * (no documentation provided)
   */
  long long cloId;
};

/**
 * Reads a CLODTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The CLODTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_CLODTO *xmlTextReaderReadNs0CLODTOType(xmlTextReaderPtr reader);

/**
 * Writes a CLODTO to XML.
 *
 * @param writer The XML writer.
 * @param _cLODTO The CLODTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0CLODTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_CLODTO *_cLODTO);

/**
 * Frees the elements of a CLODTO.
 *
 * @param _cLODTO The CLODTO to free.
 */
static void freeNs0CLODTOType(struct course_genie_backend_ns0_CLODTO *_cLODTO);

#endif /* DEF_course_genie_backend_ns0_CLODTO_H */
#ifndef DEF_course_genie_backend_ns0_courseDTO_H
#define DEF_course_genie_backend_ns0_courseDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_courseDTO {


  /**
   * (no documentation provided)
   */
  struct course_genie_backend_ns0_CLODTO *clos;

  /**
   * Size of the clos array.
   */
  int _sizeof_clos;

  /**
   * (no documentation provided)
   */
  struct course_genie_backend_ns0_sectionDTO *sections;

  /**
   * Size of the sections array.
   */
  int _sizeof_sections;

  /**
   * (no documentation provided)
   */
  xmlChar *description;

  /**
   * (no documentation provided)
   */
  xmlChar *credits;

  /**
   * (no documentation provided)
   */
  xmlChar *code;

  /**
   * (no documentation provided)
   */
  long long courseId;

  /**
   * (no documentation provided)
   */
  xmlChar *name;
};

/**
 * Reads a CourseDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The CourseDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_courseDTO *xmlTextReaderReadNs0CourseDTOType(xmlTextReaderPtr reader);

/**
 * Writes a CourseDTO to XML.
 *
 * @param writer The XML writer.
 * @param _courseDTO The CourseDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0CourseDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_courseDTO *_courseDTO);

/**
 * Frees the elements of a CourseDTO.
 *
 * @param _courseDTO The CourseDTO to free.
 */
static void freeNs0CourseDTOType(struct course_genie_backend_ns0_courseDTO *_courseDTO);

#endif /* DEF_course_genie_backend_ns0_courseDTO_H */
#ifndef DEF_course_genie_backend_ns0_gradeDTO_H
#define DEF_course_genie_backend_ns0_gradeDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_gradeDTO {


  /**
   * (no documentation provided)
   */
  long long assessmentId;

  /**
   * (no documentation provided)
   */
  double score;

  /**
   * (no documentation provided)
   */
  xmlChar *studentId;

  /**
   * (no documentation provided)
   */
  long long gradeId;
};

/**
 * Reads a GradeDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The GradeDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_gradeDTO *xmlTextReaderReadNs0GradeDTOType(xmlTextReaderPtr reader);

/**
 * Writes a GradeDTO to XML.
 *
 * @param writer The XML writer.
 * @param _gradeDTO The GradeDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0GradeDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_gradeDTO *_gradeDTO);

/**
 * Frees the elements of a GradeDTO.
 *
 * @param _gradeDTO The GradeDTO to free.
 */
static void freeNs0GradeDTOType(struct course_genie_backend_ns0_gradeDTO *_gradeDTO);

#endif /* DEF_course_genie_backend_ns0_gradeDTO_H */
#ifndef DEF_course_genie_backend_ns0_sectionDTO_H
#define DEF_course_genie_backend_ns0_sectionDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_sectionDTO {


  /**
   * (no documentation provided)
   */
  xmlChar *class_number;

  /**
   * (no documentation provided)
   */
  xmlChar *code;

  /**
   * (no documentation provided)
   */
  long long professorId;

  /**
   * (no documentation provided)
   */
  int configured;

  /**
   * (no documentation provided)
   */
  struct course_genie_backend_ns0_assessmentDTO *assessments;

  /**
   * Size of the assessments array.
   */
  int _sizeof_assessments;

  /**
   * (no documentation provided)
   */
  xmlChar *term;

  /**
   * (no documentation provided)
   */
  xmlChar *teachingMethodology;

  /**
   * (no documentation provided)
   */
  long long courseId;

  /**
   * (no documentation provided)
   */
  long long sectionId;
};

/**
 * Reads a SectionDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The SectionDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_sectionDTO *xmlTextReaderReadNs0SectionDTOType(xmlTextReaderPtr reader);

/**
 * Writes a SectionDTO to XML.
 *
 * @param writer The XML writer.
 * @param _sectionDTO The SectionDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0SectionDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_sectionDTO *_sectionDTO);

/**
 * Frees the elements of a SectionDTO.
 *
 * @param _sectionDTO The SectionDTO to free.
 */
static void freeNs0SectionDTOType(struct course_genie_backend_ns0_sectionDTO *_sectionDTO);

#endif /* DEF_course_genie_backend_ns0_sectionDTO_H */
#ifndef DEF_course_genie_backend_ns0_authQuery_H
#define DEF_course_genie_backend_ns0_authQuery_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_authQuery {


  /**
   * (no documentation provided)
   */
  xmlChar *username;

  /**
   * (no documentation provided)
   */
  xmlChar *password;
};

/**
 * Reads a AuthQuery from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The AuthQuery, or NULL in case of error.
 */
static struct course_genie_backend_ns0_authQuery *xmlTextReaderReadNs0AuthQueryType(xmlTextReaderPtr reader);

/**
 * Writes a AuthQuery to XML.
 *
 * @param writer The XML writer.
 * @param _authQuery The AuthQuery to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0AuthQueryType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_authQuery *_authQuery);

/**
 * Frees the elements of a AuthQuery.
 *
 * @param _authQuery The AuthQuery to free.
 */
static void freeNs0AuthQueryType(struct course_genie_backend_ns0_authQuery *_authQuery);

#endif /* DEF_course_genie_backend_ns0_authQuery_H */
#ifndef DEF_course_genie_backend_ns0_studentDTO_H
#define DEF_course_genie_backend_ns0_studentDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_studentDTO {


  /**
   * (no documentation provided)
   */
  xmlChar *lastName;

  /**
   * (no documentation provided)
   */
  xmlChar *firstName;

  /**
   * (no documentation provided)
   */
  xmlChar *email;

  /**
   * (no documentation provided)
   */
  xmlChar *studentId;
};

/**
 * Reads a StudentDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The StudentDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_studentDTO *xmlTextReaderReadNs0StudentDTOType(xmlTextReaderPtr reader);

/**
 * Writes a StudentDTO to XML.
 *
 * @param writer The XML writer.
 * @param _studentDTO The StudentDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0StudentDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_studentDTO *_studentDTO);

/**
 * Frees the elements of a StudentDTO.
 *
 * @param _studentDTO The StudentDTO to free.
 */
static void freeNs0StudentDTOType(struct course_genie_backend_ns0_studentDTO *_studentDTO);

#endif /* DEF_course_genie_backend_ns0_studentDTO_H */
#ifndef DEF_course_genie_backend_ns0_syllabusDTO_H
#define DEF_course_genie_backend_ns0_syllabusDTO_H

/**
 * (no documentation provided)
 */
struct course_genie_backend_ns0_syllabusDTO {


  /**
   * (no documentation provided)
   */
  long long sectionId;

  /**
   * (no documentation provided)
   */
  xmlChar *content;

  /**
   * (no documentation provided)
   */
  long long syllabusId;
};

/**
 * Reads a SyllabusDTO from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The SyllabusDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_syllabusDTO *xmlTextReaderReadNs0SyllabusDTOType(xmlTextReaderPtr reader);

/**
 * Writes a SyllabusDTO to XML.
 *
 * @param writer The XML writer.
 * @param _syllabusDTO The SyllabusDTO to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0SyllabusDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_syllabusDTO *_syllabusDTO);

/**
 * Frees the elements of a SyllabusDTO.
 *
 * @param _syllabusDTO The SyllabusDTO to free.
 */
static void freeNs0SyllabusDTOType(struct course_genie_backend_ns0_syllabusDTO *_syllabusDTO);

#endif /* DEF_course_genie_backend_ns0_syllabusDTO_H */
#ifndef DEF_course_genie_backend_ns0_assessment_M
#define DEF_course_genie_backend_ns0_assessment_M

/**
 * Reads a Assessment from XML. The reader is assumed to be at the start element.
 *
 * @return the Assessment, or NULL in case of error.
 */
static struct course_genie_backend_ns0_assessment *xmlTextReaderReadNs0AssessmentType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_assessment *_assessment = calloc(1, sizeof(struct course_genie_backend_ns0_assessment));




  return _assessment;
}

/**
 * Writes a Assessment to XML.
 *
 * @param writer The XML writer.
 * @param _assessment The Assessment to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0AssessmentType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_assessment *_assessment) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;

  return totalBytes;
}

/**
 * Frees the elements of a Assessment.
 *
 * @param _assessment The Assessment to free.
 */
static void freeNs0AssessmentType(struct course_genie_backend_ns0_assessment *_assessment) {
  int i;
}
#endif /* DEF_course_genie_backend_ns0_assessment_M */
#ifndef DEF_course_genie_backend_ns0_assessmentDTO_M
#define DEF_course_genie_backend_ns0_assessmentDTO_M

/**
 * Reads a AssessmentDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the AssessmentDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_assessmentDTO *xmlTextReaderReadNs0AssessmentDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_assessmentDTO *_assessmentDTO = calloc(1, sizeof(struct course_genie_backend_ns0_assessmentDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0AssessmentDTOType(_assessmentDTO);
        free(_assessmentDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "week", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}week of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}week of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->week = *((int*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "category", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}category of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}category of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->category = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "clos", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}clos of type {}CLODTO.\n");
#endif
        _child_accessor = xmlTextReaderReadNs0CLODTOType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}clos of type {}CLODTO.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->clos = realloc(_assessmentDTO->clos, (_assessmentDTO->_sizeof_clos + 1) * sizeof(struct course_genie_backend_ns0_CLODTO));
        memcpy(&(_assessmentDTO->clos[_assessmentDTO->_sizeof_clos++]), _child_accessor, sizeof(struct course_genie_backend_ns0_CLODTO));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "shortName", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}shortName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}shortName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->shortName = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "sectionId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}sectionId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}sectionId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->sectionId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "name", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->name = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "maxPoints", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}maxPoints of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}maxPoints of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->maxPoints = *((int*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "assessmentId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}assessmentId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}assessmentId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AssessmentDTOType(_assessmentDTO);
          free(_assessmentDTO);
          return NULL;
        }

        _assessmentDTO->assessmentId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}assessmentDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}assessmentDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _assessmentDTO;
}

/**
 * Writes a AssessmentDTO to XML.
 *
 * @param writer The XML writer.
 * @param _assessmentDTO The AssessmentDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0AssessmentDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_assessmentDTO *_assessmentDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "week", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}week. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}week...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, &(_assessmentDTO->week));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}week. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}week. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_assessmentDTO->category != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "category", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}category. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}category...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_assessmentDTO->category));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}category. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}category. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  for (i = 0; i < _assessmentDTO->_sizeof_clos; i++) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "clos", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}clos. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {}CLODTO for element {}clos...\n");
#endif
    status = xmlTextWriterWriteNs0CLODTOType(writer, &(_assessmentDTO->clos[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {}CLODTO for element {}clos. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}clos. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_assessmentDTO->shortName != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "shortName", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}shortName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}shortName...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_assessmentDTO->shortName));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}shortName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}shortName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}sectionId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_assessmentDTO->sectionId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_assessmentDTO->name != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "name", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}name...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_assessmentDTO->name));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "maxPoints", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}maxPoints. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}maxPoints...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, &(_assessmentDTO->maxPoints));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}maxPoints. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}maxPoints. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "assessmentId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}assessmentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}assessmentId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_assessmentDTO->assessmentId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}assessmentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}assessmentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a AssessmentDTO.
 *
 * @param _assessmentDTO The AssessmentDTO to free.
 */
static void freeNs0AssessmentDTOType(struct course_genie_backend_ns0_assessmentDTO *_assessmentDTO) {
  int i;
  if (_assessmentDTO->category != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor category of type course_genie_backend_ns0_assessmentDTO...\n");
#endif
    freeXsStringType(_assessmentDTO->category);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor category of type course_genie_backend_ns0_assessmentDTO...\n");
#endif
    free(_assessmentDTO->category);
  }
  if (_assessmentDTO->clos != NULL) {
    for (i = 0; i < _assessmentDTO->_sizeof_clos; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor clos[%i] of type course_genie_backend_ns0_assessmentDTO...\n", i);
#endif
      freeNs0CLODTOType(&(_assessmentDTO->clos[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor clos of type course_genie_backend_ns0_assessmentDTO...\n");
#endif
    free(_assessmentDTO->clos);
  }
  if (_assessmentDTO->shortName != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor shortName of type course_genie_backend_ns0_assessmentDTO...\n");
#endif
    freeXsStringType(_assessmentDTO->shortName);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor shortName of type course_genie_backend_ns0_assessmentDTO...\n");
#endif
    free(_assessmentDTO->shortName);
  }
  if (_assessmentDTO->name != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor name of type course_genie_backend_ns0_assessmentDTO...\n");
#endif
    freeXsStringType(_assessmentDTO->name);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor name of type course_genie_backend_ns0_assessmentDTO...\n");
#endif
    free(_assessmentDTO->name);
  }
}
#endif /* DEF_course_genie_backend_ns0_assessmentDTO_M */
#ifndef DEF_course_genie_backend_ns0_benchmarkDTO_M
#define DEF_course_genie_backend_ns0_benchmarkDTO_M

/**
 * Reads a BenchmarkDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the BenchmarkDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_benchmarkDTO *xmlTextReaderReadNs0BenchmarkDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_benchmarkDTO *_benchmarkDTO = calloc(1, sizeof(struct course_genie_backend_ns0_benchmarkDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0BenchmarkDTOType(_benchmarkDTO);
        free(_benchmarkDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "description", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BenchmarkDTOType(_benchmarkDTO);
          free(_benchmarkDTO);
          return NULL;
        }

        _benchmarkDTO->description = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "percentage", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}percentage of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}percentage of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BenchmarkDTOType(_benchmarkDTO);
          free(_benchmarkDTO);
          return NULL;
        }

        _benchmarkDTO->percentage = *((int*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "benchmarkType", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}benchmarkType of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}benchmarkType of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BenchmarkDTOType(_benchmarkDTO);
          free(_benchmarkDTO);
          return NULL;
        }

        _benchmarkDTO->benchmarkType = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "benchmarkId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}benchmarkId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}benchmarkId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BenchmarkDTOType(_benchmarkDTO);
          free(_benchmarkDTO);
          return NULL;
        }

        _benchmarkDTO->benchmarkId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "threshold", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}threshold of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
        _child_accessor = xmlTextReaderReadXsIntType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}threshold of type {http://www.w3.org/2001/XMLSchema}int.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BenchmarkDTOType(_benchmarkDTO);
          free(_benchmarkDTO);
          return NULL;
        }

        _benchmarkDTO->threshold = *((int*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}benchmarkDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}benchmarkDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _benchmarkDTO;
}

/**
 * Writes a BenchmarkDTO to XML.
 *
 * @param writer The XML writer.
 * @param _benchmarkDTO The BenchmarkDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0BenchmarkDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_benchmarkDTO *_benchmarkDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_benchmarkDTO->description != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "description", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}description...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_benchmarkDTO->description));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "percentage", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}percentage. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}percentage...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, &(_benchmarkDTO->percentage));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}percentage. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}percentage. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_benchmarkDTO->benchmarkType != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "benchmarkType", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}benchmarkType. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}benchmarkType...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_benchmarkDTO->benchmarkType));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}benchmarkType. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}benchmarkType. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "benchmarkId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}benchmarkId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}benchmarkId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_benchmarkDTO->benchmarkId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}benchmarkId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}benchmarkId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "threshold", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}threshold. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}int for element {}threshold...\n");
#endif
    status = xmlTextWriterWriteXsIntType(writer, &(_benchmarkDTO->threshold));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}int for element {}threshold. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}threshold. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a BenchmarkDTO.
 *
 * @param _benchmarkDTO The BenchmarkDTO to free.
 */
static void freeNs0BenchmarkDTOType(struct course_genie_backend_ns0_benchmarkDTO *_benchmarkDTO) {
  int i;
  if (_benchmarkDTO->description != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor description of type course_genie_backend_ns0_benchmarkDTO...\n");
#endif
    freeXsStringType(_benchmarkDTO->description);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor description of type course_genie_backend_ns0_benchmarkDTO...\n");
#endif
    free(_benchmarkDTO->description);
  }
  if (_benchmarkDTO->benchmarkType != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor benchmarkType of type course_genie_backend_ns0_benchmarkDTO...\n");
#endif
    freeXsStringType(_benchmarkDTO->benchmarkType);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor benchmarkType of type course_genie_backend_ns0_benchmarkDTO...\n");
#endif
    free(_benchmarkDTO->benchmarkType);
  }
}
#endif /* DEF_course_genie_backend_ns0_benchmarkDTO_M */
#ifndef DEF_course_genie_backend_ns0_bookDTO_M
#define DEF_course_genie_backend_ns0_bookDTO_M

/**
 * Reads a BookDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the BookDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_bookDTO *xmlTextReaderReadNs0BookDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_bookDTO *_bookDTO = calloc(1, sizeof(struct course_genie_backend_ns0_bookDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0BookDTOType(_bookDTO);
        free(_bookDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "id", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}id of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}id of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BookDTOType(_bookDTO);
          free(_bookDTO);
          return NULL;
        }

        _bookDTO->id = ((long long*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "isbn", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}isbn of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}isbn of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BookDTOType(_bookDTO);
          free(_bookDTO);
          return NULL;
        }

        _bookDTO->isbn = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "title", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}title of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}title of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BookDTOType(_bookDTO);
          free(_bookDTO);
          return NULL;
        }

        _bookDTO->title = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "url", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}url of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}url of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BookDTOType(_bookDTO);
          free(_bookDTO);
          return NULL;
        }

        _bookDTO->url = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "sectionIds", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}sectionIds of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}sectionIds of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BookDTOType(_bookDTO);
          free(_bookDTO);
          return NULL;
        }

        _bookDTO->sectionIds = realloc(_bookDTO->sectionIds, (_bookDTO->_sizeof_sectionIds + 1) * sizeof(long long));
        memcpy(&(_bookDTO->sectionIds[_bookDTO->_sizeof_sectionIds++]), _child_accessor, sizeof(long long));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "requiredReading", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}requiredReading of type {http://www.w3.org/2001/XMLSchema}boolean.\n");
#endif
        _child_accessor = xmlTextReaderReadXsBooleanType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}requiredReading of type {http://www.w3.org/2001/XMLSchema}boolean.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BookDTOType(_bookDTO);
          free(_bookDTO);
          return NULL;
        }

        _bookDTO->requiredReading = *((int*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "suggestedReading", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}suggestedReading of type {http://www.w3.org/2001/XMLSchema}boolean.\n");
#endif
        _child_accessor = xmlTextReaderReadXsBooleanType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}suggestedReading of type {http://www.w3.org/2001/XMLSchema}boolean.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0BookDTOType(_bookDTO);
          free(_bookDTO);
          return NULL;
        }

        _bookDTO->suggestedReading = *((int*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}bookDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}bookDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _bookDTO;
}

/**
 * Writes a BookDTO to XML.
 *
 * @param writer The XML writer.
 * @param _bookDTO The BookDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0BookDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_bookDTO *_bookDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_bookDTO->id != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "id", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}id...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, (_bookDTO->id));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_bookDTO->isbn != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "isbn", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}isbn. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}isbn...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_bookDTO->isbn));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}isbn. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}isbn. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_bookDTO->title != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "title", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}title. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}title...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_bookDTO->title));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}title. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}title. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_bookDTO->url != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "url", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}url. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}url...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_bookDTO->url));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}url. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}url. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  for (i = 0; i < _bookDTO->_sizeof_sectionIds; i++) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionIds", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}sectionIds. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}sectionIds...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_bookDTO->sectionIds[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}sectionIds. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}sectionIds. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "requiredReading", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}requiredReading. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}boolean for element {}requiredReading...\n");
#endif
    status = xmlTextWriterWriteXsBooleanType(writer, &(_bookDTO->requiredReading));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}boolean for element {}requiredReading. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}requiredReading. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "suggestedReading", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}suggestedReading. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}boolean for element {}suggestedReading...\n");
#endif
    status = xmlTextWriterWriteXsBooleanType(writer, &(_bookDTO->suggestedReading));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}boolean for element {}suggestedReading. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}suggestedReading. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a BookDTO.
 *
 * @param _bookDTO The BookDTO to free.
 */
static void freeNs0BookDTOType(struct course_genie_backend_ns0_bookDTO *_bookDTO) {
  int i;
  if (_bookDTO->id != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor id of type course_genie_backend_ns0_bookDTO...\n");
#endif
    freeXsLongType(_bookDTO->id);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor id of type course_genie_backend_ns0_bookDTO...\n");
#endif
    free(_bookDTO->id);
  }
  if (_bookDTO->isbn != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor isbn of type course_genie_backend_ns0_bookDTO...\n");
#endif
    freeXsStringType(_bookDTO->isbn);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor isbn of type course_genie_backend_ns0_bookDTO...\n");
#endif
    free(_bookDTO->isbn);
  }
  if (_bookDTO->title != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor title of type course_genie_backend_ns0_bookDTO...\n");
#endif
    freeXsStringType(_bookDTO->title);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor title of type course_genie_backend_ns0_bookDTO...\n");
#endif
    free(_bookDTO->title);
  }
  if (_bookDTO->url != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor url of type course_genie_backend_ns0_bookDTO...\n");
#endif
    freeXsStringType(_bookDTO->url);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor url of type course_genie_backend_ns0_bookDTO...\n");
#endif
    free(_bookDTO->url);
  }
  if (_bookDTO->sectionIds != NULL) {
    for (i = 0; i < _bookDTO->_sizeof_sectionIds; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor sectionIds[%i] of type course_genie_backend_ns0_bookDTO...\n", i);
#endif
      freeXsLongType(&(_bookDTO->sectionIds[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor sectionIds of type course_genie_backend_ns0_bookDTO...\n");
#endif
    free(_bookDTO->sectionIds);
  }
}
#endif /* DEF_course_genie_backend_ns0_bookDTO_M */
#ifndef DEF_course_genie_backend_ns0_CLODTO_M
#define DEF_course_genie_backend_ns0_CLODTO_M

/**
 * Reads a CLODTO from XML. The reader is assumed to be at the start element.
 *
 * @return the CLODTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_CLODTO *xmlTextReaderReadNs0CLODTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_CLODTO *_cLODTO = calloc(1, sizeof(struct course_genie_backend_ns0_CLODTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0CLODTOType(_cLODTO);
        free(_cLODTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "name", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CLODTOType(_cLODTO);
          free(_cLODTO);
          return NULL;
        }

        _cLODTO->name = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "description", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CLODTOType(_cLODTO);
          free(_cLODTO);
          return NULL;
        }

        _cLODTO->description = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "courseId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}courseId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}courseId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CLODTOType(_cLODTO);
          free(_cLODTO);
          return NULL;
        }

        _cLODTO->courseId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "cloId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}cloId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}cloId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CLODTOType(_cLODTO);
          free(_cLODTO);
          return NULL;
        }

        _cLODTO->cloId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}CLODTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}CLODTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _cLODTO;
}

/**
 * Writes a CLODTO to XML.
 *
 * @param writer The XML writer.
 * @param _cLODTO The CLODTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0CLODTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_CLODTO *_cLODTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_cLODTO->name != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "name", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}name...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_cLODTO->name));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_cLODTO->description != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "description", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}description...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_cLODTO->description));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "courseId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}courseId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_cLODTO->courseId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "cloId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}cloId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}cloId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_cLODTO->cloId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}cloId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}cloId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a CLODTO.
 *
 * @param _cLODTO The CLODTO to free.
 */
static void freeNs0CLODTOType(struct course_genie_backend_ns0_CLODTO *_cLODTO) {
  int i;
  if (_cLODTO->name != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor name of type course_genie_backend_ns0_CLODTO...\n");
#endif
    freeXsStringType(_cLODTO->name);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor name of type course_genie_backend_ns0_CLODTO...\n");
#endif
    free(_cLODTO->name);
  }
  if (_cLODTO->description != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor description of type course_genie_backend_ns0_CLODTO...\n");
#endif
    freeXsStringType(_cLODTO->description);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor description of type course_genie_backend_ns0_CLODTO...\n");
#endif
    free(_cLODTO->description);
  }
}
#endif /* DEF_course_genie_backend_ns0_CLODTO_M */
#ifndef DEF_course_genie_backend_ns0_courseDTO_M
#define DEF_course_genie_backend_ns0_courseDTO_M

/**
 * Reads a CourseDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the CourseDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_courseDTO *xmlTextReaderReadNs0CourseDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_courseDTO *_courseDTO = calloc(1, sizeof(struct course_genie_backend_ns0_courseDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0CourseDTOType(_courseDTO);
        free(_courseDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "clos", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}clos of type {}CLODTO.\n");
#endif
        _child_accessor = xmlTextReaderReadNs0CLODTOType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}clos of type {}CLODTO.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CourseDTOType(_courseDTO);
          free(_courseDTO);
          return NULL;
        }

        _courseDTO->clos = realloc(_courseDTO->clos, (_courseDTO->_sizeof_clos + 1) * sizeof(struct course_genie_backend_ns0_CLODTO));
        memcpy(&(_courseDTO->clos[_courseDTO->_sizeof_clos++]), _child_accessor, sizeof(struct course_genie_backend_ns0_CLODTO));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "sections", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}sections of type {}sectionDTO.\n");
#endif
        _child_accessor = xmlTextReaderReadNs0SectionDTOType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}sections of type {}sectionDTO.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CourseDTOType(_courseDTO);
          free(_courseDTO);
          return NULL;
        }

        _courseDTO->sections = realloc(_courseDTO->sections, (_courseDTO->_sizeof_sections + 1) * sizeof(struct course_genie_backend_ns0_sectionDTO));
        memcpy(&(_courseDTO->sections[_courseDTO->_sizeof_sections++]), _child_accessor, sizeof(struct course_genie_backend_ns0_sectionDTO));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "description", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CourseDTOType(_courseDTO);
          free(_courseDTO);
          return NULL;
        }

        _courseDTO->description = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "credits", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}credits of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}credits of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CourseDTOType(_courseDTO);
          free(_courseDTO);
          return NULL;
        }

        _courseDTO->credits = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "code", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CourseDTOType(_courseDTO);
          free(_courseDTO);
          return NULL;
        }

        _courseDTO->code = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "courseId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}courseId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}courseId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CourseDTOType(_courseDTO);
          free(_courseDTO);
          return NULL;
        }

        _courseDTO->courseId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "name", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0CourseDTOType(_courseDTO);
          free(_courseDTO);
          return NULL;
        }

        _courseDTO->name = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}courseDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}courseDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _courseDTO;
}

/**
 * Writes a CourseDTO to XML.
 *
 * @param writer The XML writer.
 * @param _courseDTO The CourseDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0CourseDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_courseDTO *_courseDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  for (i = 0; i < _courseDTO->_sizeof_clos; i++) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "clos", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}clos. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {}CLODTO for element {}clos...\n");
#endif
    status = xmlTextWriterWriteNs0CLODTOType(writer, &(_courseDTO->clos[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {}CLODTO for element {}clos. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}clos. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  for (i = 0; i < _courseDTO->_sizeof_sections; i++) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sections", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}sections. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {}sectionDTO for element {}sections...\n");
#endif
    status = xmlTextWriterWriteNs0SectionDTOType(writer, &(_courseDTO->sections[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {}sectionDTO for element {}sections. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}sections. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_courseDTO->description != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "description", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}description...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_courseDTO->description));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}description. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_courseDTO->credits != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "credits", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}credits. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}credits...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_courseDTO->credits));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}credits. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}credits. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_courseDTO->code != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "code", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}code...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_courseDTO->code));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "courseId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}courseId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_courseDTO->courseId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_courseDTO->name != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "name", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}name...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_courseDTO->name));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}name. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a CourseDTO.
 *
 * @param _courseDTO The CourseDTO to free.
 */
static void freeNs0CourseDTOType(struct course_genie_backend_ns0_courseDTO *_courseDTO) {
  int i;
  if (_courseDTO->clos != NULL) {
    for (i = 0; i < _courseDTO->_sizeof_clos; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor clos[%i] of type course_genie_backend_ns0_courseDTO...\n", i);
#endif
      freeNs0CLODTOType(&(_courseDTO->clos[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor clos of type course_genie_backend_ns0_courseDTO...\n");
#endif
    free(_courseDTO->clos);
  }
  if (_courseDTO->sections != NULL) {
    for (i = 0; i < _courseDTO->_sizeof_sections; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor sections[%i] of type course_genie_backend_ns0_courseDTO...\n", i);
#endif
      freeNs0SectionDTOType(&(_courseDTO->sections[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor sections of type course_genie_backend_ns0_courseDTO...\n");
#endif
    free(_courseDTO->sections);
  }
  if (_courseDTO->description != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor description of type course_genie_backend_ns0_courseDTO...\n");
#endif
    freeXsStringType(_courseDTO->description);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor description of type course_genie_backend_ns0_courseDTO...\n");
#endif
    free(_courseDTO->description);
  }
  if (_courseDTO->credits != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor credits of type course_genie_backend_ns0_courseDTO...\n");
#endif
    freeXsStringType(_courseDTO->credits);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor credits of type course_genie_backend_ns0_courseDTO...\n");
#endif
    free(_courseDTO->credits);
  }
  if (_courseDTO->code != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor code of type course_genie_backend_ns0_courseDTO...\n");
#endif
    freeXsStringType(_courseDTO->code);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor code of type course_genie_backend_ns0_courseDTO...\n");
#endif
    free(_courseDTO->code);
  }
  if (_courseDTO->name != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor name of type course_genie_backend_ns0_courseDTO...\n");
#endif
    freeXsStringType(_courseDTO->name);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor name of type course_genie_backend_ns0_courseDTO...\n");
#endif
    free(_courseDTO->name);
  }
}
#endif /* DEF_course_genie_backend_ns0_courseDTO_M */
#ifndef DEF_course_genie_backend_ns0_gradeDTO_M
#define DEF_course_genie_backend_ns0_gradeDTO_M

/**
 * Reads a GradeDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the GradeDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_gradeDTO *xmlTextReaderReadNs0GradeDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_gradeDTO *_gradeDTO = calloc(1, sizeof(struct course_genie_backend_ns0_gradeDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0GradeDTOType(_gradeDTO);
        free(_gradeDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "assessmentId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}assessmentId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}assessmentId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0GradeDTOType(_gradeDTO);
          free(_gradeDTO);
          return NULL;
        }

        _gradeDTO->assessmentId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "score", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}score of type {http://www.w3.org/2001/XMLSchema}double.\n");
#endif
        _child_accessor = xmlTextReaderReadXsDoubleType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}score of type {http://www.w3.org/2001/XMLSchema}double.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0GradeDTOType(_gradeDTO);
          free(_gradeDTO);
          return NULL;
        }

        _gradeDTO->score = *((double*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "studentId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0GradeDTOType(_gradeDTO);
          free(_gradeDTO);
          return NULL;
        }

        _gradeDTO->studentId = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "gradeId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}gradeId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}gradeId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0GradeDTOType(_gradeDTO);
          free(_gradeDTO);
          return NULL;
        }

        _gradeDTO->gradeId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}gradeDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}gradeDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _gradeDTO;
}

/**
 * Writes a GradeDTO to XML.
 *
 * @param writer The XML writer.
 * @param _gradeDTO The GradeDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0GradeDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_gradeDTO *_gradeDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "assessmentId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}assessmentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}assessmentId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_gradeDTO->assessmentId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}assessmentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}assessmentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "score", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}score. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}double for element {}score...\n");
#endif
    status = xmlTextWriterWriteXsDoubleType(writer, &(_gradeDTO->score));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}double for element {}score. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}score. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_gradeDTO->studentId != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "studentId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}studentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}studentId...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_gradeDTO->studentId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}studentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}studentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "gradeId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}gradeId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}gradeId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_gradeDTO->gradeId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}gradeId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}gradeId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a GradeDTO.
 *
 * @param _gradeDTO The GradeDTO to free.
 */
static void freeNs0GradeDTOType(struct course_genie_backend_ns0_gradeDTO *_gradeDTO) {
  int i;
  if (_gradeDTO->studentId != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor studentId of type course_genie_backend_ns0_gradeDTO...\n");
#endif
    freeXsStringType(_gradeDTO->studentId);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor studentId of type course_genie_backend_ns0_gradeDTO...\n");
#endif
    free(_gradeDTO->studentId);
  }
}
#endif /* DEF_course_genie_backend_ns0_gradeDTO_M */
#ifndef DEF_course_genie_backend_ns0_sectionDTO_M
#define DEF_course_genie_backend_ns0_sectionDTO_M

/**
 * Reads a SectionDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the SectionDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_sectionDTO *xmlTextReaderReadNs0SectionDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_sectionDTO *_sectionDTO = calloc(1, sizeof(struct course_genie_backend_ns0_sectionDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0SectionDTOType(_sectionDTO);
        free(_sectionDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "class_number", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}class_number of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}class_number of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->class_number = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "code", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->code = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "professorId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}professorId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}professorId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->professorId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "configured", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}configured of type {http://www.w3.org/2001/XMLSchema}boolean.\n");
#endif
        _child_accessor = xmlTextReaderReadXsBooleanType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}configured of type {http://www.w3.org/2001/XMLSchema}boolean.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->configured = *((int*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "assessments", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}assessments of type {}assessmentDTO.\n");
#endif
        _child_accessor = xmlTextReaderReadNs0AssessmentDTOType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}assessments of type {}assessmentDTO.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->assessments = realloc(_sectionDTO->assessments, (_sectionDTO->_sizeof_assessments + 1) * sizeof(struct course_genie_backend_ns0_assessmentDTO));
        memcpy(&(_sectionDTO->assessments[_sectionDTO->_sizeof_assessments++]), _child_accessor, sizeof(struct course_genie_backend_ns0_assessmentDTO));
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "term", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}term of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}term of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->term = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "teachingMethodology", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}teachingMethodology of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}teachingMethodology of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->teachingMethodology = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "courseId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}courseId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}courseId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->courseId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "sectionId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}sectionId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}sectionId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SectionDTOType(_sectionDTO);
          free(_sectionDTO);
          return NULL;
        }

        _sectionDTO->sectionId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}sectionDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}sectionDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _sectionDTO;
}

/**
 * Writes a SectionDTO to XML.
 *
 * @param writer The XML writer.
 * @param _sectionDTO The SectionDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0SectionDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_sectionDTO *_sectionDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_sectionDTO->class_number != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "class_number", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}class_number. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}class_number...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_sectionDTO->class_number));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}class_number. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}class_number. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_sectionDTO->code != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "code", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}code...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_sectionDTO->code));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "professorId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}professorId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}professorId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_sectionDTO->professorId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}professorId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}professorId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "configured", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}configured. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}boolean for element {}configured...\n");
#endif
    status = xmlTextWriterWriteXsBooleanType(writer, &(_sectionDTO->configured));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}boolean for element {}configured. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}configured. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  for (i = 0; i < _sectionDTO->_sizeof_assessments; i++) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "assessments", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}assessments. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {}assessmentDTO for element {}assessments...\n");
#endif
    status = xmlTextWriterWriteNs0AssessmentDTOType(writer, &(_sectionDTO->assessments[i]));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {}assessmentDTO for element {}assessments. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}assessments. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_sectionDTO->term != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "term", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}term. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}term...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_sectionDTO->term));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}term. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}term. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_sectionDTO->teachingMethodology != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "teachingMethodology", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}teachingMethodology. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}teachingMethodology...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_sectionDTO->teachingMethodology));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}teachingMethodology. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}teachingMethodology. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "courseId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}courseId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_sectionDTO->courseId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}courseId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}sectionId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_sectionDTO->sectionId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a SectionDTO.
 *
 * @param _sectionDTO The SectionDTO to free.
 */
static void freeNs0SectionDTOType(struct course_genie_backend_ns0_sectionDTO *_sectionDTO) {
  int i;
  if (_sectionDTO->class_number != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor class_number of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    freeXsStringType(_sectionDTO->class_number);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor class_number of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    free(_sectionDTO->class_number);
  }
  if (_sectionDTO->code != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor code of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    freeXsStringType(_sectionDTO->code);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor code of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    free(_sectionDTO->code);
  }
  if (_sectionDTO->assessments != NULL) {
    for (i = 0; i < _sectionDTO->_sizeof_assessments; i++) {
#if DEBUG_ENUNCIATE > 1
      printf("Freeing accessor assessments[%i] of type course_genie_backend_ns0_sectionDTO...\n", i);
#endif
      freeNs0AssessmentDTOType(&(_sectionDTO->assessments[i]));
    }
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor assessments of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    free(_sectionDTO->assessments);
  }
  if (_sectionDTO->term != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor term of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    freeXsStringType(_sectionDTO->term);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor term of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    free(_sectionDTO->term);
  }
  if (_sectionDTO->teachingMethodology != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor teachingMethodology of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    freeXsStringType(_sectionDTO->teachingMethodology);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor teachingMethodology of type course_genie_backend_ns0_sectionDTO...\n");
#endif
    free(_sectionDTO->teachingMethodology);
  }
}
#endif /* DEF_course_genie_backend_ns0_sectionDTO_M */
#ifndef DEF_course_genie_backend_ns0_authQuery_M
#define DEF_course_genie_backend_ns0_authQuery_M

/**
 * Reads a AuthQuery from XML. The reader is assumed to be at the start element.
 *
 * @return the AuthQuery, or NULL in case of error.
 */
static struct course_genie_backend_ns0_authQuery *xmlTextReaderReadNs0AuthQueryType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_authQuery *_authQuery = calloc(1, sizeof(struct course_genie_backend_ns0_authQuery));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0AuthQueryType(_authQuery);
        free(_authQuery);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "username", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}username of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}username of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AuthQueryType(_authQuery);
          free(_authQuery);
          return NULL;
        }

        _authQuery->username = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "password", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0AuthQueryType(_authQuery);
          free(_authQuery);
          return NULL;
        }

        _authQuery->password = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}authQuery.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}authQuery. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _authQuery;
}

/**
 * Writes a AuthQuery to XML.
 *
 * @param writer The XML writer.
 * @param _authQuery The AuthQuery to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0AuthQueryType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_authQuery *_authQuery) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_authQuery->username != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "username", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}username. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}username...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_authQuery->username));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}username. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}username. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_authQuery->password != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "password", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}password...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_authQuery->password));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}password. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a AuthQuery.
 *
 * @param _authQuery The AuthQuery to free.
 */
static void freeNs0AuthQueryType(struct course_genie_backend_ns0_authQuery *_authQuery) {
  int i;
  if (_authQuery->username != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor username of type course_genie_backend_ns0_authQuery...\n");
#endif
    freeXsStringType(_authQuery->username);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor username of type course_genie_backend_ns0_authQuery...\n");
#endif
    free(_authQuery->username);
  }
  if (_authQuery->password != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor password of type course_genie_backend_ns0_authQuery...\n");
#endif
    freeXsStringType(_authQuery->password);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor password of type course_genie_backend_ns0_authQuery...\n");
#endif
    free(_authQuery->password);
  }
}
#endif /* DEF_course_genie_backend_ns0_authQuery_M */
#ifndef DEF_course_genie_backend_ns0_studentDTO_M
#define DEF_course_genie_backend_ns0_studentDTO_M

/**
 * Reads a StudentDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the StudentDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_studentDTO *xmlTextReaderReadNs0StudentDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_studentDTO *_studentDTO = calloc(1, sizeof(struct course_genie_backend_ns0_studentDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0StudentDTOType(_studentDTO);
        free(_studentDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "lastName", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0StudentDTOType(_studentDTO);
          free(_studentDTO);
          return NULL;
        }

        _studentDTO->lastName = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "firstName", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0StudentDTOType(_studentDTO);
          free(_studentDTO);
          return NULL;
        }

        _studentDTO->firstName = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "email", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0StudentDTOType(_studentDTO);
          free(_studentDTO);
          return NULL;
        }

        _studentDTO->email = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "studentId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0StudentDTOType(_studentDTO);
          free(_studentDTO);
          return NULL;
        }

        _studentDTO->studentId = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}studentDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}studentDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _studentDTO;
}

/**
 * Writes a StudentDTO to XML.
 *
 * @param writer The XML writer.
 * @param _studentDTO The StudentDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0StudentDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_studentDTO *_studentDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_studentDTO->lastName != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "lastName", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}lastName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}lastName...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_studentDTO->lastName));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}lastName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}lastName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_studentDTO->firstName != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "firstName", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}firstName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}firstName...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_studentDTO->firstName));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}firstName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}firstName. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_studentDTO->email != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "email", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}email. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}email...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_studentDTO->email));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}email. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}email. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_studentDTO->studentId != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "studentId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}studentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}studentId...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_studentDTO->studentId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}studentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}studentId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a StudentDTO.
 *
 * @param _studentDTO The StudentDTO to free.
 */
static void freeNs0StudentDTOType(struct course_genie_backend_ns0_studentDTO *_studentDTO) {
  int i;
  if (_studentDTO->lastName != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor lastName of type course_genie_backend_ns0_studentDTO...\n");
#endif
    freeXsStringType(_studentDTO->lastName);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor lastName of type course_genie_backend_ns0_studentDTO...\n");
#endif
    free(_studentDTO->lastName);
  }
  if (_studentDTO->firstName != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor firstName of type course_genie_backend_ns0_studentDTO...\n");
#endif
    freeXsStringType(_studentDTO->firstName);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor firstName of type course_genie_backend_ns0_studentDTO...\n");
#endif
    free(_studentDTO->firstName);
  }
  if (_studentDTO->email != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor email of type course_genie_backend_ns0_studentDTO...\n");
#endif
    freeXsStringType(_studentDTO->email);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor email of type course_genie_backend_ns0_studentDTO...\n");
#endif
    free(_studentDTO->email);
  }
  if (_studentDTO->studentId != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor studentId of type course_genie_backend_ns0_studentDTO...\n");
#endif
    freeXsStringType(_studentDTO->studentId);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor studentId of type course_genie_backend_ns0_studentDTO...\n");
#endif
    free(_studentDTO->studentId);
  }
}
#endif /* DEF_course_genie_backend_ns0_studentDTO_M */
#ifndef DEF_course_genie_backend_ns0_syllabusDTO_M
#define DEF_course_genie_backend_ns0_syllabusDTO_M

/**
 * Reads a SyllabusDTO from XML. The reader is assumed to be at the start element.
 *
 * @return the SyllabusDTO, or NULL in case of error.
 */
static struct course_genie_backend_ns0_syllabusDTO *xmlTextReaderReadNs0SyllabusDTOType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct course_genie_backend_ns0_syllabusDTO *_syllabusDTO = calloc(1, sizeof(struct course_genie_backend_ns0_syllabusDTO));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0SyllabusDTOType(_syllabusDTO);
        free(_syllabusDTO);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "sectionId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}sectionId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}sectionId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SyllabusDTOType(_syllabusDTO);
          free(_syllabusDTO);
          return NULL;
        }

        _syllabusDTO->sectionId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "content", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}content of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}content of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SyllabusDTOType(_syllabusDTO);
          free(_syllabusDTO);
          return NULL;
        }

        _syllabusDTO->content = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "syllabusId", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}syllabusId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
        _child_accessor = xmlTextReaderReadXsLongType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}syllabusId of type {http://www.w3.org/2001/XMLSchema}long.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0SyllabusDTOType(_syllabusDTO);
          free(_syllabusDTO);
          return NULL;
        }

        _syllabusDTO->syllabusId = *((long long*)_child_accessor);
        free(_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}syllabusDTO.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}syllabusDTO. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _syllabusDTO;
}

/**
 * Writes a SyllabusDTO to XML.
 *
 * @param writer The XML writer.
 * @param _syllabusDTO The SyllabusDTO to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0SyllabusDTOType(xmlTextWriterPtr writer, struct course_genie_backend_ns0_syllabusDTO *_syllabusDTO) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}sectionId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_syllabusDTO->sectionId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}sectionId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_syllabusDTO->content != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "content", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}content. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}content...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_syllabusDTO->content));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}content. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}content. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (1) { //always write the primitive element.
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "syllabusId", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}syllabusId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}long for element {}syllabusId...\n");
#endif
    status = xmlTextWriterWriteXsLongType(writer, &(_syllabusDTO->syllabusId));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}long for element {}syllabusId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}syllabusId. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a SyllabusDTO.
 *
 * @param _syllabusDTO The SyllabusDTO to free.
 */
static void freeNs0SyllabusDTOType(struct course_genie_backend_ns0_syllabusDTO *_syllabusDTO) {
  int i;
  if (_syllabusDTO->content != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor content of type course_genie_backend_ns0_syllabusDTO...\n");
#endif
    freeXsStringType(_syllabusDTO->content);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor content of type course_genie_backend_ns0_syllabusDTO...\n");
#endif
    free(_syllabusDTO->content);
  }
}
#endif /* DEF_course_genie_backend_ns0_syllabusDTO_M */
