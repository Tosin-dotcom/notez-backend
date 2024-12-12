# Notez Backend

## Overview
The **Notez Backend** is the server-side component of the Notez application, a platform designed for managing and organizing personal notes. This backend provides RESTful APIs for features like user authentication, note management, and secure data handling. It is built with **Spring Boot** and deployed using **Docker** on **AWS ECS** for scalability and reliability.

## Features
- **User Authentication**: Secure login and registration functionality using JWT.
- **Note Management**: APIs to create, read, update, and delete notes.
- **File Management**: APIs to upload and delete files of types such as PDF, image, video, and audio. Files are uploaded to an **S3 bucket** and served using **CloudFront CDN** for efficient delivery.
- **Scalable Infrastructure**: Hosted on AWS ECS with a PostgreSQL database on RDS.
- **Modern DevOps Practices**: Built and deployed using Docker and GitHub Actions.

## Technologies Used
- **Backend Framework**: Spring Boot
- **Programming Language**: Java
- **Database**: PostgreSQL (Amazon RDS)
- **Containerization**: Docker
- **Cloud Hosting**: AWS (ECS, ECR, and Route 53)
- **Storage**:: AWS S3
- **CI/CD**: GitHub Actions
- **API Security**: JWT-based authentication

## Live URLs
- **API Base URL**: [https://api.notez.online](https://api.notez.online)
- **Frontend Application**: [https://notez.online](https://notez.online)

## Local Development Setup

### Prerequisites
Ensure the following are installed on your machine:
- Java 17
- Docker
- PostgreSQL
- AWS CLI (for deployment)

### Steps to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/tosin-dotcom/notez-backend.git
   cd notez-backend


2. **Set up environment variables** by creating a `.env` file:

   ```env
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=notez_db
   DB_USER=your_db_user
   DB_PASSWORD=your_db_password
   JWT_SECRET=your_jwt_secret

3. Build the project:

```bash

./gradlew clean build
```

4. Run the application:
```bash
java -jar build/libs/notez-backend.jar
```

---

## Access the API
- **Local URL**: [http://localhost:8080](http://localhost:8080)
- **Swagger Documentation**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Deployment
The backend is deployed to **AWS ECS** and utilizes a **PostgreSQL database** hosted on Amazon RDS. The live API is accessible via:

- **API URL**: [https://api.notez.online](https://api.notez.online)
