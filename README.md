# Book Review API - Tài Liệu Tích Hợp Frontend

Tài liệu này cung cấp chi tiết về các endpoint API, cấu trúc dữ liệu và cách gọi API dành cho Frontend Developer.

## 1. Thông Tin Cơ Bản

- **Base URL (Local):** `http://localhost:8080`
- **Content-Type:** `application/json`

---

## 2. Cấu Trúc Response Chung (Chuẩn)

Tất cả các API đều trả về dữ liệu theo cùng một cấu trúc (Wrapper) như sau:

```json
{
  "success": true,               // Trạng thái thành công của request (true/false)
  "status": 200,                 // HTTP Status Code (200, 201, 400, 404, 500...)
  "message": "Success message",  // Thông báo trả về
  "data": { ... },               // Dữ liệu chính (Object, Array, null...)
  "pagination": { ... },         // (Optional) Thông tin phân trang (nếu là list)
  "error": "...",                // (Optional) Tên lỗi khi success = false
  "fieldErrors": { ... },        // (Optional) Lỗi validate các trường dữ liệu
  "timestamp": "2026-..."        // (Optional) Thời gian xảy ra lỗi
}
```

### 2.1 Cấu Trúc Phân Trang (Pagination)

Đối với các API lấy danh sách (`GET /authors`, `GET /books`, `GET /reviews`), response sẽ có thêm trường `pagination`. 
Khi gọi các API này, Frontend có thể truyền `page` (bắt đầu từ 0) và `size` (mặc định 20) qua Query Params.

**Ví dụ Request:** `GET /books?page=0&size=10`

**Cấu trúc Pagination trong Response:**
```json
"pagination": {
  "page": 0,             // Trang hiện tại (bắt đầu từ 0)
  "size": 20,            // Số lượng phần tử mỗi trang
  "totalElements": 100,  // Tổng số lượng phần tử
  "totalPages": 5,       // Tổng số trang
  "first": true,         // Có phải trang đầu tiên không
  "last": false          // Có phải trang cuối cùng không
}
```

---

## 3. Danh Sách API Chi Tiết

### 3.1 Authors (Tác Giả)

#### Lấy danh sách tác giả (Có phân trang)
- **Method:** `GET`
- **URL:** `/authors`
- **Query Params:** `?page=0&size=20` (Optional)
- **Response Data:** Mảng các đối tượng Author
  ```json
  "data": [
    {
      "id": 1,
      "name": "Robert C. Martin"
    }
  ]
  ```

#### Lấy chi tiết tác giả
- **Method:** `GET`
- **URL:** `/authors/{id}`
- **Response Data:** Đối tượng Author

#### Tạo tác giả mới
- **Method:** `POST`
- **URL:** `/authors`
- **Body:**
  ```json
  {
    "name": "Robert C. Martin"
  }
  ```

#### Cập nhật tác giả
- **Method:** `PUT`
- **URL:** `/authors/{id}`
- **Body:**
  ```json
  {
    "name": "Robert C. Martin - Updated"
  }
  ```

#### Xóa tác giả
- **Method:** `DELETE`
- **URL:** `/authors/{id}`
- **Response Data:** `null` (Chỉ cần kiểm tra `success: true`)

---

### 3.2 Books (Sách)

#### Lấy danh sách sách (Có phân trang)
- **Method:** `GET`
- **URL:** `/books`
- **Query Params:** `?page=0&size=20` (Optional)
- **Response Data:** Mảng các đối tượng Book
  ```json
  "data": [
    {
      "id": 1,
      "name": "Clean Code",
      "authorName": "Robert C. Martin"
    }
  ]
  ```

#### Lấy chi tiết sách
- **Method:** `GET`
- **URL:** `/books/{id}`
- **Response Data:** Đối tượng Book

#### Tạo sách mới
- **Method:** `POST`
- **URL:** `/books`
- **Body:**
  ```json
  {
    "name": "Clean Code",
    "authorId": 1
  }
  ```
  *(Lưu ý: `authorId` bắt buộc phải là ID của tác giả đã tồn tại)*

#### Cập nhật sách
- **Method:** `PUT`
- **URL:** `/books/{id}`
- **Body:**
  ```json
  {
    "name": "Clean Architecture",
    "authorId": 1
  }
  ```

#### Xóa sách
- **Method:** `DELETE`
- **URL:** `/books/{id}`

---

### 3.3 Reviews (Đánh Giá)

#### Lấy danh sách đánh giá (Có phân trang)
- **Method:** `GET`
- **URL:** `/reviews`
- **Query Params:** `?page=0&size=20` (Optional)
- **Response Data:** Mảng các đối tượng Review
  ```json
  "data": [
    {
      "id": 1,
      "bookName": "Clean Code",
      "authorName": "Robert C. Martin",
      "review": "Very useful for clean architecture."
    }
  ]
  ```

#### Lấy chi tiết đánh giá
- **Method:** `GET`
- **URL:** `/reviews/{id}`
- **Response Data:** Đối tượng Review

#### Tạo đánh giá mới
- **Method:** `POST`
- **URL:** `/reviews`
- **Body:**
  ```json
  {
    "review": "Very useful for clean architecture.",
    "bookId": 1
  }
  ```
  *(Lưu ý: `bookId` bắt buộc phải là ID của sách đã tồn tại)*

#### Cập nhật đánh giá
- **Method:** `PUT`
- **URL:** `/reviews/{id}`
- **Body:**
  ```json
  {
    "review": "Great book, highly recommend!",
    "bookId": 1
  }
  ```

#### Xóa đánh giá
- **Method:** `DELETE`
- **URL:** `/reviews/{id}`

---

## 4. Chạy Backend Ở Môi Trường Local (Dành cho Frontend)

Nếu bạn cần tự chạy Backend trên máy để test:

1. Đảm bảo đã cài **Java 21** và **PostgreSQL**.
2. Tạo database PostgreSQL có tên `book-review`.
3. Cập nhật mật khẩu DB trong file `src/main/resources/application.yaml` (nếu cần).
4. Mở terminal tại thư mục Backend và chạy lệnh:
   - Trên Windows: `.\mvnw.cmd spring-boot:run`
   - Trên Mac/Linux: `./mvnw spring-boot:run`
5. Mặc định Backend sẽ chạy ở cổng `8080`.
