# 🍽️ Restaurant Management API

Enterprise-grade RESTful API for restaurant operations, order processing and sales analytics.

> [!IMPORTANT]
> **Academic Prototype:** This project uses **Hard Deletes** to fulfill CRUD requirements. In production environments, *
*Soft Deletes** (`isActive = false`) should be used to preserve report history and prevent data loss.

---

## 📸 Screenshots & Demo

* **Swagger UI** (`/swagger-ui.html`) - Interactive API documentation with all endpoints
* **Postman Collection** - Example requests and responses for all scenarios
* **HATEOAS Links** - JSON responses showing dynamic `_links` navigation
* **H2 Console** - Database state and sample data visualization
* **Error Responses** - Uniform JSON error handling examples
* **Report Endpoints** - Aggregated analytics data (daily sales, popular dishes)

---

## 🛠️ How it works & Key Features

Restaurant management system managing **Clients**, **Dishes** and **Orders** with full CRUD operations and order
lifecycle (`NEW` -> `IN_PREPARATION` -> `READY` -> `COMPLETED`) for `DINE_IN`, `TAKEAWAY`, and `DELIVERY`.

**Technical highlights:**

* **Type-Safe Enums:** Business rules via `OrderStatus`, `DishCategory`, `OrderType`, `PaymentMethod`
* **Input Validation & Sanitization:** Jakarta `@Valid`, `@NotNull` with `.trim()`, phone formatting and email
  lowercasing to protect database integrity
* **HATEOAS:** Dynamic `_links` for navigation (e.g., `GET /clients/1` -> `/clients/1/orders`)
* **Dynamic Filtering:** URL parameters like `GET /dishes?category=MAIN_COURSE&isAvailable=true`
* **Business Intelligence:** JPQL aggregations for daily sales, popular dishes, order distribution using immutable **Java
  Records**
* **Precise Date Queries:** `LocalDateTime.atStartOfDay()` and `YearMonth.plusMonths()` for accurate time ranges
* **Global Exception Handling:** `@RestControllerAdvice` returning uniform JSON with `timestamp`, `message`, `status`

---

## 📐 Technology Stack

* **Language & Framework:** Java 21 with Spring Boot 4.0.5
* **Persistence:** Spring Data JPA (Hibernate) with custom JPQL queries
* **Database:** H2 (in-memory) with pre-loaded sample data
* **Libraries:** Lombok (`@Data`, `@AllArgsConstructor`), Spring HATEOAS
* **Documentation:** Springdoc OpenAPI 3.0.0 / Swagger UI
* **Build Tool:** Maven
* **Testing:** Postman

---

## 📑 API Scenarios

Tested against comprehensive scenarios:

1. **Resource Management:** Standard CRUD for Clients, Dishes and Orders
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
