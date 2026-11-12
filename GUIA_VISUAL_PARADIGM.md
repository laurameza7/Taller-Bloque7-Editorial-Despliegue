# Cómo se construyeron los diagramas en Visual Paradigm (escritorio)

Proyecto: `Taller_Bloque7_Editorial.vpp` (Visual Paradigm 18.1). Contiene **2 diagramas de clases**, exportados a `diagramas/`:

| Diagrama | Archivo |
|---|---|
| 01 Modelo de dominio Editorial | `diagramas/01_modelo_dominio_editorial.png` |
| 02 Diseño por tres capas | `diagramas/02_clases_tres_capas.png` |

## Diagrama 01 – Modelo de dominio Editorial (herencia)

1. **Diagram > New > Class Diagram**.
2. Clase `Publicacion` marcada como **Abstract** (nombre en cursiva) con `-id : String`, `-titulo : String`, `-fechaPublicacion : LocalDate`, `-precio : BigDecimal`.
3. `Libro` (`-isbn : String`, `-numeroPaginas : int`) y `Disco` (`-duracionMinutos : int`, `-formato : String`).
4. **Generalization** de `Libro` y `Disco` hacia `Publicacion` (triángulo hueco en la superclase).

## Diagrama 02 – Diseño por tres capas

1. Cuatro **Package**: `Capa de Presentacion (Controller)`, `Capa de Negocio (Service)`, `Capa de Datos (Repository)` y `Modelo de dominio (no es una capa)`.
2. Clases dentro de cada paquete:

| Clase | Atributos | Operaciones |
|---|---|---|
| PublicacionController | -publicacionService : PublicacionService | +listar() · +obtenerPorId(id : String) · +eliminar(id : String) |
| LibroController | -libroService : LibroService | +registrar(libro : Libro) · +listar() · +actualizar(id : String, libro : Libro) |
| DiscoController | -discoService : DiscoService | +registrar(disco : Disco) · +listar() · +actualizar(id : String, disco : Disco) |
| PublicacionService | -repositorio : PublicacionRepository | +listar() · +buscarPorId(id : String) · +eliminar(id : String) |
| LibroService | -repositorio : PublicacionRepository | +registrar · +listarLibros · +actualizar · -validarIsbnUnico(isbn : String) |
| DiscoService | -repositorio : PublicacionRepository | +registrar · +listarDiscos · +actualizar · -validarDuracion(minutos : int) |
| PublicacionRepository | — | +save · +findAll · +findById · +deleteById |
| Publicacion, Libro, Disco | (igual que el diagrama 01) | — |

3. **Association** navegable Controller → Service y Service → Repository (la clase guarda una referencia a su colaborador).
4. **Dependency** `«persiste»` de `PublicacionRepository` a `Publicacion` y `«usa»` de `LibroService` → `Libro` y `DiscoService` → `Disco`.
5. Exportación: **Project > Export > Diagrams as Image** (PNG).
