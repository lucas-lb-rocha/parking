# Parking-api

Essa API é um CRUD para um sistema de estacionamentos: permite realizar crud de usuários, clientes, e carros em vagas.

# Pré-requisistos
- Java 17
- Mysql

# Criando a estrutura do banco

no terminal para acessar o mysql
```bash
mysql -u root -p
```

Ao entrar, crie o banco:
```sql
create database park;
use park;
```

Crie as tabelas
Tabela Usuarios
```sql
CREATE TABLE usuarios (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(200) NOT NULL,
role ENUM('ROLE_ADMIN', 'ROLE_CLIENTE') NOT NULL,
data_criacao DATETIME,
data_modificacao DATETIME,
criado_por VARCHAR(255),
modificado_por VARCHAR(255)
);

```

Tabela Clientes
```sql
CREATE TABLE clientes (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
cpf VARCHAR(11) NOT NULL UNIQUE,
id_usuario BIGINT NOT NULL,
data_criacao DATETIME,
data_modificacao DATETIME,
criado_por VARCHAR(255),
modificado_por VARCHAR(255),
FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
```

Tabela Vagas
```sql
CREATE TABLE vagas (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
codigo VARCHAR(4) NOT NULL UNIQUE,
status ENUM('LIVRE', 'OCUPADA') NOT NULL,
data_criacao DATETIME,
data_modificacao DATETIME,
criado_por VARCHAR(255),
modificado_por VARCHAR(255)
);
```

Tabela clientes_tem_vagas
```sql
CREATE TABLE clientes_tem_vagas (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
numero_recibo VARCHAR(15) NOT NULL UNIQUE,
placa VARCHAR(8) NOT NULL,
marca VARCHAR(45) NOT NULL,
modelo VARCHAR(45) NOT NULL,
cor VARCHAR(45) NOT NULL,
data_entrada DATETIME NOT NULL,
data_saida DATETIME,
valor DECIMAL(7, 2),
desconto DECIMAL(7, 2),
id_cliente BIGINT NOT NULL,
id_vaga BIGINT NOT NULL,
data_criacao DATETIME,
data_modificacao DATETIME,
criado_por VARCHAR(255),
modificado_por VARCHAR(255),
FOREIGN KEY (id_cliente) REFERENCES clientes(id),
FOREIGN KEY (id_vaga) REFERENCES vagas(id)
);
```

# Swagger
http://localhost:8080/park/swagger-ui/index.html

# Fonte de estudo
Esse projeto é resultado do curso https://www.udemy.com/course/api-rest-e-spring-boot-aprenda-do-zero-e-na-pratica

Link para o vídeo com a demonstração da aplicação: https://youtu.be/nNUduY_wVbM