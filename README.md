# 📚 Book Review API - Frontend Integration Guide

Tài liệu này được thiết kế đặc biệt dành cho **Frontend Developer** để dễ dàng hiểu cấu trúc dữ liệu, các endpoint và cách tích hợp với Backend API.

- **Base URL (Local)**: `http://localhost:8080`
- Tất cả các request và response đều sử dụng định dạng `application/json`.

---

## 📦 1. TypeScript Interfaces (Định dạng Dữ liệu)

Bạn có thể copy trực tiếp các interfaces dưới đây vào dự án Frontend của mình để sử dụng (hỗ trợ TypeScript).

### 1.1. Core Interfaces (Cấu trúc Response dùng chung)

Mọi API trả về đều được bọc trong một `ApiResponse`. Tùy thuộc vào việc gọi API lấy danh sách hay chi tiết mà `data` sẽ là Array hay Object.

```typescript
// Cấu trúc Response chung cho toàn bộ API
export interface ApiResponse<T> {
  success: boolean;
  status: number;       // HTTP Status Code (200, 201, 400, 404, 500...)
  message: string;      // Thông báo từ server
  data?: T;             // Dữ liệu trả về (có thể không có nếu lỗi hoặc API delete)
  pagination?: PaginationMeta; // Chỉ xuất hiện khi gọi API GET danh sách
  
  // Các field dưới đây chỉ xuất hiện khi có lỗi (success = false)
  error?: string;       // Tên loại lỗi (VD: "Bad Request")
  fieldErrors?: Record<string, string>; // Chi tiết lỗi validation (VD: { name: "Must not be blank" })
}

// Thông tin phân trang (dành cho table, list)
export interface PaginationMeta {
  page: number;         // Trang hiện tại (Bắt đầu từ 0)
  size: number;         // Số phần tử trên 1 trang (Mặc định 20)
  totalElements: number;// Tổng số tất cả các phần tử
  totalPages: number;   // Tổng số trang
  first: boolean;       // Có phải trang đầu tiên không?
  last: boolean;        // Có phải trang cuối cùng không?
}
```

### 1.2. Data Models (Các thực thể chính)

```typescript
export interface Author {
  id: number;
  name: string;
  booksCount: number; // Số lượng sách của tác giả
}

export interface Book {
  id: number;
  name: string;
  authorId: number;
  authorName: string;   // Tên tác giả được đính kèm sẵn để Frontend tiện hiển thị
  reviewsCount: number; // Số lượng review của cuốn sách
}

export interface Review {
  id: number;
  bookId: number;
  bookName: string;   // Tên sách được đính kèm sẵn
  authorId: number;
  authorName: string; // Tên tác giả được đính kèm sẵn
  review: string;     // Nội dung đánh giá
}
```

---

## 🚀 2. Danh sách API (Quick Reference)

| Đối tượng | Method | Endpoint | Query Params (GET) | Body (POST/PUT) | Chức năng |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Authors** | `GET` | `/authors` | `page`, `size`, `sort` | - | Lấy danh sách tác giả (có phân trang) |
| | `GET` | `/authors/{id}` | - | - | Lấy chi tiết 1 tác giả |
| | `POST` | `/authors` | - | `{ name: string }` | Thêm mới tác giả |
| | `PUT` | `/authors/{id}` | - | `{ name: string }` | Cập nhật tác giả |
| | `DELETE`| `/authors/{id}` | - | - | Xoá tác giả |
| **Books** | `GET` | `/books` | `page`, `size`, `sort` | - | Lấy danh sách sách (có phân trang) |
| | `GET` | `/books/{id}` | - | - | Lấy chi tiết 1 quyển sách |
| | `POST` | `/books` | - | `{ name, authorId }` | Thêm mới sách |
| | `PUT` | `/books/{id}` | - | `{ name, authorId }` | Cập nhật sách |
| | `DELETE`| `/books/{id}` | - | - | Xoá sách |
| **Reviews** | `GET` | `/reviews` | `page`, `size`, `sort` | - | Lấy danh sách đánh giá (có phân trang) |
| | `GET` | `/reviews/{id}`| - | - | Lấy chi tiết 1 đánh giá |
| | `POST` | `/reviews` | - | `{ review, bookId }`| Thêm mới đánh giá |
| | `PUT` | `/reviews/{id}`| - | `{ review, bookId }`| Cập nhật đánh giá |
| | `DELETE`| `/reviews/{id}`| - | - | Xoá đánh giá |

> **Gợi ý Query Params:**
> - `page`: Trang cần lấy (mặc định là `0`).
> - `size`: Số lượng hiển thị (mặc định là `20`).
> - `sort`: Sắp xếp. VD: `sort=name,asc` hoặc `sort=id,desc`.

---

## 📋 3. Ví dụ Response JSON Chi Tiết

### 3.1. GET /authors?page=0&size=5

```json
{
  "success": true,
  "status": 200,
  "message": "Get all authors successfully",
  "data": [
    {
      "id": 1,
      "name": "J.K. Rowling",
      "booksCount": 5
    },
    {
      "id": 2,
      "name": "George R.R. Martin",
      "booksCount": 0
    }
  ],
  "pagination": {
    "page": 0,
    "size": 5,
    "totalElements": 2,
    "totalPages": 1,
    "first": true,
    "last": true
  }
}
```

### 3.2. GET /books?page=0&size=5

```json
{
  "success": true,
  "status": 200,
  "message": "Get all books successfully",
  "data": [
    {
      "id": 1,
      "name": "Harry Potter and the Sorcerer's Stone",
      "authorId": 1,
      "authorName": "J.K. Rowling",
      "reviewsCount": 12
    }
  ],
  "pagination": {
    "page": 0,
    "size": 5,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true
  }
}
```

### 3.3. GET /reviews?page=0&size=5

```json
{
  "success": true,
  "status": 200,
  "message": "Get all reviews successfully",
  "data": [
    {
      "id": 1,
      "bookId": 1,
      "bookName": "Harry Potter and the Sorcerer's Stone",
      "authorId": 1,
      "authorName": "J.K. Rowling",
      "review": "A magical journey for all ages."
    }
  ],
  "pagination": {
    "page": 0,
    "size": 5,
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true
  }
}
```

### 3.4. POST /books (Tạo mới)

**Request Body:**
```json
{
  "name": "Clean Code",
  "authorId": 1
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "status": 201,
  "message": "Create book successfully",
  "data": {
    "id": 2,
    "name": "Clean Code",
    "authorId": 1,
    "authorName": "J.K. Rowling",
    "reviewsCount": 0
  }
}
```

---

## 🛑 4. Xử lý Lỗi (Error Handling)

Khi gọi API bị lỗi, Frontend cần chú ý bắt các `HTTP Status` sau:

- **`400 Bad Request` (Lỗi Validation):** Xảy ra khi form gửi lên bị thiếu trường hoặc sai định dạng. Frontend dùng `fieldErrors` để hiển thị lỗi màu đỏ dưới các ô input.
- **`404 Not Found`:** Xảy ra khi truyền sai ID hoặc dữ liệu đã bị xóa.
- **`500 Internal Server Error`:** Lỗi từ hệ thống Backend.

**Ví dụ JSON trả về khi lỗi Validation (Mã 400):**
```json
{
  "success": false,
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "fieldErrors": {
    "name": "name must not be blank",
    "authorId": "authorId must not be null"
  }
}
```

---

## ⚙️ 5. Hướng dẫn chạy Backend (Dành cho Dev setup)

Nếu bạn là Frontend Dev và cần tự chạy Backend này ở máy cá nhân (Local):

**Yêu cầu:** Máy đã cài sẵn Java 21+ và PostgreSQL.

**Biến môi trường cần thiết:**
- `PORT` (Mặc định: `8080`)
- `DB_HOST` (Mặc định: `localhost`)
- `DB_NAME` (Mặc định: `book-review`)
- `DB_USER` (Mặc định: `postgres`)
- `DB_PASS` (Mặc định: `123456`)

**Chạy trực tiếp bằng Maven (khuyên dùng cho Local):**
```bash
./mvnw spring-boot:run
```
