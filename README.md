# Book Review API

REST API quản lý Tác giả, Sách và Đánh giá — Spring Boot + PostgreSQL.

## Tech Stack

- Java 21, Spring Boot 4.0.6, Spring Data JPA, PostgreSQL
- MapStruct, Lombok, Maven

## Chạy dự án

**Yêu cầu:** Java 21+, PostgreSQL

```sql
CREATE DATABASE "book-review";
```

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
.\mvnw.cmd spring-boot:run
```

Server chạy tại `http://localhost:8080`

**Biến môi trường (tuỳ chọn):**

| Biến | Mặc định |
| :--- | :--- |
| `PORT` | `8080` |
| `DB_HOST` | `localhost` |
| `DB_NAME` | `book-review` |
| `DB_USER` | `postgres` |
| `DB_PASS` | `123456` |

## API Endpoints

**Base URL:** `http://localhost:8080`

### Authors

| Method | Endpoint | Body |
| :--- | :--- | :--- |
| `GET` | `/authors` | — |
| `GET` | `/authors/{id}` | — |
| `POST` | `/authors` | `{ "name": "string" }` |
| `PUT` | `/authors/{id}` | `{ "name": "string" }` |
| `DELETE` | `/authors/{id}` | — |

### Books

| Method | Endpoint | Body |
| :--- | :--- | :--- |
| `GET` | `/books` | — |
| `GET` | `/books/{id}` | — |
| `POST` | `/books` | `{ "name": "string", "authorId": number }` |
| `PUT` | `/books/{id}` | `{ "name": "string", "authorId": number }` |
| `DELETE` | `/books/{id}` | — |

### Reviews

| Method | Endpoint | Body |
| :--- | :--- | :--- |
| `GET` | `/reviews` | — |
| `GET` | `/reviews/{id}` | — |
| `POST` | `/reviews` | `{ "review": "string", "bookId": number }` |
| `PUT` | `/reviews/{id}` | `{ "review": "string", "bookId": number }` |
| `DELETE` | `/reviews/{id}` | — |

## Phân trang

Các API `GET` danh sách hỗ trợ: `?page=0&size=10&sort=name,asc`

- `page`: trang (mặc định `0`)
- `size`: số phần tử/trang (mặc định `20`)
- `sort`: sắp xếp (VD: `name,asc` hoặc `id,desc`)

## Response Format

Tất cả API trả về cùng cấu trúc:

```json
{
  "success": true,
  "status": 200,
  "message": "Get all books successfully",
  "data": [...],
  "pagination": {
    "page": 0,
    "size": 20,
    "totalElements": 10,
    "totalPages": 1,
    "first": true,
    "last": true
  }
}
```

### Data Models

**Author:**
```json
{ "id": 1, "name": "J.K. Rowling", "booksCount": 5 }
```

**Book:**
```json
{ "id": 1, "name": "Harry Potter", "authorId": 1, "authorName": "J.K. Rowling", "reviewsCount": 12 }
```

**Review:**
```json
{ "id": 1, "bookId": 1, "bookName": "Harry Potter", "authorId": 1, "authorName": "J.K. Rowling", "review": "Great book!" }
```

### Lỗi Validation (400)

```json
{
  "success": false,
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "fieldErrors": {
    "name": "name must not be blank"
  }
}
```

