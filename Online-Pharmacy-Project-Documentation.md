# Online Pharmacy – Project Documentation

---

## Table of Contents

1. Project Overview
2. Architecture Overview
3. Service Registry (Eureka)
4. API Gateway
5. Security Architecture
6. JWT Token – Structure and Flow
7. Security Layer by Service
8. Role-Based Access Control (RBAC)
9. Service-wise API Reference
   - 9.1 User Service
   - 9.2 Catalog Service
   - 9.3 Order Service
   - 9.4 Admin Service
10. Inter-Service Communication
11. Swagger UI Access
12. Port Reference

---

## 1. Project Overview

Online Pharmacy is a microservices-based backend application built with **Spring Boot** and **Spring Cloud**. It allows customers to register, browse medicines, place orders, and manage their profiles. Administrators can manage medicines, update order statuses, and view all registered users.

The system is composed of six independent services that communicate through a central API Gateway and register themselves with a Eureka Service Registry.

**Technology Stack**

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.x / 4.1.0 |
| Security | Spring Security + JWT (JJWT 0.12.6) |
| Service Discovery | Spring Cloud Netflix Eureka |
| API Gateway | Spring Cloud Gateway (WebFlux) |
| Inter-service calls | OpenFeign |
| Database | MySQL |
| API Documentation | SpringDoc OpenAPI (Swagger UI) |
| Build Tool | Maven |

---

## 2. Architecture Overview

```
Client (Browser / Postman)
        |
        v
  ┌─────────────┐
  │  API Gateway │  :8080
  │ (Port 8080)  │  — JWT validation
  │              │  — Role enforcement (/api/admin)
  │              │  — Route to downstream services
  └──────┬───────┘
         |
         | (load-balanced via Eureka)
    ─────┼──────────────────────────────────
    |         |              |           |
    v         v              v           v
┌────────┐ ┌──────────┐ ┌────────┐ ┌────────┐
│  User  │ │ Catalog  │ │ Order  │ │ Admin  │
│Service │ │ Service  │ │Service │ │Service │
│ :8081  │ │  :8082   │ │ :8083  │ │ :8084  │
└────────┘ └──────────┘ └────────┘ └────────┘
    |              |          |          |
    v              v          v          v
 MySQL DB      MySQL DB    MySQL DB   (No DB –
(users)       (medicines)  (orders)  calls User
                                      Service
                                      via Feign)

All services register with:
┌──────────────────┐
│  Eureka Server   │  :8761
└──────────────────┘
```

**Request Flow:**

1. The client sends a request to the API Gateway on port 8080.
2. The Gateway's `JwtGatewayFilter` intercepts every request.
3. Public paths (login, signup, view medicines) are passed through directly.
4. For all other paths, the filter validates the JWT token.
5. If the path starts with `/api/admin`, the filter additionally checks that the token contains `role = ADMIN`. Non-admin tokens receive HTTP 403.
6. Valid requests are routed via Eureka load balancer to the correct downstream service.
7. Each downstream service has its own `JwtAuthFilter` that re-validates the token and loads the role into Spring Security's `SecurityContext`.
8. `@PreAuthorize` annotations on controller methods enforce fine-grained RBAC within each service.

---

## 3. Service Registry (Eureka)

**Port:** 8761
**File:** `eureka-service`

The Eureka server acts as the service registry. Every microservice registers itself on startup with its name and address. The API Gateway uses Eureka to discover service instances and load-balance requests with the `lb://` URI scheme.

Eureka does not participate in security. It simply tracks which services are alive and where they are running.

**Why it is here:** Without Eureka, the gateway would need hard-coded service URLs. Eureka enables dynamic discovery and horizontal scaling — you can run multiple instances of any service and the gateway will distribute traffic automatically.

---

## 4. API Gateway

**Port:** 8080
**File:** `api-gateway`
**Class:** `JwtGatewayFilter` (implements `GlobalFilter`)

The gateway is the single entry point for all client requests. No client ever calls a downstream service directly.

**Route Configuration (`application.properties`)**

| Route ID | Path Pattern | Downstream Service |
|---|---|---|
| user-service | `/api/users/**` | `lb://user-service` |
| catalog-service | `/api/medicine/**` | `lb://catalog-prescription-service` |
| order-service | `/api/orders/**` | `lb://order-service` |
| admin-service | `/api/admin/**` | `lb://admin-service` |

**Gateway Security Logic (`JwtGatewayFilter`)**

The filter runs on every request before routing:

```
1. Is the path public?
      YES → pass through (no token needed)
       NO → read Authorization header

2. Is the header missing or not "Bearer ..."?
      YES → return 401 Unauthorized

3. Is the JWT token invalid / expired?
      YES → return 401 Unauthorized

4. Does the path start with /api/admin?
      YES → check if role == "ADMIN"
              NO  → return 403 Forbidden
              YES → continue

5. Forward request to downstream service
```

**Public Paths (no JWT required at gateway)**

- `POST /api/users/login`
- `POST /api/users/signup`
- `GET /api/medicine/allMedicine`
- `/swagger-ui/**`
- `/v3/api-docs/**`
- `/actuator/**`
- Per-service Swagger paths (e.g. `/user-service/swagger-ui/**`)

**Why the gateway has its own JWT check:** The gateway is the outer security boundary. If a request reaches a downstream service directly (bypassing the gateway), each service also has its own JWT filter as a second line of defense. The gateway check is the first line and prevents bad requests from ever hitting the network interior.

---

## 5. Security Architecture

The project uses a **two-layer security model**:

```
Layer 1 – API Gateway (JwtGatewayFilter)
  - Validates token exists and is not expired
  - Enforces coarse-grained access: ADMIN-only for /api/admin/**
  - Rejects unauthenticated access to all non-public routes

Layer 2 – Each Service (JwtAuthFilter + @PreAuthorize)
  - Re-validates the token independently
  - Loads role into Spring SecurityContext as GrantedAuthority
  - @PreAuthorize on each endpoint enforces fine-grained access rules
```

This defense-in-depth design ensures that even if the gateway is misconfigured or bypassed, unauthorized requests are still rejected by the target service.

---

## 6. JWT Token – Structure and Flow

### Token Generation (User Service)

When a user logs in successfully, the `JwtService` in the user service generates a signed JWT:

```
Header:  { alg: HS256 }
Payload: {
  sub:    "user@email.com",   // email (subject)
  userId: 1,                  // user database ID
  role:   "CUSTOMER",         // or "ADMIN"
  iat:    <issued-at>,
  exp:    <issued-at + 1 hour>
}
Signature: HMAC-SHA256 with shared secret
```

**Secret key:** `online-pharmacy-secret-key-for-jwt-token-generation-2026`
(same secret is configured in all services — shared signing key)

### Token Usage

The client stores the token and sends it in every subsequent request:

```
Authorization: Bearer <token>
```

### Token Validation (every service)

Each service's `JwtAuthFilter` does the following:

1. Reads the `Authorization` header.
2. Strips the `Bearer ` prefix.
3. Parses and verifies the signature using the shared secret.
4. Extracts `email` and `role` from the claims.
5. Creates a `UsernamePasswordAuthenticationToken` with `ROLE_<role>` as the granted authority.
6. Stores it in `SecurityContextHolder` so `@PreAuthorize` can read it.

If the token is missing, malformed, or expired, the filter clears the context and lets Spring Security reject the request as 401.

---

## 7. Security Layer by Service

### User Service
| Component | File | Purpose |
|---|---|---|
| `JwtService` | `security/JwtService.java` | Generates JWT tokens on login |
| `JwtAuthFilter` | `security/JwtAuthFilter.java` | Validates incoming JWT, loads authorities from DB via `CustomUserDetailsService` |
| `CustomUserDetailsService` | `security/CustomUserDetailsService.java` | Loads user from DB by email, maps role to `ROLE_<role>` authority |
| `SecurityConfig` | `secrityConfig/SecurityConfig.java` | Stateless session, permits login/signup/swagger, `@EnableMethodSecurity` enabled |

Note: User service loads authorities from the database (via `CustomUserDetailsService`) rather than reading them from the JWT claims. This is the only service that does this — all other services read role directly from JWT claims.

### Catalog Service
| Component | File | Purpose |
|---|---|---|
| `JwtService` | `security/JwtService.java` | Parses and validates JWT, extracts email and role from claims |
| `JwtAuthFilter` | `security/JwtAuthFilter.java` | Validates token, sets `ROLE_<role>` authority in SecurityContext |
| `SecurityConfig` | `security/SecurityConfig.java` | Permits public medicine read endpoints, `@EnableMethodSecurity` enabled |

### Order Service
| Component | File | Purpose |
|---|---|---|
| `JwtService` | `security/JwtService.java` | Parses and validates JWT, extracts email, userId, and role |
| `JwtAuthFilter` | `security/JwtAuthFilter.java` | Validates token, sets `ROLE_<role>` authority in SecurityContext |
| `SecurityConfig` | `security/SecurityConfig.java` | All order endpoints require authentication, `@EnableMethodSecurity` enabled |

### Admin Service
| Component | File | Purpose |
|---|---|---|
| `JwtService` | `security/JwtService.java` | Parses and validates JWT, extracts email and role |
| `JwtAuthFilter` | `security/JwtAuthFilter.java` | Validates token, sets `ROLE_<role>` authority in SecurityContext |
| `SecurityConfig` | `security/SecurityConfig.java` | All endpoints require authentication, `@EnableMethodSecurity` enabled |
| `FeignConfig` | `config/FeignConfig.java` | Passes Authorization header from incoming request to outgoing Feign calls (JWT forwarding) |

---

## 8. Role-Based Access Control (RBAC)

The system has two roles:

| Role | Description |
|---|---|
| `CUSTOMER` | Default role assigned to every new user on signup. Can browse medicines, place orders, view and update their own profile. |
| `ADMIN` | Elevated role. Can do everything CUSTOMER can, plus manage medicines, update order statuses, and view all users. |

### How RBAC Works End-to-End

```
1. User logs in → JWT issued with role = "CUSTOMER" or "ADMIN"
2. Request arrives at API Gateway
3. Gateway extracts role from JWT
4. If path = /api/admin/** and role ≠ ADMIN → 403 Forbidden (gateway rejects)
5. Request forwarded to service
6. Service JwtAuthFilter sets SecurityContext with ROLE_CUSTOMER or ROLE_ADMIN
7. Spring Security evaluates @PreAuthorize("hasRole('ADMIN')") on the method
8. If role doesn't match → 403 Forbidden (service rejects)
```

### RBAC Matrix – All Endpoints

| Service | Method | Path | CUSTOMER | ADMIN | Public |
|---|---|---|---|---|---|
| **User Service** | POST | `/api/users/signup` | — | — | ✅ |
| | POST | `/api/users/login` | — | — | ✅ |
| | GET | `/api/users/profile/{userId}` | ✅ | ✅ | — |
| | PUT | `/api/users/profile/{userId}` | ✅ | ✅ | — |
| | GET | `/api/users` | ❌ | ✅ | — |
| **Catalog Service** | GET | `/api/medicine/allMedicine` | — | — | ✅ |
| | GET | `/api/medicine/findMedicine/{id}` | — | — | ✅ |
| | POST | `/api/medicine/addMedicine` | ❌ | ✅ | — |
| | PUT | `/api/medicine/update/{id}` | ❌ | ✅ | — |
| | DELETE | `/api/medicine/delete/{id}` | ❌ | ✅ | — |
| | PUT | `/api/medicine/reduceStock/{id}` | ✅ | ✅ | — |
| | PUT | `/api/medicine/restoreStock/{id}` | ✅ | ✅ | — |
| **Order Service** | POST | `/api/orders` | ✅ | ✅ | — |
| | GET | `/api/orders/{id}` | ✅ | ✅ | — |
| | GET | `/api/orders/user/{userId}` | ✅ | ✅ | — |
| | PUT | `/api/orders/{id}/status` | ❌ | ✅ | — |
| | PUT | `/api/orders/{id}/cancel` | ✅ | ✅ | — |
| **Admin Service** | GET | `/api/admin/allusers` | ❌ | ✅ | — |

✅ = Allowed, ❌ = Forbidden (403), — = Not applicable

---

## 9. Service-wise API Reference

All requests go through the API Gateway at `http://localhost:8080`.

For authenticated endpoints, include the header:
```
Authorization: Bearer <your-jwt-token>
```

---

### 9.1 User Service
**Base URL:** `/api/users`
**Port (direct):** 8081
**Database:** `online_pharmacy_user`

#### POST /api/users/signup
Register a new user account.

**Auth required:** No

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "9876543210",
  "password": "secret123"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "9876543210",
  "role": "CUSTOMER"
}
```

**Notes:** Password is hashed with BCrypt before storage. Role is always set to `CUSTOMER` on signup.

---

#### POST /api/users/login
Authenticate and receive a JWT token.

**Auth required:** No

**Request Body:**
```json
{
  "email": "john@example.com",
  "password": "secret123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Notes:** The token is valid for 1 hour. Use it in the `Authorization: Bearer <token>` header for all subsequent requests.

---

#### GET /api/users/profile/{userId}
Get the profile of a specific user.

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "9876543210",
  "role": "CUSTOMER"
}
```

---

#### PUT /api/users/profile/{userId}
Update a user's profile.

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Request Body:** Fields to update (name, phone, etc.)

**Response (200 OK):** Updated `UserResponse`

---

#### GET /api/users
Get a list of all registered users.

**Auth required:** Yes
**Allowed roles:** ADMIN only

**Response (200 OK):** Array of `UserResponse` objects

---

### 9.2 Catalog Service
**Base URL:** `/api/medicine`
**Port (direct):** 8082
**Database:** `demo_db`

#### GET /api/medicine/allMedicine
Retrieve all medicines in the catalog.

**Auth required:** No (public)

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Paracetamol",
    "brand": "Calpol",
    "category": "Painkiller",
    "description": "Fever and pain relief",
    "price": 12.50,
    "stockQuantity": 200
  }
]
```

---

#### GET /api/medicine/findMedicine/{id}
Find a specific medicine by its ID.

**Auth required:** No (public)

**Response (200 OK):** Single `Medicine` object

---

#### POST /api/medicine/addMedicine
Add a new medicine to the catalog.

**Auth required:** Yes
**Allowed roles:** ADMIN only

**Request Body:**
```json
{
  "name": "Ibuprofen",
  "brand": "Brufen",
  "category": "Anti-inflammatory",
  "description": "Pain and inflammation relief",
  "price": 25.00,
  "stockQuantity": 150
}
```

**Response (201 Created):** The saved `Medicine` object with generated ID

---

#### PUT /api/medicine/update/{id}
Update an existing medicine's details.

**Auth required:** Yes
**Allowed roles:** ADMIN only

**Request Body:** Updated `Medicine` fields

**Response (200 OK):** Updated `Medicine` object

---

#### DELETE /api/medicine/delete/{id}
Remove a medicine from the catalog.

**Auth required:** Yes
**Allowed roles:** ADMIN only

**Response (200 OK):**
```
Medicine deleted successfully
```

---

#### PUT /api/medicine/reduceStock/{id}?quantity={qty}
Reduce the stock of a medicine (called when an order is placed).

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Query Param:** `quantity` – number of units to deduct

**Response (200 OK):** Updated `Medicine` object

---

#### PUT /api/medicine/restoreStock/{id}?quantity={qty}
Restore the stock of a medicine (called when an order is cancelled).

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Query Param:** `quantity` – number of units to restore

**Response (200 OK):** Updated `Medicine` object

---

### 9.3 Order Service
**Base URL:** `/api/orders`
**Port (direct):** 8083
**Database:** `online_pharmacy_order`

#### POST /api/orders
Place a new order.

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Request Body:**
```json
{
  "userId": 1,
  "items": [
    { "medicineId": 1, "quantity": 2 },
    { "medicineId": 3, "quantity": 1 }
  ]
}
```

**Response (201 Created):**
```json
{
  "orderId": 10,
  "userId": 1,
  "totalAmount": 62.50,
  "status": "PENDING",
  "createdAt": "2026-08-21T10:30:00",
  "items": [...]
}
```

---

#### GET /api/orders/{id}
Get an order by its ID.

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Response (200 OK):** `OrderResponse` object

---

#### GET /api/orders/user/{userId}
Get all orders placed by a specific user.

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Response (200 OK):** Array of `OrderResponse` objects

---

#### PUT /api/orders/{id}/status?status={status}
Update the status of an order.

**Auth required:** Yes
**Allowed roles:** ADMIN only

**Query Param:** `status` – one of `PENDING`, `CONFIRMED`, `SHIPPED`, `DELIVERED`, `CANCELLED`

**Response (200 OK):** Updated `OrderResponse`

**Notes:** Only admins can change order status. This is how the pharmacy processes, ships, and delivers orders.

---

#### PUT /api/orders/{id}/cancel
Cancel an order.

**Auth required:** Yes
**Allowed roles:** CUSTOMER, ADMIN

**Response (200 OK):** Updated `OrderResponse` with status `CANCELLED`

---

### 9.4 Admin Service
**Base URL:** `/api/admin`
**Port (direct):** 8084
**Database:** None (delegates to User Service via Feign)

#### GET /api/admin/allusers
Retrieve a list of all registered users in the system.

**Auth required:** Yes
**Allowed roles:** ADMIN only

**How it works:** The Admin Service does not have its own database. When this endpoint is called, it uses OpenFeign to make an HTTP call to the User Service (`GET /api/users`) and returns the result. The JWT from the incoming request is automatically forwarded to the User Service via `FeignConfig`.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210",
    "role": "CUSTOMER"
  }
]
```

---

## 10. Inter-Service Communication

The Admin Service calls the User Service internally using **OpenFeign**.

**FeignConfig** (`admin-service/config/FeignConfig.java`) contains a `RequestInterceptor` that reads the `Authorization` header from the active HTTP request and attaches it to every outgoing Feign call. This means the JWT token travels from the client → Gateway → Admin Service → User Service without the admin service needing to generate a new token.

```
Client
  → Authorization: Bearer <admin-jwt>
  → API Gateway (validates, allows ADMIN)
  → Admin Service (validates, allows ADMIN)
    → Feign call to User Service
    → Authorization: Bearer <admin-jwt>  (forwarded automatically)
    → User Service (validates, allows ADMIN for GET /api/users)
```

---

## 11. Swagger UI Access

Each service exposes a Swagger UI for interactive API testing. Access them directly on the service's own port:

| Service | Swagger UI URL |
|---|---|
| User Service | http://localhost:8081/swagger-ui/index.html |
| Catalog Service | http://localhost:8082/swagger-ui/index.html |
| Order Service | http://localhost:8083/swagger-ui/index.html |
| Admin Service | http://localhost:8084/swagger-ui/index.html |

**How to use Swagger UI with authentication:**

1. Open any Swagger UI URL in a browser.
2. Call `POST /api/users/login` from within Swagger to get a JWT token.
3. Click the **Authorize** button (lock icon at top right).
4. Enter `<your-token>` in the `bearerAuth` field and click Authorize.
5. All subsequent requests from Swagger will include the `Authorization: Bearer <token>` header automatically.

---

## 12. Port Reference

| Service | Port | Role |
|---|---|---|
| Eureka Server | 8761 | Service Registry |
| API Gateway | 8080 | Single entry point for all clients |
| User Service | 8081 | Registration, login, profile management |
| Catalog Service | 8082 | Medicine catalog, stock management |
| Order Service | 8083 | Order placement and lifecycle |
| Admin Service | 8084 | Admin operations, user listing |

---

*Document generated for Online Pharmacy microservices project — August 2026*
