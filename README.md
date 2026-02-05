# Catálogo Digital de Libros

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

