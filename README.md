# Haibazo Round 2 - Book Review API

REST API quản lý **Author**, **Book**, **Review** sử dụng Spring Boot + JPA + PostgreSQL.

## Công nghệ sử dụng

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Lombok
- MapStruct
- Maven

## Cấu trúc dự án

- `controller`: cung cấp các REST endpoint
- `service`: xử lý nghiệp vụ
- `repository`: truy vấn cơ sở dữ liệu qua JPA
- `entity`: mô hình dữ liệu database
- `dto/request`: dữ liệu đầu vào từ client
- `dto/response`: dữ liệu trả về cho client
- `mapper`: ánh xạ DTO <-> Entity (MapStruct)

## Base URL

- Port: `8080`
- Context path: `/book-review`

Base URL đầy đủ:

`http://localhost:8080/book-review`

## Tổng quan API

### Author

- `GET /authors`
- `GET /authors/{id}`
- `POST /authors`
- `PUT /authors/{id}`
- `DELETE /authors/{id}`

### Book

- `GET /books`
- `GET /books/{id}`
- `POST /books`
- `PUT /books/{id}`
- `DELETE /books/{id}`

### Review

- `GET /reviews`
- `GET /reviews/{id}`
- `POST /reviews`
- `PUT /reviews/{id}`
- `DELETE /reviews/{id}`

## Ví dụ request

### Tạo Author

`POST /authors`

```json
{
  "name": "Robert C. Martin"
}
```

### Tạo Book

`POST /books`

```json
{
  "name": "Clean Code",
  "authorId": 1
}
```

### Tạo Review

`POST /reviews`

```json
{
  "review": "Very useful for clean architecture.",
  "bookId": 1
}
```

## Cấu hình database (PostgreSQL)

Tạo database:

```sql
CREATE DATABASE "book-review";
```

Cập nhật cấu hình trong `src/main/resources/application.yaml`:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

## Chạy dự án

### Cách 1: Maven Wrapper

```bash
./mvnw spring-boot:run
```

Trên Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

### Cách 2: Build rồi chạy JAR

```bash
./mvnw clean package
java -jar target/haibazo-round-2-0.0.1-SNAPSHOT.jar
```

## Chạy test

```bash
./mvnw test
```

## Gợi ý cải thiện

- Nên dùng `Integer` cho `authorId`/`bookId` trong DTO request.
- Nên thêm validation (`@NotNull`, `@NotBlank`) và `@Valid` ở controller.
- Nên chuẩn hóa lỗi `400/404` bằng `@ControllerAdvice`.


