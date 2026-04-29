# Corporate Device Management API

A RESTful backend for managing corporate mobile assets — clients, employees, devices, and SIM lines — built as a portfolio project to practise **Hexagonal Architecture** and **Domain-Driven Design** with Java and Spring Boot.

---

## Live Demo

> **Both services are hosted on Render's free tier and may take up to 50 seconds to respond on the first request after a period of inactivity.**
>
> **To use the app, open both links and wait for each service to finish waking up before interacting with the frontend.**

| Service | URL |
|---|---|
| Swagger UI | https://corporate-device-management-api.onrender.com/swagger-ui/index.html |
| Frontend (React) | https://digital-citizen-app.onrender.com |

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.3.4 |
| Persistence | PostgreSQL · Spring Data JPA |
| Inter-service calls | Spring Cloud OpenFeign |
| API docs | SpringDoc OpenAPI 2.5.0 (Swagger UI) |
| Validation | Spring Boot Starter Validation |
| Boilerplate reduction | Lombok |
| Containerisation | Docker (multi-stage build) |

---

## Architecture

The project follows **Hexagonal Architecture** (Ports & Adapters), keeping domain logic completely isolated from frameworks and infrastructure.

```
src/main/java/com/tfg/digitalcitizen/platform/
│
├── client_service/
│   ├── core/               # Domain model + port interfaces
│   ├── application/        # Use cases, DTOs, mappers
│   └── infrastructure/     # REST controllers, JPA repositories
│
├── device_service/         # same structure
├── line_service/           # same structure
├── user_service/           # same structure
├── report_service/         # same structure + external Feign adapters
│
└── shared/                 # Cross-cutting: exceptions, value objects, config
```

**Dependency rule:** `infrastructure` → `application` → `core`. Nothing in `core` depends on Spring or JPA.

---

## API Endpoints

### Clients — `/clients`

| Method | Path | Description |
|---|---|---|
| GET | `/clients` | List all clients |
| GET | `/clients/{id}` | Get client by ID |
| POST | `/clients` | Create a client |
| PUT | `/clients/{id}` | Update a client |
| DELETE | `/clients/{id}` | Delete a client |

### Devices — `/devices`

| Method | Path | Description |
|---|---|---|
| GET | `/devices` | List all devices |
| GET | `/devices/{id}` | Get device by ID |
| GET | `/devices/client/{clientId}` | List devices by client |
| GET | `/devices/employee/{employeeId}` | List devices assigned to an employee |
| POST | `/devices` | Register a device |
| PUT | `/devices/{id}` | Update a device |
| DELETE | `/devices/{id}` | Delete a device |

### Lines — `/lines`

| Method | Path | Description |
|---|---|---|
| GET | `/lines` | List all lines |
| GET | `/lines/active` | List active lines |
| GET | `/lines/{id}` | Get line by ID |
| GET | `/lines/client/{clientId}` | List lines by client |
| POST | `/lines` | Create a line |
| PUT | `/lines/{id}` | Update a line |
| DELETE | `/lines/{id}` | Delete a line |

### Users / Employees — `/users`

| Method | Path | Description |
|---|---|---|
| GET | `/users` | List all users |
| GET | `/users/{id}` | Get user by ID |
| GET | `/users/client/{clientId}` | List users by client |
| POST | `/users` | Create a user |
| PUT | `/users/{id}` | Update a user |
| DELETE | `/users/{id}` | Delete a user |

### Reports — `/reports`

| Method | Path | Description |
|---|---|---|
| GET | `/reports/system` | System-wide summary report |
| GET | `/reports/client/{clientId}` | Full report for a client |
| GET | `/reports/client/{clientId}/devices` | Device health report for a client |
| GET | `/reports/client/{clientId}/lines` | Line usage report for a client |

Full request/response schemas are documented in the [Swagger UI](https://corporate-device-management-api.onrender.com/swagger-ui/index.html).

---

## Running Locally

### Prerequisites

- Java 21+
- Maven 3.9+
- PostgreSQL 15+

### 1. Clone the repository

```bash
git clone https://github.com/cfval/corporate-device-management-api.git
cd corporate-device-management-api
```

### 2. Set up the database

Create a PostgreSQL database named `digital_citizen`:

```sql
CREATE DATABASE digital_citizen;
```

### 3. Configure environment variables

The application reads configuration from environment variables. Defaults are provided for local development:

| Variable | Default | Description |
|---|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/digital_citizen` | JDBC connection URL |
| `DB_USER` | `postgres` | Database username |
| `DB_PASSWORD` | `admin` | Database password |
| `CORS_ALLOWED_ORIGIN` | `http://localhost:5173` | Configurable allowed CORS origin |
| `CLIENT_SERVICE_URL` | `http://localhost:8080` | Internal client service base URL |
| `DEVICE_SERVICE_URL` | `http://localhost:8080` | Internal device service base URL |
| `LINE_SERVICE_URL` | `http://localhost:8080` | Internal line service base URL |
| `USER_SERVICE_URL` | `http://localhost:8080` | Internal user service base URL |

### 4. Build and run

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.
Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Running with Docker

```bash
docker build -t corporate-device-management-api .

docker run -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://host.docker.internal:5432/digital_citizen \
  -e DB_USER=postgres \
  -e DB_PASSWORD=admin \
  corporate-device-management-api
```

---

