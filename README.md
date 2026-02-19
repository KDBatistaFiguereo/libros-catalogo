# Catálogo Digital de Libros

[English Version](#english-version)

Aplicación web para la búsqueda, catalogación y gestión de libros y autores, con integración con la API del Proyecto Gutenberg para acceder a libros de dominio público.

## Características Principales

- **Búsqueda de Libros**: Busca libros por título mediante la API del Proyecto Gutenberg
- **Gestión de Libros**: Guarda y cataloga libros localmente
- **Gestión de Autores**: Visualiza y gestiona información de autores
- **Historial de Búsqueda**: Registra sesiones de búsqueda de libros y autores
- **Soporte Multiidioma**: Capacidades de filtrado por idioma
- **Interfaz Web**: Interfaz con plantillas Thymeleaf

## Herramientas

- **Backend Framework**: Spring Boot 3.5.10
- **Versión Java**: Java 21
- **Arquitectura**: Arquitectura Hexagonal
- **Web Framework**: Spring MVC con Thymeleaf
- **Base de Datos**: PostgreSQL (dev), H2 (testing)
- **Build Tool**: Maven
- **Librerías Principales**:
  - Lombok (reducción de código boilerplate)
  - MapStruct 1.6.3 (mapeo de objetos)
  - Spring Validation
  - Jackson para procesamiento JSON

## Estructura del Proyecto

```
libros-catalogo/
├── application/
│   ├── domain/           # Entidades y servicios del dominio
│   │   ├── entities/     # Book, Author
│   │   └── services/     # FindBooksByTitleService, SaveBookService
│   ├── port/             # Puertos de entrada y salida
│   │   ├── input/        # Comandos y consultas
│   │   └── output/       # Interfaces para integraciones externas
│   └── adapter/          # Adaptadores de implementación
│       ├── web/          # Controladores
│       ├── persistence/  # Repositorios JPA
│       └── api/          # Clientes de API externas
├── common/
│   └── session/          # Aspecto para iterceptar la busqueda de libros y Agregar historial
├── docker-compose.yml    # Configuración de Docker
└── pom.xml              # Configuración de Maven
```

## Instalación y Configuración

### Prerrequisitos

- Java 21
- Maven 3.6+
- Docker y Docker Compose

### 1. Configuración de Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto basado en `.example.env`:

```bash
# Copiar archivo de ejemplo
cp .example.env .env
```

Edita `.env` con tus credenciales:

```env
POSTGRES_PASSWORD=tu_contraseña_postgres
POSTGRES_USER=tu_usuario_postgres
POSTGRES_DB=libros_catalogo
PORT=5432
DB_HOST=localhost
```

### 2. Iniciar Base de Datos

```bash
# Iniciar PostgreSQL en Docker
docker-compose up -d

# Verificar que el contenedor esté corriendo
docker-compose ps
```

### 3. Ejecutar la Aplicación

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar pruebas (opcional)
mvn test

# Iniciar la aplicación
mvn spring-boot:run
```

### 4. Acceder a la Aplicación

Abre tu navegador en: `http://localhost:8080`

## Rutas de la Aplicación

- `/` - Página principal con menú de navegación
- `/libros/busqueda` - Interfaz de búsqueda de libros
- `/libros/guardar` - Funcionalidad para guardar libros
- `/libros/historial` - Historial de búsquedas
- `/libros` - Libros guardados
- `/libros/autores` - Historial de búsqueda de autores
- `/autores` - Autores guardados

## Pruebas

El proyecto tiene **cobertura de pruebas (todavia no completa)**:

```bash
# Ejecutar todas las pruebas
mvn test
```

### Tipos de Pruebas

- **Pruebas Unitarias**: Entidades del dominio y servicios
- **Pruebas de Integración**: Controladores, repositorios y adaptadores API
- **MockWebServer**: Para probar integraciones con API externas

## Integraciones Externas

### API del Proyecto Gutenberg

- **URL Base**: `https://gutendex.com/books/`
- **Funcionalidades**:
  - Búsqueda de libros por título
  - Acceso a metadatos de libros y autores
  - Transformación de respuestas API a modelos de dominio

## Arquitectura

La aplicación sigue el patrón **Arquitectura Hexagonal**:

### Capa de Dominio (`application/domain`)

- **Entidades**: `Book`, `Author`
- **Servicios**: Casos de uso del dominio
- **Puertos**: Interfaces para comunicación externa

### Capa de Aplicación (`application/port`)

- **Puertos de Entrada**: Comandos y consultas
- **Puertos de Salida**: Interfaces para integraciones

### Capa de Adaptadores (`application/adapter`)

- **Web**: Controladores y plantillas Thymeleaf
- **Persistencia**: Adaptadores JPA y repositorios
- **API**: Clientes para servicios externos

## Licencia

Este proyecto es para fines de aprendizaje.

---

# English Version

Digital Book Catalog

Web application for searching, cataloging, and managing books and authors, with integration with the Gutenberg Project API to access public domain books.

## Main Features

- **Book Search**: Search books by title via the Gutenberg Project API
- **Book Management**: Save and catalog books locally
- **Author Management**: View and manage author information
- **Search History**: Records book and author search sessions
- **Multi-language Support**: Language filtering capabilities
- **Web Interface**: Interface with Thymeleaf templates

## Tools

- **Backend Framework**: Spring Boot 3.5.10
- **Java Version**: Java 21
- **Architecture**: Hexagonal Architecture
- **Web Framework**: Spring MVC with Thymeleaf
- **Database**: PostgreSQL (dev), H2 (testing)
- **Build Tool**: Maven
- **Main Libraries**:
  - Lombok (boilerplate code reduction)
  - MapStruct 1.6.3 (object mapping)
  - Spring Validation
  - Jackson for JSON processing

## Project Structure

```
libros-catalogo/
├── application/
│   ├── domain/           # Domain entities and services
│   │   ├── entities/     # Book, Author
│   │   └── services/     # FindBooksByTitleService, SaveBookService
│   ├── port/             # Input and output ports
│   │   ├── input/        # Commands and queries
│   │   └── output/       # Interfaces for external integrations
│   └── adapter/          # Implementation adapters
│       ├── web/          # Controllers
│       ├── persistence/  # JPA repositories
│       └── api/          # External API clients
├── common/
│   └── session/          # Aspect to intercept book searches and add history
├── docker-compose.yml    # Docker configuration
└── pom.xml              # Maven configuration
```

## Installation and Configuration

### Prerequisites

- Java 21
- Maven 3.6+
- Docker and Docker Compose

### 1. Environment Variables Configuration

Create a `.env` file in the project root based on `.example.env`:

```bash
# Copy example file
cp .example.env .env
```

Edit `.env` with your credentials:

```env
POSTGRES_PASSWORD=your_postgres_password
POSTGRES_USER=your_postgres_user
POSTGRES_DB=libros_catalogo
PORT=5432
DB_HOST=localhost
```

### 2. Start Database

```bash
# Start PostgreSQL in Docker
docker-compose up -d

# Verify container is running
docker-compose ps
```

### 3. Run the Application

```bash
# Compile the project
mvn clean compile

# Run tests (optional)
mvn test

# Start the application
mvn spring-boot:run
```

### 4. Access the Application

Open your browser at: `http://localhost:8080`

## Application Routes

- `/` - Main page with navigation menu
- `/libros/busqueda` - Book search interface
- `/libros/guardar` - Book saving functionality
- `/libros/historial` - Search history
- `/libros` - Saved books
- `/libros/autores` - Author search history
- `/autores` - Saved authors

## Tests

The project has **test coverage (not yet complete)**:

```bash
# Run all tests
mvn test
```

### Test Types

- **Unit Tests**: Domain entities and services
- **Integration Tests**: Controllers, repositories, and API adapters
- **MockWebServer**: For testing external API integrations

## External Integrations

### Gutenberg Project API

- **Base URL**: `https://gutendex.com/books/`
- **Functionalities**:
  - Search books by title
  - Access book and author metadata
  - Transform API responses to domain models

## Architecture

The application follows the **Hexagonal Architecture** pattern:

### Domain Layer (`application/domain`)

- **Entities**: `Book`, `Author`
- **Services**: Domain use cases
- **Ports**: Interfaces for external communication

### Application Layer (`application/port`)

- **Input Ports**: Commands and queries
- **Output Ports**: Interfaces for integrations

### Adapter Layer (`application/adapter`)

- **Web**: Controllers and Thymeleaf templates
- **Persistence**: JPA adapters and repositories
- **API**: Clients for external services

## License

This project is for educational purposes.

