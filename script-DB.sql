-- =============================================================================
-- BikApp — Script de Creación de Schemas y Tablas
-- Base de Datos: PostgreSQL
-- Versión: 1.0 | Marzo 2026
-- =============================================================================
-- NOTA: Las FKs cross-schema se documentan como comentarios.
--       Solo se crean FKs reales entre tablas del mismo schema.
-- =============================================================================


-- =============================================================================
-- DROP — Limpieza previa (orden inverso por dependencias)
-- =============================================================================

-- suscripcion
DROP TABLE IF EXISTS suscripcion.usuario_plan           CASCADE;
DROP TABLE IF EXISTS suscripcion.plan_mensual           CASCADE;
DROP SCHEMA IF EXISTS suscripcion                       CASCADE;

-- billetera
DROP TABLE IF EXISTS billetera.transaccion_cobro        CASCADE;
DROP TABLE IF EXISTS billetera.transaccion_recarga      CASCADE;
DROP TABLE IF EXISTS billetera.transacciones_servicio   CASCADE;
DROP TABLE IF EXISTS billetera.transacciones            CASCADE;
DROP TABLE IF EXISTS billetera.cuentas                  CASCADE;
DROP TABLE IF EXISTS billetera.entidad_pago             CASCADE;
DROP TABLE IF EXISTS billetera.moneda                   CASCADE;
DROP SCHEMA IF EXISTS billetera                         CASCADE;

-- servicio
DROP TABLE IF EXISTS servicio.servicio                  CASCADE;
DROP TABLE IF EXISTS servicio.tarifa                    CASCADE;
DROP TABLE IF EXISTS servicio.tipo_servicio             CASCADE;
DROP SCHEMA IF EXISTS servicio                          CASCADE;

-- reserva
DROP TABLE IF EXISTS reserva.reserva_expirada           CASCADE;
DROP TABLE IF EXISTS reserva.reserva                    CASCADE;
DROP SCHEMA IF EXISTS reserva                           CASCADE;

-- bicicleta
DROP TABLE IF EXISTS bicicleta.bicicletas               CASCADE;
DROP TABLE IF EXISTS bicicleta.tipo_bicicleta           CASCADE;
DROP SCHEMA IF EXISTS bicicleta                         CASCADE;

-- estacion
DROP TABLE IF EXISTS estacion.estacion                  CASCADE;
DROP SCHEMA IF EXISTS estacion                          CASCADE;

-- usuario
DROP TABLE IF EXISTS usuario.fidelizacion               CASCADE;
DROP TABLE IF EXISTS usuario.usuarios                   CASCADE;
DROP SCHEMA IF EXISTS usuario                           CASCADE;

-- auth
DROP TABLE IF EXISTS auth.credenciales                  CASCADE;
DROP TABLE IF EXISTS auth.rol                           CASCADE;
DROP SCHEMA IF EXISTS auth                              CASCADE;



-- -----------------------------------------------------------------------------
-- SCHEMA: auth
-- Responsabilidad: Autenticación y seguridad de acceso
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS auth;

CREATE TABLE auth.rol (
    id          SMALLINT        PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre      VARCHAR(15)     NOT NULL
);

CREATE TABLE auth.credenciales (
    id              UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK cross-schema: usuario_id → usuario.usuarios.id
    usuario_id      UUID            NOT NULL,
    email           VARCHAR(255)    NOT NULL UNIQUE,
    password_hash   TEXT            NOT NULL,
    rol_id          SMALLINT        NOT NULL REFERENCES auth.rol(id),
    activo          BOOLEAN         NOT NULL DEFAULT TRUE,
    ultimo_login    TIMESTAMPTZ,
    creado_en       TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    actualizado_en  TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);


-- -----------------------------------------------------------------------------
-- SCHEMA: usuario
-- Responsabilidad: Perfil de usuario y programa de puntos
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS usuario;

CREATE TABLE usuario.usuarios (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre              VARCHAR(150)    NOT NULL,
    nickname            VARCHAR(80)     NOT NULL UNIQUE,
    tarjeta_credito     VARCHAR(20),
    telefono            VARCHAR(20),
    fecha_nacimiento    DATE,
    foto_perfil_url     TEXT,
    creado_en           TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    actualizado_en      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

CREATE TABLE usuario.fidelizacion (
    id              UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK intra-schema: usuario_id → usuario.usuarios.id
    usuario_id      UUID        NOT NULL UNIQUE REFERENCES usuario.usuarios(id),
    puntos          INTEGER     NOT NULL DEFAULT 0,
    puntos_canjeados INTEGER    NOT NULL DEFAULT 0,
    actualizado_en  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);


-- -----------------------------------------------------------------------------
-- SCHEMA: estacion
-- Responsabilidad: Estaciones físicas de retiro y devolución de bicicletas
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS estacion;

CREATE TABLE estacion.estacion (
    id                      UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre                  VARCHAR(150)    NOT NULL,
    direccion               TEXT            NOT NULL,
    latitud                 DECIMAL(10,8)   NOT NULL,
    longitud                DECIMAL(11,8)   NOT NULL,
    capacidad_total         INTEGER         NOT NULL,
    capacidad_disponible    INTEGER         NOT NULL,
    estado                  VARCHAR(20)     NOT NULL
                                CHECK (estado IN ('activa', 'inactiva', 'mantenimiento')),
    creado_en               TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    actualizado_en          TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);


-- -----------------------------------------------------------------------------
-- SCHEMA: bicicleta
-- Responsabilidad: Flota de bicicletas disponibles
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS bicicleta;

CREATE TABLE bicicleta.tipo_bicicleta (
    id      VARCHAR(35)     PRIMARY KEY,
    tipo    VARCHAR(20)     NOT NULL CHECK (tipo IN ('electrica', 'mecanica'))
);

CREATE TABLE bicicleta.bicicletas (
    -- El código físico es único e identificador, se usa como PK
    id                  VARCHAR(35)     PRIMARY KEY,
    tipo_id             VARCHAR(35)     NOT NULL REFERENCES bicicleta.tipo_bicicleta(id),
    -- FK cross-schema: estacion_id → estacion.estacion.id
    estacion_id         UUID,
    estado              VARCHAR(20)     NOT NULL
                            CHECK (estado IN ('disponible', 'reservada', 'en_uso', 'mantenimiento')),
    nivel_bateria       INTEGER         CHECK (nivel_bateria BETWEEN 0 AND 100),
    ultima_revision     DATE,
    creado_en           TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);


-- -----------------------------------------------------------------------------
-- SCHEMA: reserva
-- Responsabilidad: Gestión de reservas de bicicletas
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS reserva;

CREATE TABLE reserva.reserva (
    id                  UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK cross-schema: usuario_id  → usuario.usuarios.id
    -- FK cross-schema: bicicleta_id → bicicleta.bicicletas.id
    usuario_id          UUID        NOT NULL,
    bicicleta_id        VARCHAR(35) NOT NULL,
    estado              VARCHAR(15) NOT NULL
                            CHECK (estado IN ('activo', 'cancelado', 'uso', 'finalizado')),
    fecha_reserva       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    fecha_expiracion    TIMESTAMPTZ NOT NULL,
    creado_en           TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE reserva.reserva_expirada (
    id                  UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK intra-schema: reserva_id → reserva.reserva.id
    reserva_id          UUID        NOT NULL REFERENCES reserva.reserva(id),
    fecha_expiracion    TIMESTAMPTZ NOT NULL,
    motivo              TEXT
);


-- -----------------------------------------------------------------------------
-- SCHEMA: servicio
-- Responsabilidad: Servicios ejecutados (viajes en tiempo real)
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS servicio;

CREATE TABLE servicio.tipo_servicio (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre      VARCHAR(100)    NOT NULL,
    descripcion VARCHAR(255)    NOT NULL
);

CREATE TABLE servicio.tarifa (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK intra-schema: tipo_servicio_id → servicio.tipo_servicio.id
    tipo_servicio_id    UUID            NOT NULL REFERENCES servicio.tipo_servicio(id),
    precio_base         DECIMAL(10,2)   NOT NULL,
    precio_por_minuto   DECIMAL(10,2)   NOT NULL,
    fecha_inicio        DATE            NOT NULL,
    fecha_fin           DATE
);

CREATE TABLE servicio.servicio (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK cross-schema: reserva_id         → reserva.reserva.id
    -- FK cross-schema: estacion_inicio_id → estacion.estacion.id
    -- FK cross-schema: estacion_fin_id    → estacion.estacion.id
    reserva_id              UUID            NOT NULL UNIQUE,
    estacion_inicio_id      UUID            NOT NULL,
    estacion_fin_id         UUID,
    -- FK intra-schema: tipo_servicio_id → servicio.tipo_servicio.id
    tipo_servicio_id        UUID            NOT NULL REFERENCES servicio.tipo_servicio(id),
    fecha_hora_inicio       TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    fecha_hora_fin          TIMESTAMPTZ,
    millas_recorridas       DECIMAL(10,2),
    duracion_segundos       INTEGER,
    creado_en               TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);


-- -----------------------------------------------------------------------------
-- SCHEMA: billetera
-- Responsabilidad: Saldo y movimientos financieros
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS billetera;

CREATE TABLE billetera.moneda (
    id          VARCHAR(4)      PRIMARY KEY,
    descripcion VARCHAR(255)
);

CREATE TABLE billetera.entidad_pago (
    id      SMALLINT        PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre  VARCHAR(100)    NOT NULL UNIQUE
);

CREATE TABLE billetera.cuentas (
    id                      UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK cross-schema: usuario_id → usuario.usuarios.id
    usuario_id              UUID            NOT NULL UNIQUE,
    saldo                   DECIMAL(12,2)   NOT NULL DEFAULT 0,
    -- FK intra-schema: moneda → billetera.moneda.id
    moneda                  VARCHAR(4)      NOT NULL REFERENCES billetera.moneda(id),
    ultima_actualizacion    TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    creado_en               TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

CREATE TABLE billetera.transacciones (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK intra-schema: cuenta_id → billetera.cuentas.id
    cuenta_id   UUID            NOT NULL REFERENCES billetera.cuentas(id),
    monto       DECIMAL(12,2)   NOT NULL,
    -- FK intra-schema: moneda → billetera.moneda.id
    moneda      VARCHAR(4)      NOT NULL REFERENCES billetera.moneda(id),
    descripcion TEXT,
    fecha       TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

CREATE TABLE billetera.transacciones_servicio (
    id              UUID    PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK intra-schema: transaccion_id → billetera.transacciones.id
    transaccion_id  UUID    NOT NULL UNIQUE REFERENCES billetera.transacciones(id),
    -- FK cross-schema: servicio_id → servicio.servicio.id
    servicio_id     UUID    NOT NULL
);

CREATE TABLE billetera.transaccion_recarga (
    id              UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK intra-schema: transaccion_id → billetera.transacciones.id
    transaccion_id  UUID            NOT NULL UNIQUE REFERENCES billetera.transacciones(id),
    -- FK intra-schema: entidad_id → billetera.entidad_pago.id
    entidad_id      SMALLINT        NOT NULL REFERENCES billetera.entidad_pago(id),
    numero_cuenta   VARCHAR(30)     NOT NULL
);

CREATE TABLE billetera.transaccion_cobro (
    id              UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    -- FK intra-schema: transaccion_id → billetera.transacciones.id
    transaccion_id  UUID            NOT NULL UNIQUE REFERENCES billetera.transacciones(id),
    razon           VARCHAR(255)    NOT NULL
);


-- -----------------------------------------------------------------------------
-- SCHEMA: suscripcion
-- Responsabilidad: Planes y suscripciones de usuario
-- -----------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS suscripcion;

CREATE TABLE suscripcion.plan_mensual (
    id                      UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre                  VARCHAR(100)    NOT NULL,
    cantidad_viajes         INTEGER         NOT NULL,
    costo                   DECIMAL(10,2)   NOT NULL,
    duracion_max_viaje_min  INTEGER,
    activo                  BOOLEAN         NOT NULL DEFAULT TRUE,
    creado_en               TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

CREATE TABLE suscripcion.usuario_plan (
    -- FK cross-schema: usuario_id → usuario.usuarios.id
    usuario_id              UUID        NOT NULL,
    -- FK intra-schema: plan_id → suscripcion.plan_mensual.id
    plan_id                 UUID        NOT NULL REFERENCES suscripcion.plan_mensual(id),
    estado                  VARCHAR(15) NOT NULL
                                CHECK (estado IN ('activo', 'cancelado', 'expirado')),
    fecha_inicio            DATE        NOT NULL,
    fecha_fin               DATE        NOT NULL,
    viajes_usados           INTEGER     NOT NULL DEFAULT 0,
    renovacion_automatica   BOOLEAN     NOT NULL DEFAULT FALSE,
    creado_en               TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (usuario_id, plan_id)
);


-- =============================================================================
-- FIN DEL SCRIPT
-- =============================================================================