# Job Application Tracker

Backend application for job application tracker web application.

## Getting Started

### Prerequisites

- Java 17
- Docker
- Gradle

### How to run

1. Clone the repo:
   ```bash
   git clone https://github.com/your_username/project_name.git
2. Navigate to the project directory:
   ```bash
   cd job-app
3. Run Docker container:
   ```bash
   docker-compose up -d
4. Migrate the database:
   ```bash
   ./gradlew flywayClean flywayMigrate
5. Generate jOOQ classes:
   ```bash
   ./gradlew generateJooq
6. Run the application:
   ```bash
   ./gradlew bootRun

### Other Commands

- Lint the code:
  ```bash
  ./gradlew ktlintFormat
- Log in to the database:
  ```bash
  docker exec -it job-app mysql -uuser -ppassword job_app
  mysql -h 127.0.0.1 -P 3307 -u user -ppassword job_app
