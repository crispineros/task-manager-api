# API REST de Gestión de Tareas con Spring Boot

Este es un proyecto de API REST para la gestión de tareas, desarrollado con **Spring Boot**, **PostgreSQL** y documentado con **Swagger**. Se han implementado validaciones, manejo de errores y pruebas unitarias utilizando **JUnit** para garantizar la calidad del código.

## Características

- CRUD completo para tareas (Crear, Leer, Actualizar y Eliminar).
- Validaciones para los datos de entrada.
- Manejo de errores mejorado.
- Documentación con Swagger.
- Persistencia de datos con PostgreSQL.
- Pruebas unitarias con JUnit y Mockito.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger (Springdoc OpenAPI)**
- **JUnit 5 y Mockito**
- **Maven**

## Instalación y configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/crispineros/task-manager-api.git
cd task-manager-api
```

### 2. Configurar la base de datos

Asegúrate de tener **PostgreSQL** instalado y ejecutando. Luego, configura las credenciales en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=(tu_usuario)
spring.datasource.password=(tu_contraseña)
spring.jpa.hibernate.ddl-auto=update
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

## Endpoints principales

| Método | Endpoint    | Descripción                      |
| ------ | ----------- | -------------------------------- |
| GET    | /tasks      | Obtener todas las tareas         |
| GET    | /tasks/{id} | Obtener una tarea por ID         |
| POST   | /tasks      | Crear una nueva tarea            |
| PUT    | /tasks/{id} | Actualizar una tarea             |
| PATCH  | /tasks/{id} | Modificar parcialmente una tarea |
| DELETE | /tasks/{id} | Eliminar una tarea               |

La documentación detallada de los endpoints está disponible en Swagger: [Swagger UI](http://localhost:8080/swagger-ui.html)

## Ejemplo de Uso

### Crear una tarea

**POST** `/tasks`

```json
{
  "title": "Aprender Spring Boot",
  "description": "Revisar tutoriales y practicar",
  "completed": false
}
```

### Obtener todas las tareas

**GET** `/tasks`

aparecera una respuesta como esta:

200 OK

```json
[
  {
    "id": 1,
    "title": "Aprender Spring Boot",
    "description": "Revisar tutoriales y practicar",
    "completed": false
  }
]
```

## Pruebas

Para ejecutar los tests unitarios, usa el siguiente comando:

```bash
mvn test
```

## Contribuciones

Si deseas contribuir, por favor abre un **Issue** o un **Pull Request** en este repositorio.

---

### Autor

**Cristian David Piñeros Gallego** – [LinkedIn](https://www.linkedin.com/in/cristianpineros/) – [GitHub](https://github.com/crispineros)

