USE course_genie;

-- Run the backend once before this script so Hibernate creates course.department_id.

-- 1. Populate course.department_id by matching existing course.discipline to department.department_name.
UPDATE course c
JOIN department d
  ON LOWER(TRIM(c.discipline)) = LOWER(TRIM(d.department_name))
SET c.department_id = d.department_id
WHERE c.department_id IS NULL
  AND c.discipline IS NOT NULL
  AND TRIM(c.discipline) <> '';

-- 2. Verification: show all departments.
SELECT *
FROM department
ORDER BY department_id;

-- 3. Verification: show course department links.
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

-- 4. Verification: find courses still missing a department link.
SELECT
  c.course_id,
  c.code,
  c.name,
  c.discipline
FROM course c
WHERE c.department_id IS NULL
ORDER BY c.course_id;
