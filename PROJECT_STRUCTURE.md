## Base Package

All code should stay under:

`com.restaurant.api`

## Folders You Keep

### `entities`
JPA entities mapped to database tables.

### `enums`
Shared enums used by entities and API payloads.

### `repository`
Spring Data repositories (`JpaRepository`) for each entity.

### `controller`
REST endpoints (`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`).

### `dto`
API DTOs only where needed.

Use subfolders:
- `dto.request` for incoming payloads (optional for minimum),
- `dto.response` for output payloads (especially HATEOAS responses).

### `service`
Optional business layer for shared logic.

For a strict minimum, controllers may call repositories directly.
If logic grows, move it here.

### `exception`
Custom exceptions and error response models.

Use one global handler class in this package or in `controller`.

## Minimal Naming Convention

- Controller: `ClientController`
- Repository: `ClientRepository`
- DTO: `ClientResponse`, `CreateClientRequest`
- Exception: `ResourceNotFoundException`
- Error response: `ApiError`

## Minimal Implementation Order

1. Add repositories for all entities.
2. Add CRUD endpoints in controllers.
3. Add a few `GET` endpoints with query parameters.
4. Add HATEOAS links in response DTOs for key relations.
5. Add global 4xx/5xx error handling.
6. Verify Swagger (`/swagger-ui/index.html`).
