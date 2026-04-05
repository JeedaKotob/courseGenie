USE course_genie;

-- 1. Derive discipline from the alphabetic prefix of course.code.
-- Handles values like:
--   ISTE 230  -> ISTE
--   NSSA-241  -> NSSA
--   IGME201   -> IGME
UPDATE course
SET discipline = REGEXP_SUBSTR(TRIM(code), '^[A-Za-z]+')
WHERE code IS NOT NULL
  AND TRIM(code) <> '';

-- 2. Verification: show updated discipline values and preserve department links.
SELECT
  course_id,
  code,
  name,
  discipline,
  department_id
FROM course
ORDER BY course_id;

-- 3. Verification: find courses whose discipline could not be derived.
SELECT
  course_id,
  code,
  name,
  discipline
FROM course
WHERE discipline IS NULL
   OR TRIM(discipline) = ''
ORDER BY course_id;

-- 4. Verification: confirm course department links remain populated.
SELECT
  c.course_id,
  c.code,
  c.name,
  c.discipline,
  c.department_id,
  d.department_name
FROM course c
LEFT JOIN department d
  ON d.department_id = c.department_id
ORDER BY c.course_id;
