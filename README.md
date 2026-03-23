# BikApp - Plataforma de Bicicletas Compartidas

Sistema de microservicios escalable para la gestión de una plataforma de bicicletas compartidas. Implementa una arquitectura distribuida con autenticación, gestión de usuarios, reservas, servicios de viajes, billeteras y suscripciones.

## Arquitectura

**BikApp** utiliza una arquitectura de microservicios con los siguientes componentes:

### Microservicios

| Servicio | Puerto | Responsabilidad |
|----------|--------|-----------------|
| **auth** | 9090 (gRPC) | Autenticación, JWT y control de acceso |
| **usuario** | 9090 (gRPC) | Perfil de usuario y programa de fidelización |
| **estacion** | - | Gestión de estaciones de retiro/devolución |
| **bicicleta** | - | Inventario y estado de bicicletas |
| **reserva** | - | Reservas de bicicletas |
| **servicio** | - | Servicios ejecutados (viajes en tiempo real) |
| **billetera** | - | Saldo y transacciones financieras |
| **suscripcion** | - | Planes y suscripciones mensuales |
| **email** | - | Envío de correos (consumer RabbitMQ) |
| **informe** | - | Generación de reportes y análisis |

### Infraestructura

- **RabbitMQ** - Message broker para comunicación asíncrona
- **PostgreSQL** - Base de datos compartida con múltiples schemas
- **Nginx** - API Gateway
- **Docker Compose** - Orquestación de contenedores
- **Observabilidad** (comentada) - Loki + Promtail + Grafana

## Requisitos Previos

- Docker y Docker Compose
- Java 17+ (para desarrollo local)
- Gradle
- PostgreSQL 14+ (si se ejecuta sin Docker)

## Instalación

### 1. Clonar el repositorio

```bash
git clone <repository-url>
cd HELIO-PRACTICA-PENAGOS
```

### 2. Configurar variables de entorno

Edita el archivo `.env` con tus valores:

```properties
# General
COMPOSE_PROJECT_NAME=bike
TZ=America/Bogota

# RabbitMQ
RABBITMQ_DEFAULT_USER=admin
RABBITMQ_DEFAULT_PASS=admin

# Base de Datos
DB_HOST=tu_host_postgres
DB_PORT=5432
DB_NAME=neondb
DB_USER=tu_usuario
DB_PASS=tu_contraseña

# Schemas (crear previamente en la BD)
AUTH_DB_SCHEMA=auth
USUARIO_DB_SCHEMA=usuario
ESTACION_DB_SCHEMA=estacion
# ... (ver .env para todos)

# JWT
JWT_SECRET=tu_secreto_largo_y_seguro
JWT_EXPIRATION=900000
JWT_REFRESH_EXPIRATION=604800000

# SMTP (Email)
SMTP_HOST=smtp.tudominio.com
SMTP_PORT=587
SMTP_USER=tu_email@tudominio.com
SMTP_PASS=tu_contraseña
```

### 3. Crear schemas en la base de datos

```bash
psql -h $DB_HOST -U $DB_USER -d $DB_NAME < script-DB.sql
```

### 4. Construir los microservicios

```bash
./build.sh
```

Este script construye todos los servicios con Gradle.

### 5. Iniciar los servicios con Docker Compose

```bash
docker compose up -d
```

Verifica el estado:

```bash
docker compose ps
```

## 🗄️ Base de Datos

La aplicación utiliza una **base de datos PostgreSQL compartida** con múltiples **schemas** para separación lógica:

- **auth** - Credenciales y roles
- **usuario** - Perfil y fidelización
- **estacion** - Estaciones
- **bicicleta** - Bicicletas
- **reserva** - Reservas
- **servicio** - Servicios/Viajes
- **billetera** - Transacciones
- **suscripcion** - Planes

### Crear schemas iniciales

```sql
CREATE SCHEMA IF NOT EXISTS auth;
CREATE SCHEMA IF NOT EXISTS usuario;
CREATE SCHEMA IF NOT EXISTS estacion;
CREATE SCHEMA IF NOT EXISTS bicicleta;
CREATE SCHEMA IF NOT EXISTS reserva;
CREATE SCHEMA IF NOT EXISTS servicio;
CREATE SCHEMA IF NOT EXISTS billetera;
CREATE SCHEMA IF NOT EXISTS suscripcion;
```

## 🔌 Comunicación entre Servicios

### Asíncrona (RabbitMQ)

Los servicios se comunican a través de colas de mensajes:
- `email` consume eventos para enviar correos
- `informe` consume eventos para generar reportes
- `billetera` consume eventos de servicios para actualizar transacciones

### Síncrona (gRPC)

- **auth** y **usuario** exponen puertos gRPC (9090) para comunicación sincrónica

## API Gateway (Nginx)

Nginx actúa como proxy reverso en el puerto **80**:

```
GET /api/v1/auth/* → auth:8080/api/v1/auth/
```

## Estructura del Proyecto

```
HELIO-PRACTICA-PENAGOS/
├── auth/                    # Microservicio de autenticación
├── usuario/                 # Microservicio de usuario
├── estacion/                # Microservicio de estaciones
├── bicicleta/               # Microservicio de bicicletas
├── reserva/                 # Microservicio de reservas
├── servicio/                # Microservicio de servicios/viajes
├── billetera/               # Microservicio de billetera
├── suscripcion/             # Microservicio de suscripciones
├── email/                   # Microservicio de email
├── informe/                 # Microservicio de reportes
├── docker-compose.yml       # Configuración completa
├── docker-compose.p1.yml    # Configuración fase 1
├── nginx.conf               # Configuración del API Gateway
├── script-DB.sql            # SQL de creación de schemas y tablas
├── build.sh                 # Script para construir servicios
├── .env                     # Variables de entorno
└── README.md                # Este archivo
```

## Desarrollo Local

### Ejecutar un servicio individualmente

```bash
cd auth
./gradlew bootRun
```

### Ver logs

```bash
docker compose logs -f auth
```

### Detener los servicios

```bash
docker compose down
```

### Limpiar volúmenes (datos)

```bash
docker compose down -v
```

## Seguridad

- **JWT** para autenticación stateless
- **Roles** por usuario (definidos en `auth.rol`)
- **HTTPS/TLS** debe configurarse en producción
- Variables sensibles en `.env` (no versionar)

## Monitoreo (Opcional)

Los servicios de observabilidad están comentados en `docker-compose.yml`:
- **Loki** - Agregación de logs
- **Promtail** - Recolector de logs
- **Grafana** - Visualización (http://localhost:3000)

Para habilitar:

1. Descomenta las secciones de `loki`, `promtail` y `grafana` en `docker-compose.yml`
2. Ejecuta: `docker compose up -d`

## Testing

```bash
# Todos los servicios
./build.sh

# Un servicio específico
cd auth && ./gradlew test
```

## Variables de Entorno

| Variable | Descripción | Ejemplo |
|----------|-------------|---------|
| `DB_HOST` | Host de PostgreSQL | `localhost` |
| `DB_PORT` | Puerto de PostgreSQL | `5432` |
| `DB_NAME` | Nombre de BD | `neondb` |
| `JWT_SECRET` | Secreto para firmar JWT | `your-secret-key` |
| `RABBITMQ_DEFAULT_USER` | Usuario RabbitMQ | `admin` |
| `RABBITMQ_DEFAULT_PASS` | Contraseña RabbitMQ | `admin` |

## Troubleshooting

### RabbitMQ no inicia
```bash
docker compose logs rabbitmq
# Verifica que el puerto 5672 esté disponible
```

### Error de conexión a BD
```bash
# Verifica credenciales en .env
# Asegúrate que los schemas existan:
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\dn"
```

### Servicio no responde
```bash
# Revisa logs
docker compose logs <servicio>

# Reinicia el servicio
docker compose restart <servicio>
```

## Documentación Adicional

- **PostgreSQL**: https://www.postgresql.org/docs/
- **RabbitMQ**: https://www.rabbitmq.com/documentation.html
- **Docker Compose**: https://docs.docker.com/compose/
- **gRPC**: https://grpc.io/docs/

## Contribuciones

Para contribuir:

1. Crea una rama (`git checkout -b feature/AmazingFeature`)
2. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
3. Push a la rama (`git push origin feature/AmazingFeature`)
4. Abre un Pull Request

## Licencia

Este proyecto es privado. Consulta con el propietario para más información.

## Autor

Desarrollado como práctica en **HELIO-PRACTICA-PENAGOS**.

---

**Última actualización**: Marzo 2026