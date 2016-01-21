# ATON
Administrador de laboratorios de computadoras.

## Advertencia
Este proyecto todavía se encuentra en fase de desarrollo.

## Como instalar
### 1. Descargar una copia de este proyecto
```bash
git clone https://github.com/ProjectAton/Aton.git
```

### 2. Instalar postgresql. Ir al paso 4 si ya se tiene configurado PostgreSQL.

Instalar la base de datos PostgreSQL, usando el gestor de paquetes por defecto o con cualquier otro método.
 
**Instalación en Ubuntu - Debian**

```bash
sudo apt-get install postgresql
```

**CentOS**

```bash
sudo yum install postgresql-server
```

### 3. Asignar un password para acceder a PostgreSQL:

**Ingresar al shell de PostgreSQL:**

```bash
sudo -u postgres psql
```

**Ejecutar para cambiar el password**

```bash
\password postgres
```

### 3. Importar la base de datos de database.sql
```bash
sudo -u postgres psql < database.sql
```

### 4. Modificar los archivos de configuración

#### 4.1. src/main/resources/aplicacion.properties
 

#### 4.2. src/main/resources/hibernate.properties
