# editorial-api · API REST Editorial (Spring Boot + MongoDB)

Implementación del diseño del taller: **tres capas** (Controller → Service → Repository) sobre el modelo de dominio **Publicacion / Libro / Disco**, persistido en **MongoDB** (colección `publicaciones`).

## Requisitos

- Java 17 o superior
- Maven 3.9+ (o el Maven integrado de IntelliJ / VS Code)
- MongoDB local (`mongodb://localhost:27017`) o una base en MongoDB Atlas

## Ejecutar

```bash
cd editorial-api
mvn spring-boot:run
```

Con MongoDB Atlas, antes de ejecutar:

```bash
# Windows (PowerShell)
$env:MONGODB_URI="mongodb+srv://usuario:clave@cluster.mongodb.net/editorial"
# Linux / macOS
export MONGODB_URI="mongodb+srv://usuario:clave@cluster.mongodb.net/editorial"
```

La API queda en `http://localhost:8080`. En `peticiones.http` hay ejemplos listos para probar (VS Code REST Client o IntelliJ HTTP Client).

## Estructura (trazabilidad con el diagrama de tres capas)

```
src/main/java/co/edu/ucc/editorial/
├── EditorialApplication.java
├── controller/   ← Capa de Presentación
│   ├── PublicacionController.java   /api/publicaciones
│   ├── LibroController.java         /api/libros
│   └── DiscoController.java         /api/discos
├── service/      ← Capa de Negocio (reglas del caso de uso)
│   ├── PublicacionService.java
│   ├── LibroService.java            validarIsbnUnico -> 409
│   └── DiscoService.java            validarDuracion  -> 400
├── repository/   ← Capa de Datos
│   └── PublicacionRepository.java   MongoRepository<Publicacion, String>
├── model/        ← Modelo de dominio (no es una capa)
│   ├── Publicacion.java (abstracta)
│   ├── Libro.java
│   └── Disco.java
└── exception/    ← Manejo centralizado de errores (@RestControllerAdvice)
```

## Contrato

| Método y ruta | Éxito | Errores |
|---|---|---|
| `GET /api/publicaciones` | 200 | — |
| `GET /api/publicaciones/{id}` | 200 | 404 |
| `DELETE /api/publicaciones/{id}` | 204 | 404 |
| `POST /api/libros` | 201 | 400 · 409 (ISBN repetido) |
| `GET /api/libros` | 200 | — |
| `PUT /api/libros/{id}` | 200 | 400 · 404 · 409 |
| `POST /api/discos` | 201 | 400 |
| `GET /api/discos` | 200 | — |
| `PUT /api/discos/{id}` | 200 | 400 · 404 |
