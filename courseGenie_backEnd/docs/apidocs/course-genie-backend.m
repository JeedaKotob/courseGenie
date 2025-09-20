#import "course-genie-backend.h"
#ifndef DEF_COURSE_GENIE_BACKENDNS0BenchmarkDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0BenchmarkDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0BenchmarkDTO

/**
 * (no documentation provided)
 */
- (NSString *) benchmarkType
{
  return _benchmarkType;
}

/**
 * (no documentation provided)
 */
- (void) setBenchmarkType: (NSString *) newBenchmarkType
{
  [newBenchmarkType retain];
  [_benchmarkType release];
  _benchmarkType = newBenchmarkType;
}

/**
 * (no documentation provided)
 */
- (long long) benchmarkId
{
  return _benchmarkId;
}

/**
 * (no documentation provided)
 */
- (void) setBenchmarkId: (long long) newBenchmarkId
{
  _benchmarkId = newBenchmarkId;
}

/**
 * (no documentation provided)
 */
- (int) threshold
{
  return _threshold;
}

/**
 * (no documentation provided)
 */
- (void) setThreshold: (int) newThreshold
{
  _threshold = newThreshold;
}

/**
 * (no documentation provided)
 */
- (NSString *) description
{
  return _description;
}

/**
 * (no documentation provided)
 */
- (void) setDescription: (NSString *) newDescription
{
  [newDescription retain];
  [_description release];
  _description = newDescription;
}

/**
 * (no documentation provided)
 */
- (int) percentage
{
  return _percentage;
}

/**
 * (no documentation provided)
 */
- (void) setPercentage: (int) newPercentage
{
  _percentage = newPercentage;
}

- (void) dealloc
{
  [self setBenchmarkType: nil];
  [self setDescription: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0BenchmarkDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0BenchmarkDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0BenchmarkDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0BenchmarkDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0BenchmarkDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0BenchmarkDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0BenchmarkDTO *_cOURSE_GENIE_BACKENDNS0BenchmarkDTO = [[COURSE_GENIE_BACKENDNS0BenchmarkDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0BenchmarkDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0BenchmarkDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0BenchmarkDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0BenchmarkDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0BenchmarkDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0BenchmarkDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "benchmarkType", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}benchmarkType of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}benchmarkType of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setBenchmarkType: __child];
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "benchmarkId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setBenchmarkId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "threshold", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadIntType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setThreshold: *((int*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "description", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setDescription: __child];
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "percentage", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadIntType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setPercentage: *((int*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self benchmarkType]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "benchmarkType", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}benchmarkType."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}benchmarkType...");
#endif
    [[self benchmarkType] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}benchmarkType...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}benchmarkType."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "benchmarkId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}benchmarkId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}benchmarkId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_benchmarkId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}benchmarkId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}benchmarkId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}benchmarkId."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "threshold", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}threshold."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}threshold...");
#endif
    status = xmlTextWriterWriteIntType(writer, &_threshold);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}threshold...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}threshold."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}threshold."];
    }
  }
  if ([self description]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "description", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}description."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}description...");
#endif
    [[self description] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}description...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}description."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "percentage", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}percentage."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}percentage...");
#endif
    status = xmlTextWriterWriteIntType(writer, &_percentage);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}percentage...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}percentage."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}percentage."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0BenchmarkDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0BenchmarkDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0CLODTO_M
#define DEF_COURSE_GENIE_BACKENDNS0CLODTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0CLODTO

/**
 * (no documentation provided)
 */
- (long long) cloId
{
  return _cloId;
}

/**
 * (no documentation provided)
 */
- (void) setCloId: (long long) newCloId
{
  _cloId = newCloId;
}

/**
 * (no documentation provided)
 */
- (NSString *) description
{
  return _description;
}

/**
 * (no documentation provided)
 */
- (void) setDescription: (NSString *) newDescription
{
  [newDescription retain];
  [_description release];
  _description = newDescription;
}

/**
 * (no documentation provided)
 */
- (NSString *) name
{
  return _name;
}

/**
 * (no documentation provided)
 */
- (void) setName: (NSString *) newName
{
  [newName retain];
  [_name release];
  _name = newName;
}

/**
 * (no documentation provided)
 */
- (long long) courseId
{
  return _courseId;
}

/**
 * (no documentation provided)
 */
- (void) setCourseId: (long long) newCourseId
{
  _courseId = newCourseId;
}

- (void) dealloc
{
  [self setDescription: nil];
  [self setName: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0CLODTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0CLODTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0CLODTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0CLODTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0CLODTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0CLODTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0CLODTO *_cOURSE_GENIE_BACKENDNS0CLODTO = [[COURSE_GENIE_BACKENDNS0CLODTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0CLODTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0CLODTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0CLODTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0CLODTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0CLODTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0CLODTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "cloId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setCloId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "description", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setDescription: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "name", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setName: __child];
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "courseId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setCourseId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "cloId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}cloId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}cloId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_cloId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}cloId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}cloId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}cloId."];
    }
  }
  if ([self description]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "description", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}description."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}description...");
#endif
    [[self description] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}description...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}description."];
    }
  }
  if ([self name]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "name", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}name."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}name...");
#endif
    [[self name] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}name...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}name."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "courseId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}courseId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}courseId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_courseId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}courseId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}courseId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}courseId."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0CLODTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0CLODTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0GradeDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0GradeDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0GradeDTO

/**
 * (no documentation provided)
 */
- (long long) gradeId
{
  return _gradeId;
}

/**
 * (no documentation provided)
 */
- (void) setGradeId: (long long) newGradeId
{
  _gradeId = newGradeId;
}

/**
 * (no documentation provided)
 */
- (NSString *) studentId
{
  return _studentId;
}

/**
 * (no documentation provided)
 */
- (void) setStudentId: (NSString *) newStudentId
{
  [newStudentId retain];
  [_studentId release];
  _studentId = newStudentId;
}

/**
 * (no documentation provided)
 */
- (long long) assessmentId
{
  return _assessmentId;
}

/**
 * (no documentation provided)
 */
- (void) setAssessmentId: (long long) newAssessmentId
{
  _assessmentId = newAssessmentId;
}

/**
 * (no documentation provided)
 */
- (double) score
{
  return _score;
}

/**
 * (no documentation provided)
 */
- (void) setScore: (double) newScore
{
  _score = newScore;
}

- (void) dealloc
{
  [self setStudentId: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0GradeDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0GradeDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0GradeDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0GradeDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0GradeDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0GradeDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0GradeDTO *_cOURSE_GENIE_BACKENDNS0GradeDTO = [[COURSE_GENIE_BACKENDNS0GradeDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0GradeDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0GradeDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0GradeDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0GradeDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0GradeDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0GradeDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "gradeId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setGradeId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "studentId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setStudentId: __child];
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "assessmentId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setAssessmentId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "score", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadDoubleType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setScore: *((double*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "gradeId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}gradeId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}gradeId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_gradeId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}gradeId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}gradeId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}gradeId."];
    }
  }
  if ([self studentId]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "studentId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}studentId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}studentId...");
#endif
    [[self studentId] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}studentId...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}studentId."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "assessmentId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}assessmentId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}assessmentId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_assessmentId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}assessmentId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}assessmentId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}assessmentId."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "score", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}score."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}score...");
#endif
    status = xmlTextWriterWriteDoubleType(writer, &_score);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}score...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}score."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}score."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0GradeDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0GradeDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0AuthQuery_M
#define DEF_COURSE_GENIE_BACKENDNS0AuthQuery_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0AuthQuery

/**
 * (no documentation provided)
 */
- (NSString *) password
{
  return _password;
}

/**
 * (no documentation provided)
 */
- (void) setPassword: (NSString *) newPassword
{
  [newPassword retain];
  [_password release];
  _password = newPassword;
}

/**
 * (no documentation provided)
 */
- (NSString *) username
{
  return _username;
}

/**
 * (no documentation provided)
 */
- (void) setUsername: (NSString *) newUsername
{
  [newUsername retain];
  [_username release];
  _username = newUsername;
}

- (void) dealloc
{
  [self setPassword: nil];
  [self setUsername: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0AuthQuery */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0AuthQuery (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0AuthQuery (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0AuthQuery (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0AuthQuery from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0AuthQuery defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0AuthQuery *_cOURSE_GENIE_BACKENDNS0AuthQuery = [[COURSE_GENIE_BACKENDNS0AuthQuery alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0AuthQuery initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0AuthQuery = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0AuthQuery autorelease];
  return _cOURSE_GENIE_BACKENDNS0AuthQuery;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0AuthQuery according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0AuthQuery to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "password", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}password of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setPassword: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "username", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}username of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}username of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setUsername: __child];
    return YES;
  } //end "if choice"


  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self password]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "password", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}password."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}password...");
#endif
    [[self password] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}password...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}password."];
    }
  }
  if ([self username]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "username", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}username."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}username...");
#endif
    [[self username] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}username...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}username."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0AuthQuery (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0AuthQuery_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0SyllabusDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0SyllabusDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0SyllabusDTO

/**
 * (no documentation provided)
 */
- (long long) syllabusId
{
  return _syllabusId;
}

/**
 * (no documentation provided)
 */
- (void) setSyllabusId: (long long) newSyllabusId
{
  _syllabusId = newSyllabusId;
}

/**
 * (no documentation provided)
 */
- (NSString *) content
{
  return _content;
}

/**
 * (no documentation provided)
 */
- (void) setContent: (NSString *) newContent
{
  [newContent retain];
  [_content release];
  _content = newContent;
}

/**
 * (no documentation provided)
 */
- (long long) sectionId
{
  return _sectionId;
}

/**
 * (no documentation provided)
 */
- (void) setSectionId: (long long) newSectionId
{
  _sectionId = newSectionId;
}

- (void) dealloc
{
  [self setContent: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0SyllabusDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0SyllabusDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0SyllabusDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0SyllabusDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0SyllabusDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0SyllabusDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0SyllabusDTO *_cOURSE_GENIE_BACKENDNS0SyllabusDTO = [[COURSE_GENIE_BACKENDNS0SyllabusDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0SyllabusDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0SyllabusDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0SyllabusDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0SyllabusDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0SyllabusDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0SyllabusDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "syllabusId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setSyllabusId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "content", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}content of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}content of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setContent: __child];
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "sectionId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setSectionId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "syllabusId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}syllabusId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}syllabusId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_syllabusId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}syllabusId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}syllabusId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}syllabusId."];
    }
  }
  if ([self content]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "content", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}content."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}content...");
#endif
    [[self content] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}content...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}content."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}sectionId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}sectionId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_sectionId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}sectionId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}sectionId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}sectionId."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0SyllabusDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0SyllabusDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0StudentDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0StudentDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0StudentDTO

/**
 * (no documentation provided)
 */
- (NSString *) studentId
{
  return _studentId;
}

/**
 * (no documentation provided)
 */
- (void) setStudentId: (NSString *) newStudentId
{
  [newStudentId retain];
  [_studentId release];
  _studentId = newStudentId;
}

/**
 * (no documentation provided)
 */
- (NSString *) firstName
{
  return _firstName;
}

/**
 * (no documentation provided)
 */
- (void) setFirstName: (NSString *) newFirstName
{
  [newFirstName retain];
  [_firstName release];
  _firstName = newFirstName;
}

/**
 * (no documentation provided)
 */
- (NSString *) email
{
  return _email;
}

/**
 * (no documentation provided)
 */
- (void) setEmail: (NSString *) newEmail
{
  [newEmail retain];
  [_email release];
  _email = newEmail;
}

/**
 * (no documentation provided)
 */
- (NSString *) lastName
{
  return _lastName;
}

/**
 * (no documentation provided)
 */
- (void) setLastName: (NSString *) newLastName
{
  [newLastName retain];
  [_lastName release];
  _lastName = newLastName;
}

- (void) dealloc
{
  [self setStudentId: nil];
  [self setFirstName: nil];
  [self setEmail: nil];
  [self setLastName: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0StudentDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0StudentDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0StudentDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0StudentDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0StudentDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0StudentDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0StudentDTO *_cOURSE_GENIE_BACKENDNS0StudentDTO = [[COURSE_GENIE_BACKENDNS0StudentDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0StudentDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0StudentDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0StudentDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0StudentDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0StudentDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0StudentDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "studentId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}studentId of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setStudentId: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "firstName", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}firstName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setFirstName: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "email", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}email of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setEmail: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "lastName", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}lastName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setLastName: __child];
    return YES;
  } //end "if choice"


  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self studentId]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "studentId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}studentId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}studentId...");
#endif
    [[self studentId] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}studentId...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}studentId."];
    }
  }
  if ([self firstName]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "firstName", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}firstName."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}firstName...");
#endif
    [[self firstName] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}firstName...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}firstName."];
    }
  }
  if ([self email]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "email", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}email."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}email...");
#endif
    [[self email] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}email...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}email."];
    }
  }
  if ([self lastName]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "lastName", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}lastName."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}lastName...");
#endif
    [[self lastName] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}lastName...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}lastName."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0StudentDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0StudentDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0SectionDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0SectionDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0SectionDTO

/**
 * (no documentation provided)
 */
- (long long) sectionId
{
  return _sectionId;
}

/**
 * (no documentation provided)
 */
- (void) setSectionId: (long long) newSectionId
{
  _sectionId = newSectionId;
}

/**
 * (no documentation provided)
 */
- (NSString *) teachingMethodology
{
  return _teachingMethodology;
}

/**
 * (no documentation provided)
 */
- (void) setTeachingMethodology: (NSString *) newTeachingMethodology
{
  [newTeachingMethodology retain];
  [_teachingMethodology release];
  _teachingMethodology = newTeachingMethodology;
}

/**
 * (no documentation provided)
 */
- (NSString *) class_number
{
  return _class_number;
}

/**
 * (no documentation provided)
 */
- (void) setClass_number: (NSString *) newClass_number
{
  [newClass_number retain];
  [_class_number release];
  _class_number = newClass_number;
}

/**
 * (no documentation provided)
 */
- (NSString *) code
{
  return _code;
}

/**
 * (no documentation provided)
 */
- (void) setCode: (NSString *) newCode
{
  [newCode retain];
  [_code release];
  _code = newCode;
}

/**
 * (no documentation provided)
 */
- (NSArray *) assessments
{
  return _assessments;
}

/**
 * (no documentation provided)
 */
- (void) setAssessments: (NSArray *) newAssessments
{
  [newAssessments retain];
  [_assessments release];
  _assessments = newAssessments;
}

/**
 * (no documentation provided)
 */
- (long long) professorId
{
  return _professorId;
}

/**
 * (no documentation provided)
 */
- (void) setProfessorId: (long long) newProfessorId
{
  _professorId = newProfessorId;
}

/**
 * (no documentation provided)
 */
- (long long) courseId
{
  return _courseId;
}

/**
 * (no documentation provided)
 */
- (void) setCourseId: (long long) newCourseId
{
  _courseId = newCourseId;
}

/**
 * (no documentation provided)
 */
- (BOOL) configured
{
  return _configured;
}

/**
 * (no documentation provided)
 */
- (void) setConfigured: (BOOL) newConfigured
{
  _configured = newConfigured;
}

/**
 * (no documentation provided)
 */
- (NSString *) term
{
  return _term;
}

/**
 * (no documentation provided)
 */
- (void) setTerm: (NSString *) newTerm
{
  [newTerm retain];
  [_term release];
  _term = newTerm;
}

- (void) dealloc
{
  [self setTeachingMethodology: nil];
  [self setClass_number: nil];
  [self setCode: nil];
  [self setAssessments: nil];
  [self setTerm: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0SectionDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0SectionDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0SectionDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0SectionDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0SectionDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0SectionDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0SectionDTO *_cOURSE_GENIE_BACKENDNS0SectionDTO = [[COURSE_GENIE_BACKENDNS0SectionDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0SectionDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0SectionDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0SectionDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0SectionDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0SectionDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0SectionDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "sectionId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setSectionId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "teachingMethodology", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}teachingMethodology of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}teachingMethodology of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setTeachingMethodology: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "class_number", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}class_number of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}class_number of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setClass_number: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "code", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setCode: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "assessments", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}assessments of type {}assessmentDTO.");
#endif

     __child = [COURSE_GENIE_BACKENDNS0AssessmentDTO readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}assessments of type {}assessmentDTO.");
#endif

    if ([self assessments]) {
      [self setAssessments: [[self assessments] arrayByAddingObject: __child]];
    }
    else {
      [self setAssessments: [NSArray arrayWithObject: __child]];
    }
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "professorId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setProfessorId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "courseId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setCourseId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "configured", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadBooleanType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setConfigured: *((BOOL*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "term", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}term of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}term of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setTerm: __child];
    return YES;
  } //end "if choice"


  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}sectionId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}sectionId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_sectionId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}sectionId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}sectionId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}sectionId."];
    }
  }
  if ([self teachingMethodology]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "teachingMethodology", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}teachingMethodology."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}teachingMethodology...");
#endif
    [[self teachingMethodology] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}teachingMethodology...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}teachingMethodology."];
    }
  }
  if ([self class_number]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "class_number", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}class_number."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}class_number...");
#endif
    [[self class_number] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}class_number...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}class_number."];
    }
  }
  if ([self code]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "code", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}code."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}code...");
#endif
    [[self code] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}code...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}code."];
    }
  }
  if ([self assessments]) {
    __enumerator = [[self assessments] objectEnumerator];

    while ( (__item = [__enumerator nextObject]) ) {
      status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "assessments", NULL);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing start child element {}assessments."];
      }

#if DEBUG_ENUNCIATE > 1
      NSLog(@"writing element {}assessments...");
#endif
      [__item writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
      NSLog(@"successfully wrote element {}assessments...");
#endif

      status = xmlTextWriterEndElement(writer);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing end child element {}assessments."];
      }
    } //end item iterator.
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "professorId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}professorId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}professorId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_professorId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}professorId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}professorId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}professorId."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "courseId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}courseId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}courseId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_courseId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}courseId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}courseId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}courseId."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "configured", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}configured."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}configured...");
#endif
    status = xmlTextWriterWriteBooleanType(writer, &_configured);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}configured...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}configured."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}configured."];
    }
  }
  if ([self term]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "term", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}term."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}term...");
#endif
    [[self term] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}term...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}term."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0SectionDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0SectionDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0CourseDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0CourseDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0CourseDTO

/**
 * (no documentation provided)
 */
- (NSString *) credits
{
  return _credits;
}

/**
 * (no documentation provided)
 */
- (void) setCredits: (NSString *) newCredits
{
  [newCredits retain];
  [_credits release];
  _credits = newCredits;
}

/**
 * (no documentation provided)
 */
- (NSString *) code
{
  return _code;
}

/**
 * (no documentation provided)
 */
- (void) setCode: (NSString *) newCode
{
  [newCode retain];
  [_code release];
  _code = newCode;
}

/**
 * (no documentation provided)
 */
- (NSString *) name
{
  return _name;
}

/**
 * (no documentation provided)
 */
- (void) setName: (NSString *) newName
{
  [newName retain];
  [_name release];
  _name = newName;
}

/**
 * (no documentation provided)
 */
- (NSArray *) clos
{
  return _clos;
}

/**
 * (no documentation provided)
 */
- (void) setClos: (NSArray *) newClos
{
  [newClos retain];
  [_clos release];
  _clos = newClos;
}

/**
 * (no documentation provided)
 */
- (long long) courseId
{
  return _courseId;
}

/**
 * (no documentation provided)
 */
- (void) setCourseId: (long long) newCourseId
{
  _courseId = newCourseId;
}

/**
 * (no documentation provided)
 */
- (NSArray *) sections
{
  return _sections;
}

/**
 * (no documentation provided)
 */
- (void) setSections: (NSArray *) newSections
{
  [newSections retain];
  [_sections release];
  _sections = newSections;
}

/**
 * (no documentation provided)
 */
- (NSString *) description
{
  return _description;
}

/**
 * (no documentation provided)
 */
- (void) setDescription: (NSString *) newDescription
{
  [newDescription retain];
  [_description release];
  _description = newDescription;
}

- (void) dealloc
{
  [self setCredits: nil];
  [self setCode: nil];
  [self setName: nil];
  [self setClos: nil];
  [self setSections: nil];
  [self setDescription: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0CourseDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0CourseDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0CourseDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0CourseDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0CourseDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0CourseDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0CourseDTO *_cOURSE_GENIE_BACKENDNS0CourseDTO = [[COURSE_GENIE_BACKENDNS0CourseDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0CourseDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0CourseDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0CourseDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0CourseDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0CourseDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0CourseDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "credits", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}credits of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}credits of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setCredits: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "code", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}code of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setCode: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "name", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setName: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "clos", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}clos of type {}CLODTO.");
#endif

     __child = [COURSE_GENIE_BACKENDNS0CLODTO readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}clos of type {}CLODTO.");
#endif

    if ([self clos]) {
      [self setClos: [[self clos] arrayByAddingObject: __child]];
    }
    else {
      [self setClos: [NSArray arrayWithObject: __child]];
    }
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "courseId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setCourseId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "sections", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}sections of type {}sectionDTO.");
#endif

     __child = [COURSE_GENIE_BACKENDNS0SectionDTO readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}sections of type {}sectionDTO.");
#endif

    if ([self sections]) {
      [self setSections: [[self sections] arrayByAddingObject: __child]];
    }
    else {
      [self setSections: [NSArray arrayWithObject: __child]];
    }
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "description", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}description of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setDescription: __child];
    return YES;
  } //end "if choice"


  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self credits]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "credits", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}credits."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}credits...");
#endif
    [[self credits] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}credits...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}credits."];
    }
  }
  if ([self code]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "code", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}code."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}code...");
#endif
    [[self code] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}code...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}code."];
    }
  }
  if ([self name]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "name", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}name."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}name...");
#endif
    [[self name] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}name...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}name."];
    }
  }
  if ([self clos]) {
    __enumerator = [[self clos] objectEnumerator];

    while ( (__item = [__enumerator nextObject]) ) {
      status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "clos", NULL);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing start child element {}clos."];
      }

#if DEBUG_ENUNCIATE > 1
      NSLog(@"writing element {}clos...");
#endif
      [__item writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
      NSLog(@"successfully wrote element {}clos...");
#endif

      status = xmlTextWriterEndElement(writer);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing end child element {}clos."];
      }
    } //end item iterator.
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "courseId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}courseId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}courseId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_courseId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}courseId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}courseId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}courseId."];
    }
  }
  if ([self sections]) {
    __enumerator = [[self sections] objectEnumerator];

    while ( (__item = [__enumerator nextObject]) ) {
      status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sections", NULL);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing start child element {}sections."];
      }

#if DEBUG_ENUNCIATE > 1
      NSLog(@"writing element {}sections...");
#endif
      [__item writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
      NSLog(@"successfully wrote element {}sections...");
#endif

      status = xmlTextWriterEndElement(writer);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing end child element {}sections."];
      }
    } //end item iterator.
  }
  if ([self description]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "description", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}description."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}description...");
#endif
    [[self description] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}description...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}description."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0CourseDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0CourseDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0BookDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0BookDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0BookDTO

/**
 * (no documentation provided)
 */
- (long long *) identifier
{
  return _identifier;
}

/**
 * (no documentation provided)
 */
- (void) setIdentifier: (long long *) newIdentifier
{
  if (_identifier != NULL) {
    free(_identifier);
  }
  _identifier = newIdentifier;
}

/**
 * (no documentation provided)
 */
- (NSString *) isbn
{
  return _isbn;
}

/**
 * (no documentation provided)
 */
- (void) setIsbn: (NSString *) newIsbn
{
  [newIsbn retain];
  [_isbn release];
  _isbn = newIsbn;
}

/**
 * (no documentation provided)
 */
- (NSString *) title
{
  return _title;
}

/**
 * (no documentation provided)
 */
- (void) setTitle: (NSString *) newTitle
{
  [newTitle retain];
  [_title release];
  _title = newTitle;
}

/**
 * (no documentation provided)
 */
- (NSString *) url
{
  return _url;
}

/**
 * (no documentation provided)
 */
- (void) setUrl: (NSString *) newUrl
{
  [newUrl retain];
  [_url release];
  _url = newUrl;
}

/**
 * (no documentation provided)
 */
- (NSArray *) sectionIds
{
  return _sectionIds;
}

/**
 * (no documentation provided)
 */
- (void) setSectionIds: (NSArray *) newSectionIds
{
  [newSectionIds retain];
  [_sectionIds release];
  _sectionIds = newSectionIds;
}

/**
 * (no documentation provided)
 */
- (BOOL) requiredReading
{
  return _requiredReading;
}

/**
 * (no documentation provided)
 */
- (void) setRequiredReading: (BOOL) newRequiredReading
{
  _requiredReading = newRequiredReading;
}

/**
 * (no documentation provided)
 */
- (BOOL) suggestedReading
{
  return _suggestedReading;
}

/**
 * (no documentation provided)
 */
- (void) setSuggestedReading: (BOOL) newSuggestedReading
{
  _suggestedReading = newSuggestedReading;
}

- (void) dealloc
{
  [self setIdentifier: NULL];
  [self setIsbn: nil];
  [self setTitle: nil];
  [self setUrl: nil];
  [self setSectionIds: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0BookDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0BookDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0BookDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0BookDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0BookDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0BookDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0BookDTO *_cOURSE_GENIE_BACKENDNS0BookDTO = [[COURSE_GENIE_BACKENDNS0BookDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0BookDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0BookDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0BookDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0BookDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0BookDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0BookDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "id", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setIdentifier: ((long long*) _child_accessor)];
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "isbn", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}isbn of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}isbn of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setIsbn: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "title", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}title of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}title of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setTitle: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "url", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}url of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}url of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setUrl: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "sectionIds", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}sectionIds of type {http://www.w3.org/2001/XMLSchema}long.");
#endif

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    __child = [NSValue value: _child_accessor withObjCType: @encode(long long)];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}sectionIds of type {http://www.w3.org/2001/XMLSchema}long.");
#endif

    if ([self sectionIds]) {
      [self setSectionIds: [[self sectionIds] arrayByAddingObject: __child]];
    }
    else {
      [self setSectionIds: [NSArray arrayWithObject: __child]];
    }
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "requiredReading", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadBooleanType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setRequiredReading: *((BOOL*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "suggestedReading", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadBooleanType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setSuggestedReading: *((BOOL*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self identifier] != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "id", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}id."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}id...");
#endif
    status = xmlTextWriterWriteLongType(writer, [self identifier]);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}id...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}id."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}id."];
    }
  }
  if ([self isbn]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "isbn", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}isbn."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}isbn...");
#endif
    [[self isbn] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}isbn...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}isbn."];
    }
  }
  if ([self title]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "title", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}title."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}title...");
#endif
    [[self title] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}title...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}title."];
    }
  }
  if ([self url]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "url", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}url."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}url...");
#endif
    [[self url] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}url...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}url."];
    }
  }
  if ([self sectionIds]) {
    __enumerator = [[self sectionIds] objectEnumerator];

    while ( (__item = [__enumerator nextObject]) ) {
      status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionIds", NULL);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing start child element {}sectionIds."];
      }

#if DEBUG_ENUNCIATE > 1
      NSLog(@"writing element {}sectionIds...");
#endif
      status = xmlTextWriterWriteLongType(writer, ((long long*) [((NSValue *)__item) pointerValue]));
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing child element {}sectionIds."];
      }
#if DEBUG_ENUNCIATE > 1
      NSLog(@"successfully wrote element {}sectionIds...");
#endif

      status = xmlTextWriterEndElement(writer);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing end child element {}sectionIds."];
      }
    } //end item iterator.
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "requiredReading", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}requiredReading."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}requiredReading...");
#endif
    status = xmlTextWriterWriteBooleanType(writer, &_requiredReading);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}requiredReading...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}requiredReading."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}requiredReading."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "suggestedReading", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}suggestedReading."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}suggestedReading...");
#endif
    status = xmlTextWriterWriteBooleanType(writer, &_suggestedReading);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}suggestedReading...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}suggestedReading."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}suggestedReading."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0BookDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0BookDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0AssessmentDTO_M
#define DEF_COURSE_GENIE_BACKENDNS0AssessmentDTO_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0AssessmentDTO

/**
 * (no documentation provided)
 */
- (NSString *) category
{
  return _category;
}

/**
 * (no documentation provided)
 */
- (void) setCategory: (NSString *) newCategory
{
  [newCategory retain];
  [_category release];
  _category = newCategory;
}

/**
 * (no documentation provided)
 */
- (long long) sectionId
{
  return _sectionId;
}

/**
 * (no documentation provided)
 */
- (void) setSectionId: (long long) newSectionId
{
  _sectionId = newSectionId;
}

/**
 * (no documentation provided)
 */
- (NSString *) shortName
{
  return _shortName;
}

/**
 * (no documentation provided)
 */
- (void) setShortName: (NSString *) newShortName
{
  [newShortName retain];
  [_shortName release];
  _shortName = newShortName;
}

/**
 * (no documentation provided)
 */
- (NSString *) name
{
  return _name;
}

/**
 * (no documentation provided)
 */
- (void) setName: (NSString *) newName
{
  [newName retain];
  [_name release];
  _name = newName;
}

/**
 * (no documentation provided)
 */
- (int) maxPoints
{
  return _maxPoints;
}

/**
 * (no documentation provided)
 */
- (void) setMaxPoints: (int) newMaxPoints
{
  _maxPoints = newMaxPoints;
}

/**
 * (no documentation provided)
 */
- (long long) assessmentId
{
  return _assessmentId;
}

/**
 * (no documentation provided)
 */
- (void) setAssessmentId: (long long) newAssessmentId
{
  _assessmentId = newAssessmentId;
}

/**
 * (no documentation provided)
 */
- (NSArray *) clos
{
  return _clos;
}

/**
 * (no documentation provided)
 */
- (void) setClos: (NSArray *) newClos
{
  [newClos retain];
  [_clos release];
  _clos = newClos;
}

/**
 * (no documentation provided)
 */
- (int) week
{
  return _week;
}

/**
 * (no documentation provided)
 */
- (void) setWeek: (int) newWeek
{
  _week = newWeek;
}

- (void) dealloc
{
  [self setCategory: nil];
  [self setShortName: nil];
  [self setName: nil];
  [self setClos: nil];
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0AssessmentDTO */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0AssessmentDTO (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0AssessmentDTO (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0AssessmentDTO (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0AssessmentDTO from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0AssessmentDTO defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0AssessmentDTO *_cOURSE_GENIE_BACKENDNS0AssessmentDTO = [[COURSE_GENIE_BACKENDNS0AssessmentDTO alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0AssessmentDTO initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0AssessmentDTO = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0AssessmentDTO autorelease];
  return _cOURSE_GENIE_BACKENDNS0AssessmentDTO;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0AssessmentDTO according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0AssessmentDTO to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "category", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}category of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}category of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setCategory: __child];
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "sectionId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setSectionId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "shortName", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}shortName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}shortName of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setShortName: __child];
    return YES;
  } //end "if choice"

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "name", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.");
#endif
    __child = [NSString readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}name of type {http://www.w3.org/2001/XMLSchema}string.");
#endif

    [self setName: __child];
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "maxPoints", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadIntType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setMaxPoints: *((int*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "assessmentId", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadLongType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setAssessmentId: *((long long*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }
  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "clos", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
    NSLog(@"Attempting to read choice {}clos of type {}CLODTO.");
#endif

     __child = [COURSE_GENIE_BACKENDNS0CLODTO readXMLType: reader];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully read choice {}clos of type {}CLODTO.");
#endif

    if ([self clos]) {
      [self setClos: [[self clos] arrayByAddingObject: __child]];
    }
    else {
      [self setClos: [NSArray arrayWithObject: __child]];
    }
    return YES;
  } //end "if choice"


  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "week", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {

    _child_accessor = xmlTextReaderReadIntType(reader);
    if (_child_accessor == NULL) {
      //panic: unable to return the value for some reason.
      [NSException raise: @"XMLReadError"
                   format: @"Error reading element value."];
    }
    [self setWeek: *((int*) _child_accessor)];
    free(_child_accessor);
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

  if ([self category]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "category", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}category."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}category...");
#endif
    [[self category] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}category...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}category."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "sectionId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}sectionId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}sectionId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_sectionId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}sectionId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}sectionId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}sectionId."];
    }
  }
  if ([self shortName]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "shortName", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}shortName."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}shortName...");
#endif
    [[self shortName] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}shortName...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}shortName."];
    }
  }
  if ([self name]) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "name", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}name."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}name...");
#endif
    [[self name] writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}name...");
#endif

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}name."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "maxPoints", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}maxPoints."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}maxPoints...");
#endif
    status = xmlTextWriterWriteIntType(writer, &_maxPoints);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}maxPoints...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}maxPoints."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}maxPoints."];
    }
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "assessmentId", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}assessmentId."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}assessmentId...");
#endif
    status = xmlTextWriterWriteLongType(writer, &_assessmentId);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}assessmentId...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}assessmentId."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}assessmentId."];
    }
  }
  if ([self clos]) {
    __enumerator = [[self clos] objectEnumerator];

    while ( (__item = [__enumerator nextObject]) ) {
      status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "clos", NULL);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing start child element {}clos."];
      }

#if DEBUG_ENUNCIATE > 1
      NSLog(@"writing element {}clos...");
#endif
      [__item writeXMLType: writer];
#if DEBUG_ENUNCIATE > 1
      NSLog(@"successfully wrote element {}clos...");
#endif

      status = xmlTextWriterEndElement(writer);
      if (status < 0) {
        [NSException raise: @"XMLWriteError"
                     format: @"Error writing end child element {}clos."];
      }
    } //end item iterator.
  }
  if (YES) { //always write the primitive element...
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "week", NULL);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing start child element {}week."];
    }

#if DEBUG_ENUNCIATE > 1
    NSLog(@"writing element {}week...");
#endif
    status = xmlTextWriterWriteIntType(writer, &_week);
#if DEBUG_ENUNCIATE > 1
    NSLog(@"successfully wrote element {}week...");
#endif
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing child element {}week."];
    }

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
      [NSException raise: @"XMLWriteError"
                   format: @"Error writing end child element {}week."];
    }
  }
}
@end /* implementation COURSE_GENIE_BACKENDNS0AssessmentDTO (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0AssessmentDTO_M */
#ifndef DEF_COURSE_GENIE_BACKENDNS0Assessment_M
#define DEF_COURSE_GENIE_BACKENDNS0Assessment_M

/**
 * (no documentation provided)
 */
@implementation COURSE_GENIE_BACKENDNS0Assessment

- (void) dealloc
{
  [super dealloc];
}
@end /* implementation COURSE_GENIE_BACKENDNS0Assessment */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COURSE_GENIE_BACKENDNS0Assessment (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COURSE_GENIE_BACKENDNS0Assessment (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COURSE_GENIE_BACKENDNS0Assessment (JAXB)

/**
 * Read an instance of COURSE_GENIE_BACKENDNS0Assessment from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COURSE_GENIE_BACKENDNS0Assessment defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COURSE_GENIE_BACKENDNS0Assessment *_cOURSE_GENIE_BACKENDNS0Assessment = [[COURSE_GENIE_BACKENDNS0Assessment alloc] init];
  NS_DURING
  {
    [_cOURSE_GENIE_BACKENDNS0Assessment initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOURSE_GENIE_BACKENDNS0Assessment = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOURSE_GENIE_BACKENDNS0Assessment autorelease];
  return _cOURSE_GENIE_BACKENDNS0Assessment;
}

/**
 * Initialize this instance of COURSE_GENIE_BACKENDNS0Assessment according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COURSE_GENIE_BACKENDNS0Assessment to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

}
@end /* implementation COURSE_GENIE_BACKENDNS0Assessment (JAXB) */

#endif /* DEF_COURSE_GENIE_BACKENDNS0Assessment_M */
