# Spring Cloud - Projeto de Microserviços

> Projeto desenvolvido com base no curso de **Spring Cloud da Rocketseat**

## 📋 Descrição

Este é um projeto de arquitetura de microserviços utilizando **Spring Cloud**, demonstrando conceitos fundamentais como descoberta de serviços com Eureka, configuração centralizada com Config Server e comunicação entre serviços via Feign.

---

## 🏗️ Arquitetura

O projeto é composto por 3 serviços principais e um Config Server centralizado:

### **1. Service Main (Servidor de Configuração e Descoberta)**
- **Porta**: `8888`
- **Responsabilidades**:
  - **Eureka Server**: Servidor de registro e descoberta de serviços
  - **Config Server**: Servidor centralizado de configurações conectado a um repositório Git
  - Ponto central de comunicação para todos os microserviços

### **2. Service Notification (Serviço de Notificações)**
- **Porta**: `8082`
- **Responsabilidades**:
  - Gerencia o envio e processamento de notificações
  - Comunica-se com o Service Main via Feign Client
  - Registra-se no Eureka para descoberta dinâmica
  - Obtém configurações do Config Server

### **3. Service One / Service Tasks (Serviço de Tarefas)**
- **Porta**: `8081`
- **Responsabilidades**:
  - Gerencia CRUD de tarefas com persistência em banco de dados
  - Utiliza H2 como banco de dados em memória
  - Comunica-se com o Service Notification via Feign Client
  - Registra-se no Eureka para descoberta dinâmica
  - Obtém configurações do Config Server

---

## 🛠️ Tecnologias e Dependências

### **Versões**
- **Java**: 21
- **Spring Boot**: 3.5.5 e 3.5.6
- **Spring Cloud**: 2025.0.0

### **Dependências Principais**

#### Service Main
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

#### Service Notification
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

#### Service Tasks
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

---

## 🐳 Executando com Docker

### **Pré-requisitos**
- Docker instalado e em funcionamento
- Docker Compose instalado

### **Como rodar o projeto**

1. **Clone o repositório**:
```bash
git clone https://github.com/seu-usuario/springCloud.git
cd springCloud
```

2. **Inicie os serviços com Docker Compose**:
```bash
docker-compose up --build
```

O comando acima irá:
- Construir as imagens Docker de cada serviço
- Iniciar os 3 serviços em containers separados
- Criar uma rede isolada chamada `service-network` para comunicação entre containers
- Verificar a saúde de cada serviço

### **Acessando os Serviços**

Após o comando `docker-compose up`, os serviços estarão disponíveis em:

- **Eureka Dashboard** (Service Main): http://localhost:8888/eureka/web
- **Service Notification**: http://localhost:8082
- **Service Tasks**: http://localhost:8081
  - **H2 Console** (banco de dados): http://localhost:8081/h2-console

### **Parando os serviços**

```bash
docker-compose down
```

Para remover volumes também:
```bash
docker-compose down -v
```

---

## 📦 Estrutura do Projeto

```
springCloud/
├── docker-compose.yml           # Configuração dos containers
├── config-server/               # Arquivos de configuração centralizados
│   ├── service-notification.properties
│   └── service-tasks.properties
├── service.main/                # Service de Configuração e Eureka
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
├── service.notification/        # Service de Notificações
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
└── service.one/                 # Service de Tarefas (Tasks)
    ├── Dockerfile
    ├── pom.xml
    └── src/
```

---

## 🔗 Fluxo de Comunicação

```
┌─────────────────────────────────────────────┐
│         Service Main (8888)                 │
│     - Eureka Server                         │
│     - Config Server                         │
│     - Discovery Center                      │
└─────────────┬───────────────────────────────┘
              │
       ┌──────┴──────┐
       │             │
┌──────▼────────┐   ┌──────▼──────────┐
│ Service       │   │ Service         │
│ Notification  │◄──┤ Tasks (8081)    │
│ (8082)        │   │ - H2 Database   │
│ - Feign       │   │ - JPA           │
│   Client      │   │ - Feign Client  │
└───────────────┘   └─────────────────┘
```

---

## 🎯 Conceitos Demonstrados

1. **Eureka Server & Client**: Descoberta dinâmica de serviços
2. **Config Server**: Configuração centralizada a partir de um repositório Git
3. **Feign Client**: Comunicação declarativa entre microserviços
4. **Spring Cloud**: Integração completa de padrões de microserviços
5. **Docker & Docker Compose**: Orquestração de containers

---

## 📝 Notas Adicionais

- O arquivo `docker-compose.yml` define a ordem de inicialização dos serviços usando health checks
- O Service Main aguarda estar saudável antes de iniciar os outros serviços
- Todos os serviços comunicam-se através da rede Docker `service-network`
- As configurações centralizadas vêm de um repositório Git externo (consultado pelo Config Server)

---

## 📚 Referências

- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Netflix Eureka](https://github.com/Netflix/eureka)
- [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
- [OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [Rocketseat Courses](https://www.rocketseat.com.br/)

---

**Autor**: Lucas  
**Data**: 2026  
**Licença**: MIT

