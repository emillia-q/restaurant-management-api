# SCENARIO LIST - Restaurant Management API

## 1. CLIENTS

### 1.1 Get client list
- **GET** `/api/v1/clients`
- **Expected result:** `200 OK`

### 1.2 Create a client
- **POST** `/api/v1/clients`
- **Example body:**
```json
{"name":"Jan","lastName":"Kowalski","phoneNumber":"123456789","email":"jan@example.com"}

```

* **Expected result:** `201 Created`

### 1.3 Get client by ID

* **GET** `/api/v1/clients/1`
* **Expected result:** `200 OK`

### 1.4 Update a client

* **PUT** `/api/v1/clients/1`
* **Example body:**

```json
{"name":"Jan","lastName":"Nowak","phoneNumber":"123456789","email":"jan.nowak@example.com"}

```

* **Expected result:** `200 OK`

### 1.5 Delete a client

* **DELETE** `/api/v1/clients/1`
* **Expected result:** `204 No Content`

---

## 2. DISHES

### 2.1 Get dish list

* **GET** `/api/v1/dishes`
* **Expected result:** `200 OK`

### 2.2 Create a dish

* **POST** `/api/v1/dishes`
* **Example body:**

```json
{"name":"Schabowy","category":"MAIN_COURSE","calories":650,"currentPrice":32.50,"isAvailable":true,"isVegetarian":false}

```

* **Expected result:** `201 Created`

### 2.3 Get dish by ID

* **GET** `/api/v1/dishes/1`
* **Expected result:** `200 OK`

### 2.4 Update a dish

* **PUT** `/api/v1/dishes/1`
* **Example body:** (as above, with updated price)
* **Expected result:** `200 OK`

### 2.5 Delete a dish

* **DELETE** `/api/v1/dishes/1`
* **Expected result:** `204 No Content`

---

## 3. ORDERS

### 3.1 Create an order

* **POST** `/api/v1/orders`
* **Example body:**

```json
{"orderStatus":"NEW","paymentMethod":"CARD","type":"DELIVERY","clientId":1,"totalPrice":65.00}

```

* **Expected result:** `201 Created`

### 3.2 Get order by ID

* **GET** `/api/v1/orders/1`
* **Expected result:** `200 OK`

### 3.3 Update order status

* **PUT** `/api/v1/orders/1`
* **Example body:** (status e.g., IN_PREPARATION)
* **Expected result:** `200 OK`

### 3.4 Delete an order

* **DELETE** `/api/v1/orders/1`
* **Expected result:** `204 No Content`

---

## 4. HATEOAS SCENARIOS

### 4.1 Get a dish with links to related resources

* **GET** `/api/v1/dishes/1`
* **Expected result:** `200 OK`, response contains `_links` field (e.g., link to the recipe)

### 4.2 Get a client with a link to their orders

* **GET** `/api/v1/clients/1`
* **Expected result:** `200 OK`, `_links` contains link to `/api/v1/clients/1/orders`

### 4.3 Navigate to client's orders via HATEOAS link

* **GET** `/api/v1/clients/1/orders`
* **Expected result:** `200 OK`, list of the client's orders

---

## 5. REQUEST PARAMETERS SCENARIOS

### 5.1 Filter dishes by category

* **GET** `/api/v1/dishes?category=MAIN_COURSE`
* **Expected result:** `200 OK`

### 5.2 Filter available dishes

* **GET** `/api/v1/dishes?isAvailable=true`
* **Expected result:** `200 OK`

### 5.3 Filter orders by status

* **GET** `/api/v1/orders?orderStatus=NEW`
* **Expected result:** `200 OK`

---

## 6. ERROR HANDLING SCENARIOS (4xx)

### 6.1 Non-existent resource

* **GET** `/api/v1/clients/99999`
* **Expected result:** `404 Not Found`

### 6.2 Invalid data during creation (missing required field)

* **POST** `/api/v1/clients`
* **Example body:**

```json
{"name":"Jan"}

```

* **Expected result:** `400 Bad Request`

### 6.3 Invalid enum value

* **POST** `/api/v1/dishes`
* **Example body:** `{"category":"INVALID", ...}`
* **Expected result:** `400 Bad Request`

### 6.4 Relation to a non-existent client

* **POST** `/api/v1/orders`
* **Example body:** `{"clientId":99999, ...}`
* **Expected result:** `404 Not Found` or `400 Bad Request`

---

## 7. ERROR HANDLING SCENARIOS (5xx)

### 7.1 Server error when database is unavailable

* **GET** `/api/v1/dishes`
* **Expected result:** `500 Internal Server Error` or `503 Service Unavailable`

---

## 8. REPORT SCENARIOS

### 8.1 Get daily sales amount

* **GET** `/api/v1/reports/daily-sales?date=2026-05-28`
* **Expected result:** `200 OK`

### 8.2 Get most popular dishes in a given month

* **GET** `/api/v1/reports/popular-dishes?month=2026-05`
* **Expected result:** `200 OK`

### 8.3 Get order count grouped by delivery type

* **GET** `/api/v1/reports/orders-by-type`
* **Expected result:** `200 OK`
