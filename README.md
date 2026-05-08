# CourseGenie+

CourseGenie+ is a web-based academic workflow automation system built to support course administration, faculty workflows, reporting, and assessment-related processes.

## Project Overview

The system provides role-based dashboards for professors and administrators, allowing users to manage academic tasks such as syllabus submissions, CAR generation, grade analysis, peer review, course calendars, and exam room allocation.

## Technology Stack

* Angular
* Spring Boot
* MySQL
* LDAP
* Docker
* Maven
* npm

## Prerequisites

Ensure the following are installed before running the project:

* Docker Desktop
* JDK 21
* Node.js
* npm

## Installation and Setup

Clone or download the project, then open a terminal in the project root folder:

```bash
cd courseGenie
```

Start the Docker services:

```bash
cd courseGenie_backEnd/docker
docker compose up -d
cd ../..
```

Load LDAP users:

```bash
docker cp courseGenie_backEnd/docker/users.ldif openldap:/tmp/users.ldif
docker exec -it openldap ldapadd -x -D "cn=admin,dc=course,dc=genie" -w root -f /tmp/users.ldif
```

Run the backend:

```bash
cd courseGenie_backEnd
./mvnw spring-boot:run
```

Import the seed data after the backend creates the database tables:

```bash
cd courseGenie_backEnd/docker
docker compose exec -T mysql sh -c 'mysql -uroot -p"$MYSQL_ROOT_PASSWORD" course_genie' < ../../course_genie_seed_data.sql
```

Run the frontend:

```bash
cd courseGenie_frontEnd
npm install
ng serve --open
```

## Application URLs

* Frontend: `http://localhost:4200`
* Backend: `http://localhost:8080`






