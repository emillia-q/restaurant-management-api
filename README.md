# 🍽️ Restaurant Management API

Enterprise-grade RESTful API for restaurant operations, order processing and sales analytics.

> [!IMPORTANT]
> **Academic Prototype:** This project uses **Hard Deletes** to fulfill CRUD requirements. In production environments, **Soft Deletes** (`isActive = false`) should be used to preserve order history and prevent data loss.

## 🛠️ How it works

The API supports operations for Clients, Waiters, Kitchen Staff, and Managers. System handles orders for **Dine-In, Takeaway, and Delivery** with automatic price calculation, input sanitization, field validation, and order status updates through a robust pipeline.

---

## 📐 Architecture & Tech Stack

### Technology Stack

* **Language & Framework:** Java 21 with Spring Boot 4.0.5
* **Persistence:** Spring Data JPA (Hibernate)
* **Database:** H2 (in-memory) with sample data
* **Libraries:** Lombok, Spring HATEOAS
* **Documentation:** Springdoc OpenAPI 3.0.0 / Swagger UI
* **Build Tool:** Maven
* **Testing:** Postman

---

## ⚙️ Key Features

* **Full CRUD Operations:** Complete lifecycle management for Clients, Dishes, and Orders with validation
* **Data Sanitization:** Automated cleaning via `.trim()`, phone formatting, and email lowercasing to protect database integrity
* **HATEOAS Implementation:** Dynamic `_links` in responses for navigation between related resources (e.g., Client → Order History)
* **Advanced Filtering:** Query by dish category (`MAIN_COURSE`), availability status, or order execution state
* **Reporting & Analytics:** JPQL aggregations (`SUM`, `COUNT`, `GROUP BY`) mapped to Java Records/DTOs
* **Date Range Queries:** Semi-open intervals (`>= startOfMonth`, `< startOfNextDay`) for accurate daily/monthly reports
* **Global Exception Handling:** Centralized `@ControllerAdvice` returning uniform JSON errors with proper HTTP status codes

---

## 📑 API Scenarios Covered

The entire application was tested and verified against a comprehensive regression test suite including:

1. **Resource Fetching & Management:** Complete standard CRUD operations across all domains.
2. **HATEOAS Navigation:** Dynamic link traversal for relational discovery.
3. **Advanced Request Parameters:** Complex querying and filtering via URL parameters.
4. **Resilience Testing (4xx/5xx):** Strict validation of payload constraints, missing parameters, invalid Enums, and
   non-existent resource handling.

*For a full detailed breakdown of every single endpoint, method, and example payloads,
see [scenario_list.md](scenario_list.md).*

---

## 👥 The Team

This project was a collaborative effort by:

* **Emilia Kura**
* **Maja Kucab**
