USE course_genie;

-- 1. Ensure the Computing department exists.
INSERT INTO department (department_name, description)
SELECT 'Computing', 'Dev seed department for professor/course mapping'
WHERE NOT EXISTS (
  SELECT 1
  FROM department
  WHERE LOWER(TRIM(department_name)) = 'computing'
);

-- 2. Assign all professors to the Computing department.
UPDATE `user` u
JOIN user_roles ur
  ON ur.user_id = u.user_id
JOIN department d
  ON LOWER(TRIM(d.department_name)) = 'computing'
SET u.department_id = d.department_id
WHERE ur.role = 'ROLE_PROFESSOR';

-- 3. Align all current courses to the Computing discipline.
UPDATE course
SET discipline = 'Computing'
WHERE discipline IS NULL
   OR LOWER(TRIM(discipline)) <> 'computing';

-- 4. Verification queries.
SELECT * FROM department ORDER BY department_id;

SELECT * FROM `user` ORDER BY user_id;

SELECT * FROM user_roles ORDER BY user_id, role;

SELECT * FROM course ORDER BY course_id;

SELECT
  u.user_id,
  u.user_name,
  u.first_name,
  u.last_name,
  ur.role,
  d.department_name
FROM `user` u
LEFT JOIN user_roles ur
  ON ur.user_id = u.user_id
LEFT JOIN department d
  ON d.department_id = u.department_id
ORDER BY u.user_id, ur.role;

SELECT
  course_id,
  code,
  name,
  discipline
FROM course
ORDER BY course_id;
