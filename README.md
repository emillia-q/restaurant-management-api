# 🍽️ Restaurant Management API

## Enterprise-grade RESTful API for restaurant operations, order processing and sales analytics.

> [!IMPORTANT]
> **Academic Prototype & CRUD Specification:** This project is a prototype to
> practice enterprise API design. To fulfill strict academic requirements for a full CRUD implementation, the system
> performs **Hard Deletes** on records (e.g., physically deleting clients and cascading their data). In a commercial,
> production-ready environment, a **Soft Delete** approach (using flags like `isActive = false`) would be mandatory to
> prevent data loss and preserve financial order history for business intelligence.

## 🛠️ How it works

The backend exposes a secure API designed to support front-end applications for Clients, Waiters, Kitchen Staff, and
Restaurant Managers.
Clients can browse available menus, and system operators can create orders for **Dine-In, Takeaway, or Delivery**. The
system handles total price calculation, automatically formats and sanitizes user input, validates unique fields, and
seamlessly updates order states through a robust status pipeline.

---

## 📐 Architecture & Tech Stack

### Technology Stack & Production Practices

* **Language & Framework:** **Java 21** with **Spring Boot 4.0.5**.
* **Persistence Layer:** **Spring Data JPA (Hibernate)** for object-relational mapping.
* **Database:** **H2 Database** (in-memory) with sample data initialization.
* **Additional Libraries:** **Lombok** for boilerplate reduction, **Spring HATEOAS** for hypermedia-driven APIs.
* **Documentation:** **Springdoc OpenAPI 3.0.0 / Swagger UI** for interactive API documentation.
* **Build Tool:** **Maven** with custom annotation processing configuration.
* **Testing Platform:** **Postman** for automated API endpoint and integration verification.

---

## ⚙️ Key Features

This project implements production-grade backend patterns:

* **Full CRUD Automation:** Complete lifecycle management for Clients, Dishes, and Orders based on a strictly verified
  list of business integration scenarios.
* **Data Sanitization Pipeline:** Custom automated data cleaning (e.g., stripping trailing/leading whitespaces via
  `.trim()`, uniform phone number formatting, and lowercasing e-mails) protecting the database from corrupted smartphone
  autocomplete inputs.
* **HATEOAS Maturity:** Implementation of Hypermedia As the Engine of Application State (`_links`). The API returns
  dynamic links to related resources (e.g., navigating from a Client profile directly to their dynamic Order History
  URL).
* **Advanced Criteria & Filtering:** Dynamic data querying allowing users to filter dishes by categories (e.g.,
  `MAIN_COURSE`), availability status, or retrieve specific orders by their execution state.
* **Enterprise Reporting & Analytics:** High-performance analytical queries using JPQL aggregation (`SUM`, `COUNT`)
  combined with `GROUP BY` statements mapped directly into high-performance Java Records/DTOs.
* **Robust Range Queries:** Date-range calculation utilizing semi-open intervals (`>= startOfMonth` and
  `< startOfNextDay`) ensuring no database index truncation and absolute accuracy in daily/monthly financial reports.
* **Global Exception Handling:** A centralized `@ControllerAdvice` component that intercepts business exceptions (e.g.,
  `ItemNotFoundException`, data duplicity, parsing errors) and translates them into uniform, developer-friendly JSON
  error responses with appropriate HTTP 4xx/5xx status codes.

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
