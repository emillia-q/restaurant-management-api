# 🍽️ Restaurant Management API

Enterprise-grade RESTful API for restaurant operations, order processing and sales analytics.

> [!IMPORTANT]
> **Academic Prototype:** This project uses **Hard Deletes** to fulfill CRUD requirements. In production environments, *
*Soft Deletes** (`isActive = false`) should be used to preserve order history and prevent data loss.

---

## 📸 Screenshots & Demo

* **Swagger UI** (`/swagger-ui.html`) - Interactive API documentation with all endpoints
* **Postman Collection** - Example requests and responses for all scenarios
* **HATEOAS Links** - JSON responses showing dynamic `_links` navigation
* **H2 Console** - Database state and sample data visualization
* **Error Responses** - Uniform JSON error handling examples
* **Report Endpoints** - Aggregated analytics data (daily sales, popular dishes)

---

## 🛠️ How it works

The API manages restaurant resources: **Clients**, **Dishes**, and **Orders**. Supports order types: **Dine-In,
Takeaway, and Delivery** with automatic price calculation, input sanitization, field validation, and order status
updates through a robust pipeline.

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
* **Data Sanitization:** Automated cleaning via `.trim()`, phone formatting, and email lowercasing to protect database
  integrity
* **HATEOAS Implementation:** Dynamic `_links` in responses for navigation between related resources (e.g., Client →
  Order History)
* **Advanced Filtering:** Query by dish category (`MAIN_COURSE`), availability status (`isAvailable`), or order status (
  `NEW`, `IN_PREPARATION`, etc.)
* **Reporting & Analytics:** JPQL aggregations (`SUM`, `COUNT`, `GROUP BY`) mapped to immutable Java Records for
  type-safe DTOs
* **Date Range Queries:** Time-based intervals using `atStartOfDay()` and `plusMonths()` for accurate daily/monthly
  reports
* **Global Exception Handling:** Centralized `@ControllerAdvice` returning uniform JSON errors with proper HTTP status
  codes

---

## 📑 API Scenarios

Tested against comprehensive scenarios:

1. **Resource Management:** Standard CRUD for Clients, Dishes, and Orders
2. **HATEOAS Navigation:** Dynamic link traversal between related resources
3. **Advanced Filtering:** Query parameters for category, availability, order status
4. **Reporting:** Daily sales, popular dishes by month, orders by delivery type
5. **Error Handling:** Validation of payloads, missing parameters, invalid Enums (4xx/5xx responses)

*Full endpoint documentation with example payloads: [scenario_list.md](scenario_list.md)*

---

## 👥 The Team

This project was a collaborative effort by:

* **Emilia Kura**
* **Maja Kucab**
