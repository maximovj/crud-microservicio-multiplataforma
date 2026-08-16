# 📋 Sistema de Pagos - API Collection (Postman)

## 📌 Descripción General

Esta colección de Postman ha sido diseñada para facilitar la interacción con el **Sistema de Pagos**, una arquitectura basada en microservicios que centraliza la gestión de usuarios y transacciones. La colección expone dos vías de comunicación con el sistema:

- **Vía Directa (`ms-api-users`)**: Comunicación directa con el microservicio de usuarios, ideal para pruebas internas y desarrollo.
- **Vía Gateway (`ms-api-gateway`)**: Comunicación a través del API Gateway, que actúa como punto único de entrada, proporcionando capas de seguridad, enrutamiento y balanceo de carga.

Esta estructura permite a los desarrolladores probar los servicios de forma aislada o a través del orquestador central, simulando escenarios de producción.

---

## 🏗️ Arquitectura del Sistema

```
┌─────────────────────────────────────────────────────┐
│                   Cliente / Frontend               │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│              API Gateway (ms-api-gateway)          │
│              - Enrutamiento                        │
│              - Autenticación (futuro)              │
│              - Balanceo de carga                   │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│         Microservicio de Usuarios                  │
│         (ms-api-users - Puerto 3005)               │
│         - Gestión de usuarios                      │
│         - CRUD completo                            │
│         - Autenticación Bearer Token              │
└─────────────────────────────────────────────────────┘
```

---

## 📂 Estructura de la Colección

La colección está organizada jerárquicamente para facilitar la navegación:

### 1. **`ms-api-users`** (Microservicio Directo)

| Endpoint | Método | Descripción Completa |
|----------|--------|---------------------|
| `/api/users` | `POST` | **Creación de usuario**: Registra un nuevo usuario en el sistema. Requiere nombre, apellido, email y contraseña. Retorna el usuario creado con su ID asignado. |
| `/api/users` | `GET` | **Listado de usuarios**: Obtiene todos los usuarios registrados en el sistema. Útil para auditorías y listados administrativos. |
| `/api/users/{id}` | `GET` | **Búsqueda por ID**: Recupera la información detallada de un usuario específico mediante su identificador único. |
| `/api/users/{id}` | `PATCH` | **Actualización parcial**: Modifica uno o varios campos de un usuario existente. Campos permitidos: nombre, apellido, email, contraseña. |
| `/api/users/{id}` | `DELETE` | **Eliminación lógica/física**: Remueve un usuario del sistema. Esta acción es irreversible. |

### 2. **`ms-api-gateway`** (Vía Gateway)
Agrupa las mismas operaciones de usuarios, pero enrutadas a través del API Gateway. Estas solicitudes no requieren autenticación en su configuración actual (aunque el gateway podría añadirla en el futuro).

#### Subgrupo: `users`
Contiene las mismas 5 operaciones CRUD descritas anteriormente, pero apuntando a la URL del gateway.

| Endpoint | Método | Observación |
|----------|--------|-------------|
| `/api/users` | `POST` | Creación de usuario a través del gateway. |
| `/api/users` | `GET` | Listado de usuarios vía gateway. |
| `/api/users/{id}` | `GET` | Búsqueda por ID a través del gateway. |
| `/api/users/{id}` | `PATCH` | Actualización parcial vía gateway. |
| `/api/users/{id}` | `DELETE` | Eliminación vía gateway. |

---


## ⚙️ Variables de Entorno

La colección utiliza variables para hacer los requests completamente configurables sin modificar los endpoints manualmente.

| Variable | Propósito | Formato | Ejemplo |
|----------|-----------|---------|---------|
| `HOSTmsApiUsers` | URL base del microservicio de usuarios. Debe incluir protocolo y puerto. | `http://<host>:<puerto>` | `http://localhost:3005` |
| `URLmsApiGateway` | URL base del API Gateway. Debe incluir protocolo y puerto. | `http://<host>:<puerto>` | `http://localhost:3000` |