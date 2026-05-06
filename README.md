# TenTech Edu Backend

Backend-платформа для учебного сайта школы TenTech Edu.

Проект разрабатывается с подходом, максимально приближенным к production-разработке:

* микросервисная архитектура
* Java 21
* Spring Boot
* PostgreSQL
* Docker
* CI/CD
* документация
* работа через Jira
* код через Pull Request
* постепенная подготовка к Kubernetes

---

# 1. Цель проекта

Цель проекта — разработать backend-систему для учебного сайта школы.

На сайте студент должен иметь личный кабинет, где он сможет:

* войти по логину и паролю
* посмотреть свои учебные материалы
* видеть этапы обучения
* открывать уроки
* работать с файлами и материалами
* видеть свой прогресс обучения

Администратор должен иметь возможность:

* создавать студентов
* управлять пользователями
* создавать курсы
* создавать этапы обучения
* создавать уроки
* добавлять учебные материалы
* смотреть прогресс студентов

Важно:

Студенты не регистрируются самостоятельно. Аккаунты студентов создаёт только администратор.

---

# 2. Основной стек технологий

Проект использует следующий стек:

* Java 21
* Spring Boot
* Spring Web MVC
* Spring Security
* Spring Data JPA
* PostgreSQL
* Flyway
* Maven
* Docker
* Docker Compose
* OpenAPI / Swagger
* Lombok
* MapStruct
* JUnit 5
* Testcontainers
* GitHub Actions / GitLab CI в будущем
* Kubernetes в будущем

---

# 3. Архитектурный подход

Проект строится как monorepo с набором микросервисов.

Monorepo означает, что все backend-сервисы находятся в одном Git-репозитории, но каждый сервис является отдельным Maven-модулем и отдельным Spring Boot-приложением.

Общая схема:

```text
Client / Frontend
       |
       v
 API Gateway
       |
       +----------------------+
       |                      |
       v                      v
 Auth Service            User Service
       |
       +----------------------+
       |                      |
       v                      v
 Content Service       Progress Service
       |
       +----------------------+
       |
       v
 File Storage Service

 Notification Service
```

---

# 4. Структура репозитория

```text
tentech-edu-backend/
├── api-gateway/
├── auth-service/
├── user-service/
├── content-service/
├── progress-service/
├── file-storage-service/
├── notification-service/
├── docs/
├── infra/
├── .mvn/
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
для каждой задачи создаем свою ветку!!!
---

# 5. Назначение директорий

## api-gateway

Единая точка входа во все backend-сервисы.

Отвечает за:

* маршрутизацию запросов
* проверку JWT токена в будущем
* CORS
* единый вход для frontend-приложения
* проксирование запросов к нужным микросервисам

---

## auth-service

Микросервис авторизации и аутентификации.

Отвечает за:

* login/password authentication
* генерацию JWT access token
* refresh token
* password hashing
* роли пользователей
* Spring Security
* проверку доступа

Основные роли:

```text
ADMIN
STUDENT
```

---

## user-service

Микросервис управления пользователями.

Отвечает за:

* создание пользователей
* создание студентов
* хранение профилей студентов
* хранение профилей администраторов
* получение текущего профиля пользователя
* получение списка студентов
* обновление профиля студента

---

## content-service

Микросервис учебного контента.

Отвечает за:

* курсы
* этапы обучения
* уроки
* учебные материалы
* структуру обучения
* управление контентом администратором
* просмотр контента студентами

---

## progress-service

Микросервис прогресса обучения.

Отвечает за:

* прогресс студента
* отметку урока как пройденного
* процент прохождения курса
* историю прохождения уроков
* отображение статуса обучения

---

## file-storage-service

Микросервис хранения файлов.

Отвечает за:

* загрузку файлов
* хранение файлов
* удаление файлов
* выдачу ссылок на файлы
* связь файлов с учебными материалами

На первом этапе можно использовать локальное хранилище.

Позже можно подключить:

```text
MinIO
S3-compatible storage
```

---

## notification-service

Микросервис уведомлений.

Отвечает за:

* email-уведомления
* уведомление студента о создании аккаунта
* уведомление о новых материалах
* уведомление о смене пароля
* подготовку к асинхронным событиям

На первом этапе может быть простым REST-сервисом.

Позже можно добавить:

```text
Kafka
RabbitMQ
```

---

## docs

Папка с документацией проекта.

Примеры файлов:

```text
docs/
├── architecture.md
├── api.md
├── jira.md
├── git-flow.md
└── deployment.md
```

---

## infra

Папка с инфраструктурой проекта.

Примеры файлов:

```text
infra/
├── docker-compose/
├── postgres/
├── k8s/
└── monitoring/
```

На первом этапе здесь будет Docker Compose.

Позже:

* Kubernetes manifests
* Helm charts
* Prometheus
* Grafana
* logging configuration

---

# 6. Maven modules

Корневой `pom.xml` является parent-проектом.

В нём должны быть указаны все backend-модули:

```xml
<modules>
    <module>api-gateway</module>
    <module>auth-service</module>
    <module>user-service</module>
    <module>content-service</module>
    <module>progress-service</module>
    <module>file-storage-service</module>
    <module>notification-service</module>
</modules>
```

Корневой проект не содержит Java-кода.

В корне не должно быть папки:

```text
src/
```

Код должен находиться только внутри конкретных сервисов.

---

# 7. Базовые порты сервисов

```text
api-gateway              8080
auth-service             8081
user-service             8082
content-service          8083
progress-service         8084
file-storage-service     8085
notification-service     8086
```

PostgreSQL:

```text
5432
```

Swagger UI для каждого сервиса:

```text
http://localhost:<port>/swagger-ui/index.html
```

Actuator health-check:

```text
http://localhost:<port>/actuator/health
```

---

# 8. Базы данных

Нужно использовать отдельную базу данных PostgreSQL для каждого сервиса.

```text
auth_db
user_db
content_db
progress_db
file_storage_db
notification_db
```

Каждый сервис должен владеть только своей базой.

Важно:

Один сервис не должен напрямую читать таблицы другого сервиса.

Правильно:

```text
user-service -> user_db
content-service -> content_db
progress-service -> progress_db
```

Неправильно:

```text
progress-service -> user_db
```

Если одному сервису нужны данные другого сервиса, он должен получить их через API или событие.

---

# 9. Основные бизнес-сущности

## User

```text
id
email
passwordHash
role
status
createdAt
updatedAt
```

---

## StudentProfile

```text
id
userId
firstName
lastName
phone
groupName
status
createdAt
updatedAt
```

---

## Course

```text
id
title
description
status
createdAt
updatedAt
```

---

## Stage

```text
id
courseId
title
description
position
createdAt
updatedAt
```

---

## Lesson

```text
id
stageId
title
description
content
position
createdAt
updatedAt
```

---

## Material

```text
id
lessonId
title
type
fileUrl
createdAt
updatedAt
```

---

## Progress

```text
id
studentId
courseId
lessonId
status
completedAt
createdAt
updatedAt
```

---

# 10. Роли пользователей

## ADMIN

Администратор может:

* создавать студентов
* смотреть список студентов
* обновлять данные студентов
* создавать курсы
* создавать этапы обучения
* создавать уроки
* добавлять материалы
* смотреть прогресс студентов

---

## STUDENT

Студент может:

* войти в систему
* открыть личный кабинет
* посмотреть свой профиль
* посмотреть доступные курсы
* открыть этапы обучения
* открыть уроки
* открыть материалы
* отметить урок как пройденный
* посмотреть свой прогресс

---

# 11. MVP проекта

MVP — это минимальная рабочая версия проекта.

В рамках MVP нужно реализовать:

## Для администратора

```text
1. Создание студента
2. Получение списка студентов
3. Создание курса
4. Создание этапа обучения
5. Создание урока
6. Добавление материала к уроку
7. Просмотр прогресса студента
```

## Для студента

```text
1. Login
2. Просмотр личного профиля
3. Просмотр доступных курсов
4. Просмотр этапов курса
5. Просмотр уроков
6. Просмотр материалов
7. Отметка урока как пройденного
8. Просмотр прогресса
```

---

# 12. Jira structure

В Jira используется простая структура:

```text
Epic -> Task
```

На старте Story можно не использовать, чтобы не усложнять процесс для студентов.

## Epic

Epic = крупный модуль или микросервис.

Примеры Epic:

```text
Project Setup
API Gateway
Auth Service
User Service
Content Service
Progress Service
File Storage Service
Notification Service
DevOps
Documentation
```

## Task

Task = конкретная работа внутри Epic.

Пример:

```text
Epic: User Service

Tasks:
- Create user entity
- Create student profile entity
- Implement create student endpoint
- Implement get current user profile
- Implement get students list endpoint
- Implement update student profile endpoint
```

---

# 13. Первый этап разработки

Первый этап называется:

```text
Project Foundation
```

Цель этапа — подготовить основу проекта.

Задачи:

```text
1. Создать monorepo
2. Настроить parent pom.xml
3. Создать Maven modules для всех сервисов
4. Настроить Java 21
5. Подключить базовые зависимости
6. Создать README.md
7. Создать docs/architecture.md
8. Создать infra/docker-compose
9. Поднять PostgreSQL
10. Создать application.yml для каждого сервиса
11. Назначить порты сервисам
12. Добавить health-check endpoints
13. Проверить запуск каждого сервиса
```

---

# 14. Рекомендованный порядок реализации

## Шаг 1. Project Setup

```text
- parent pom.xml
- modules
- базовая структура
- README.md
- docs
- infra
```

---

## Шаг 2. User Service

```text
- User entity
- StudentProfile entity
- migrations
- repositories
- services
- controllers
- DTO
- validation
```

---

## Шаг 3. Auth Service

```text
- login
- password hashing
- JWT
- refresh token
- roles
- Spring Security
```

---

## Шаг 4. Content Service

```text
- Course
- Stage
- Lesson
- Material
- CRUD endpoints
```

---

## Шаг 5. Progress Service

```text
- mark lesson completed
- get student progress
- calculate completion percentage
```

---

## Шаг 6. File Storage Service

```text
- upload file
- download file
- delete file
- link file to material
```

---

## Шаг 7. API Gateway

```text
- routes
- CORS
- JWT validation
- service routing
```

---

## Шаг 8. Notification Service

```text
- send email
- notify student after account creation
- notify about new materials
```

---

## Шаг 9. DevOps

```text
- Dockerfile
- docker-compose
- CI pipeline
- test pipeline
- build pipeline
```

---

# 15. Git workflow

Для разработки используется feature branch workflow.

Нельзя писать напрямую в main/master.

Каждая задача выполняется в отдельной ветке.

Формат ветки:

```text
feature/TE-1-create-user-entity
feature/TE-2-create-student-profile
feature/TE-3-create-student-endpoint
bugfix/TE-10-fix-user-validation
chore/TE-20-update-readme
```

Пример:

```bash
git checkout -b feature/TE-1-create-user-entity
```

---

# 16. Commit messages

Формат коммита:

```text
<jira-task-key> <short description>
```

Примеры:

```text
TE-1 Create user entity
TE-2 Add student profile entity
TE-3 Implement create student endpoint
TE-4 Add user service validation
```

---

# 17. Pull Request rules

Каждая задача должна завершаться Pull Request.

В Pull Request нужно указать:

```text
1. Что сделано
2. Какая задача из Jira
3. Как проверить
4. Есть ли миграции
5. Есть ли тесты
6. Какие endpoints добавлены
```

---

# 18. Definition of Done

Задача считается готовой, если:

```text
1. Код написан
2. Код отформатирован
3. Нет лишнего мусора
4. Добавлены необходимые DTO
5. Добавлена валидация
6. Добавлены тесты
7. Добавлена миграция, если нужна БД
8. Swagger отображает endpoint, если endpoint был добавлен
9. Сервис запускается локально
10. Maven build проходит успешно
11. Pull Request создан
12. Pull Request прошёл review
13. Код влит в основную ветку
```

---

# 19. Code style

Общие правила:

```text
- Java 21
- package names только lowercase
- классы называем понятно
- DTO не смешиваем с Entity
- Controller не содержит бизнес-логику
- Service содержит бизнес-логику
- Repository работает только с базой
- Mapper отвечает за преобразование DTO <-> Entity
- Exception обрабатываются через GlobalExceptionHandler
```

---

# 20. Рекомендуемая структура внутри сервиса

Пример для `user-service`:

```text
user-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ru/
│   │   │       └── tentech/
│   │   │           └── userservice/
│   │   │               ├── UserServiceApplication.java
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               ├── repository/
│   │   │               ├── entity/
│   │   │               ├── dto/
│   │   │               ├── mapper/
│   │   │               ├── exception/
│   │   │               └── config/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/
│   │           └── migration/
│   └── test/
│       └── java/
└── pom.xml
```

---

# 21. Package naming

Используем единый стиль пакетов:

```text
ru.tentech.apigateway
ru.tentech.authservice
ru.tentech.userservice
ru.tentech.contentservice
ru.tentech.progressservice
ru.tentech.filestorageservice
ru.tentech.notificationservice
```

---

# 22. Controller layer

Controller отвечает только за HTTP.

Controller должен:

```text
- принимать request
- валидировать DTO
- вызывать service
- возвращать response
```

Controller не должен:

```text
- писать бизнес-логику
- работать напрямую с repository
- собирать сложные данные вручную
```

---

# 23. Service layer

Service содержит бизнес-логику.

Service отвечает за:

```text
- правила системы
- проверки
- работу с несколькими repository
- вызов mapper
- генерацию исключений
```

---

# 24. Repository layer

Repository отвечает только за доступ к базе данных.

Используем Spring Data JPA.

Пример:

```java
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
```

---

# 25. DTO

DTO используются для API.

Entity нельзя напрямую возвращать из Controller.

Правильно:

```text
Request DTO -> Service -> Entity -> Response DTO
```

Неправильно:

```text
Controller возвращает Entity напрямую
```

---

# 26. Validation

Для request DTO используем Bean Validation.

Пример:

```java
public record CreateStudentRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email
) {
}
```

---

# 27. Exceptions

Для ошибок используем custom exceptions и global handler.

Пример ошибок:

```text
UserNotFoundException
EmailAlreadyExistsException
StudentProfileNotFoundException
AccessDeniedException
```

Все ошибки должны возвращаться в едином формате.

Пример:

```json
{
  "timestamp": "2026-01-01T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found",
  "path": "/api/v1/users/123"
}
```

---

# 28. Database migrations

Для миграций используем Flyway.

Миграции лежат здесь:

```text
src/main/resources/db/migration/
```

Формат имени:

```text
V1__create_users_table.sql
V2__create_student_profiles_table.sql
V3__add_user_status.sql
```

Миграции нельзя изменять после того, как они были применены.

Если нужно изменить структуру БД — создаём новую миграцию.

---

# 29. API versioning

Все endpoints должны начинаться с версии API:

```text
/api/v1
```

Примеры:

```text
POST /api/v1/auth/login
GET  /api/v1/users/me
POST /api/v1/students
GET  /api/v1/students
GET  /api/v1/courses
POST /api/v1/courses
```

---

# 30. Local development

## Требования

Перед запуском нужно установить:

```text
Java 21
Maven
Docker
Docker Compose
Git
IntelliJ IDEA
```

---

## Проверка Java

```bash
java -version
```

Ожидаем Java 21.

---

## Проверка Maven Wrapper

```bash
./mvnw -version
```

На Windows:

```bash
mvnw.cmd -version
```

---

## Сборка всего проекта

Linux/macOS:

```bash
./mvnw clean install
```

Windows:

```bash
mvnw.cmd clean install
```

---

## Сборка одного сервиса

Пример для user-service:

```bash
./mvnw -pl user-service clean install
```

Windows:

```bash
mvnw.cmd -pl user-service clean install
```

---

## Запуск одного сервиса

Пример:

```bash
./mvnw -pl user-service spring-boot:run
```

Windows:

```bash
mvnw.cmd -pl user-service spring-boot:run
```

---

# 31. Docker Compose

Docker Compose будет использоваться для локального запуска инфраструктуры.

На первом этапе:

```text
PostgreSQL
```

Позже:

```text
MinIO
Kafka/RabbitMQ
Prometheus
Grafana
```

Пример будущей структуры:

```text
infra/docker-compose/
└── docker-compose.yml
```

Запуск:

```bash
docker compose -f infra/docker-compose/docker-compose.yml up -d
```

Остановка:

```bash
docker compose -f infra/docker-compose/docker-compose.yml down
```

---

# 32. Environment variables

Пароли и настройки не храним в коде.

Используем environment variables.

Пример:

```text
DB_HOST=localhost
DB_PORT=5432
DB_NAME=user_db
DB_USERNAME=postgres
DB_PASSWORD=postgres
JWT_SECRET=change-me
```

---

# 33. application.yml

У каждого сервиса должен быть свой `application.yml`.

Пример для `user-service`:

```yaml
server:
  port: 8082

spring:
  application:
    name: user-service

  datasource:
    url: jdbc:postgresql://localhost:5432/user_db
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: validate
    open-in-view: false

  flyway:
    enabled: true

management:
  endpoints:
    web:
      exposure:
        include: health,info

springdoc:
  swagger-ui:
    path: /swagger-ui.html
```

---

# 34. Testing

Используем:

```text
JUnit 5
Spring Boot Test
Testcontainers
Mockito
```

Минимальные требования:

```text
- unit tests для service layer
- integration tests для repository
- controller tests для важных endpoints
```

Запуск тестов:

```bash
./mvnw test
```

Windows:

```bash
mvnw.cmd test
```

---

# 35. CI/CD

На первом этапе CI должен делать:

```text
1. Checkout code
2. Setup Java 21
3. Run Maven build
4. Run tests
```

Позже:

```text
5. Build Docker images
6. Push images to registry
7. Deploy to environment
```

---

# 36. Security rules

Общие правила безопасности:

```text
- Пароли храним только в hash
- Никогда не логируем password
- JWT secret не храним в Git
- ADMIN endpoints доступны только ADMIN
- STUDENT endpoints доступны только STUDENT или владельцу данных
- Все request DTO валидируются
```

---

# 37. Logging

Логи должны быть понятными.

Нельзя логировать:

```text
password
access token
refresh token
secret keys
personal sensitive data без необходимости
```

Можно логировать:

```text
service started
request id
business event
error message
operation result
```

---

# 38. Что не делаем на первом этапе

Чтобы не усложнять проект, на первом этапе не добавляем:

```text
Kafka
RabbitMQ
Redis
Kubernetes
Service Discovery
Config Server
Distributed Tracing
Complex monitoring
```

Это будет позже.

---

# 39. Первый список задач для User Service

Epic:

```text
User Service
```

Tasks:

```text
1. Create user entity
2. Create student profile entity
3. Implement create student endpoint
4. Implement get current user profile
5. Implement get students list endpoint
6. Implement update student profile endpoint
```

---

# 40. Первый список задач для Project Setup

Epic:

```text
Project Setup
```

Tasks:

```text
1. Create monorepo structure
2. Configure parent pom.xml
3. Add service modules
4. Add docs directory
5. Add infra directory
6. Add README.md
7. Configure Maven Wrapper
8. Verify full Maven build
```

---

# 41. Команды для первого запуска

После клонирования проекта:

```bash
git clone <repository-url>
cd tentech-edu-backend
```

Проверить сборку:

```bash
./mvnw clean install
```

Windows:

```bash
mvnw.cmd clean install
```

Запустить конкретный сервис:

```bash
./mvnw -pl user-service spring-boot:run
```

Windows:

```bash
mvnw.cmd -pl user-service spring-boot:run
```

Проверить health:

```text
http://localhost:8082/actuator/health
```

Проверить Swagger:

```text
http://localhost:8082/swagger-ui/index.html
```

---

# 42. Правила для студентов

Каждый студент должен:

```text
1. Взять задачу из Jira
2. Создать отдельную Git-ветку
3. Написать код
4. Проверить локально
5. Добавить тесты
6. Сделать commit
7. Создать Pull Request
8. Дождаться review
9. Исправить замечания
10. После approve влить изменения
```

---

# 43. Правила для тимлида

Тимлид должен:

```text
1. Создавать Epic
2. Создавать Tasks
3. Назначать задачи студентам
4. Проверять Pull Request
5. Следить за архитектурой
6. Следить за качеством кода
7. Не давать смешивать ответственность сервисов
8. Следить за Definition of Done
9. Проводить короткие созвоны/status check
10. Обновлять документацию
```

---

# 44. Current project status

Текущий статус:

```text
Project initialized
Monorepo structure created
Maven parent project configured
Microservice modules created
README in progress
Jira project created
Initial Epics created
User Service tasks started
```

---

# 45. License

Internal educational project for TenTech Edu.
