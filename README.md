# 🔗 ChotuUrl

A high-performance, lightweight URL shortener backend service built with Spring Boot, Spring Data JPA, and PostgreSQL. It generates short, unique identifiers for long URLs using Base62 encoding and provides fast, persistent HTTP redirects.

---

## 🛠️ Tech Stack

* **Language:** Java 17 / 21
* **Framework:** Spring Boot 3
* **Database:** PostgreSQL (Production) / H2 (Development & Testing)
* **OR Mapping:** Spring Data JPA / Hibernate
* **Build Tool:** Maven

---

## ✨ Features

* ⚡ **URL Shortening:** Encodes auto-incrementing database IDs into Base62 strings for minimal length.
* 🔀 **Redirection:** Fast HTTP 302 redirects from short codes to original target URLs.
* 🛡️ **Validation & Error Handling:** Centralized REST exception handling for invalid URLs and missing entries.
* ⚙️ **Multi-Environment Configuration:** Dedicated profile configurations (`dev`, `prod`) using standard Spring environment properties.

---

## 📁 Repository Structure

```text
ChotuUrl/
├── src/
│   ├── main/
│   │   ├── java/com/chotuURL/url/
│   │   │   ├── controller/
│   │   │   │   ├── UrlController.java       # API endpoints for URL creation & management
│   │   │   │   └── RedirectController.java  # Handles resolution & HTTP redirect execution
│   │   │   ├── dto/
│   │   │   │   └── CreateShortUrlRequest.java
│   │   │   ├── entity/
│   │   │   │   └── ShortUrl.java            # JPA Entity model
│   │   │   ├── repository/
│   │   │   │   └── ShortUrlRepository.java  # Data access interface
│   │   │   ├── service/
│   │   │   │   ├── UrlService.java          # Primary business logic
│   │   │   │   └── ShortCodeService.java    # Code generation logic
│   │   │   └── util/
│   │   │       └── Base62.java              # Custom Base62 conversion logic
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
└── pom.xml
```

# 📡 API Endpoints
1. Create Short URL
URL: POST /api/v1/urls

Content-Type: application/json
Request Body:
{
  "longUrl": "[https://www.example.com/very/long/url/path/to/resource](https://www.example.com/very/long/url/path/to/resource)"
}
Response (201 Created):
{
  "id": 1,
  "longUrl": "[https://www.example.com/very/long/url/path/to/resource](https://www.example.com/very/long/url/path/to/resource)",
  "shortCode": "b",
  "createdAt": "2026-10-26T10:00:00"
}

## 2. Redirect Short URL
URL: GET /{shortCode}

Response: 302 Found (Redirects user to original longUrl)

# 🚀 Getting Started
## 📋 Prerequisites
-> JDK 17 or higher
-> Maven 3.8+
-> PostgreSQL database instance

# ⚙️ Local Setup Instructions
## Clone the repository:
git clone [https://github.com/YOUR_USERNAME/ChotuUrl.git](https://github.com/YOUR_USERNAME/ChotuUrl.git)
cd ChotuUrl
## Configure Database Credentials:
Update your database settings in src/main/resources/application-dev.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/chotuurl_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```
## Build the Application:
./mvnw clean package

## Run the Application:
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

## 🧪 Testing
Execute test suites using Maven:
./mvnw test
