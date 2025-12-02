# Book Management System - Лабораториска вежба 2

Систем за управување со книги изработен со Spring Boot, Thymeleaf и PostgreSQL база на податоци.

## 📋 Содржина

- [Опис на проектот](#опис-на-проектот)
- [Технологии](#технологии)
- [Архитектура](#архитектура)
- [Структура на проектот](#структура-на-проектот)
- [База на податоци](#база-на-податоци)
- [Конфигурација](#конфигурација)
- [Инсталација](#инсталација)
- [Користење](#користење)
- [CRUD Операции](#crud-операции)
- [API Endpoints](#api-endpoints)

---

## Опис на проектот

Ова е веб апликација за управување со книги која овозможува:
- Преглед на сите книги
- Додавање нова книга
- Ажурирање на постоечка книга
- Бришење на книга
- Пребарување на книги по наслов, жанр и рејтинг
- Управување со автори

Податоците се перзистираат во **PostgreSQL** база на податоци со помош на **Spring Data JPA** и **Hibernate**.

---

## Технологии

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
  - Spring Web (MVC)
  - Spring Data JPA
  - Spring DevTools
- **Hibernate** (ORM)
- **PostgreSQL** (Production база)
- **H2 Database** (In-memory база за тестирање)
- **Lombok** (Boilerplate код)

### Frontend
- **Thymeleaf** (Template Engine)
- **HTML5/CSS3**
- **Bootstrap** (опционално)

### Build Tool
- **Maven**

---

## Архитектура

Проектот следи **MVC (Model-View-Controller)** архитектурен шаблон и **слоевита архитектура**:

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│    (Controllers, Thymeleaf Views)       │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         Service Layer                   │
│    (Business Logic)                     │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         Repository Layer                │
│    (Data Access - JPA Repositories)     │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         Database Layer                  │
│    (PostgreSQL / H2)                    │
└─────────────────────────────────────────┘
```

---

## Структура на проектот

```
src/main/java/mk/finki/ukim/wp/lab/
│
├── model/                          # Ентитети (JPA)
│   ├── Book.java                   # Book ентитет (@Entity)
│   ├── Author.java                 # Author ентитет (@Entity)
│   └── BookReservation.java        # BookReservation ентитет
│
├── repository/                     # Repository слој
│   ├── BookRepository.java         # JPA Repository за Book
│   ├── AuthorRepository.java       # JPA Repository за Author
│   └── BookReservationRepository.java
│
├── service/                        # Service слој (бизнис логика)
│   ├── BookService.java            # Interface
│   ├── BookServiceImpl.java        # Имплементација
│   ├── AuthorService.java
│   └── AuthorServiceImpl.java
│
├── web/
│   └── controller/                 # Controller слој
│       ├── BookController.java     # HTTP endpoints за книги
│       └── BookReservationController.java
│
├── bootstrap/                      # Иницијализација
│   ├── DataLoader.java             # Вчитување на почетни податоци
│   └── DataHolder.java             # Legacy in-memory storage
│
└── Lab1Application.java            # Main Spring Boot класа

src/main/resources/
├── application.properties          # Главна конфигурација
├── application-h2.properties       # H2 профил
├── application-prod.properties     # PostgreSQL профил
└── templates/                      # Thymeleaf темплејти
    ├── listBooks.html
    └── book-form.html
```

---

## База на податоци

### ER Дијаграм

```
┌─────────────────────┐           ┌─────────────────────┐
│      AUTHORS        │           │       BOOKS         │
├─────────────────────┤           ├─────────────────────┤
│ id (PK)            │◄──────────│ id (PK)            │
│ name               │    1:N     │ title              │
│ surname            │           │ genre              │
│ country            │           │ average_rating     │
│ biography          │           │ author_id (FK)     │
└─────────────────────┘           └─────────────────────┘
```

### Табели

#### 1. **AUTHORS**
```sql
CREATE TABLE authors (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    country VARCHAR(255),
    biography TEXT
);
```

#### 2. **BOOKS**
```sql
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    genre VARCHAR(255),
    average_rating DOUBLE PRECISION,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
```

### Релации

- **Author ↔ Book**: Еден автор може да има многу книги (One-to-Many)
- **Book ↔ Author**: Една книга има еден автор (Many-to-One)

---

## Конфигурација

### Spring Профили

Проектот поддржува два профила:

#### 1. **H2 Profile** (In-Memory база за тестирање)
```properties
# application-h2.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop
```

#### 2. **Production Profile** (PostgreSQL)
```properties
# application-prod.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/books
spring.datasource.username=postgres
spring.datasource.password=BikeIsLife9@
spring.jpa.hibernate.ddl-auto=update
```

### Активен профил
```properties
# application.properties
spring.profiles.active=prod
```

### Hibernate DDL Auto
- **create-drop**: Креира табели при старт, брише при стоп (H2)
- **update**: Ги ажурира табелите без да ги брише (Production)

---

## Инсталација

### Предуслови

1. **Java 21** или повисока верзија
2. **Maven 3.6+**
3. **PostgreSQL 12+** (за production)
4. **IntelliJ IDEA** (или друго IDE)

### Чекори

#### 1. Клонирајте го репозиториумот
```bash
git clone <repository-url>
cd VP
```

#### 2. Инсталирајте PostgreSQL

**Windows:**
- Преземете од [postgresql.org](https://www.postgresql.org/download/)
- Инсталирајте и поставете password (пр. `BikeIsLife9@`)

**Linux:**
```bash
sudo apt-get install postgresql
```

**Mac:**
```bash
brew install postgresql
```

#### 3. Креирајте PostgreSQL база

**Преку psql:**
```bash
psql -U postgres
```

```sql
CREATE DATABASE books;
\q
```

**Преку pgAdmin:**
- Отворете pgAdmin
- Десен клик на Databases → Create → Database
- Име: `books`

#### 4. Конфигурирајте ја апликацијата

Изменете `src/main/resources/application-prod.properties`:
```properties
spring.datasource.username=postgres
spring.datasource.password=<вашиот-password>
```

#### 5. Build проектот

```bash
mvn clean install
```

#### 6. Стартувајте ја апликацијата

**Преку IDE:**
- Отворете `Lab1Application.java`
- Run главната метода

**Преку Maven:**
```bash
mvn spring-boot:run
```

#### 7. Пристапете до апликацијата

```
http://localhost:8080/books
```

---

## Користење

### Иницијални податоци

При првото стартување, `DataLoader` автоматски вчитува:

**3 автори:**
1. George Orwell (UK)
2. J.R.R. Tolkien (UK)
3. Fyodor Dostoevsky (Russia)

**3 книги:**
1. 1984 (Dystopian, 4.8) - George Orwell
2. The Hobbit (Fantasy, 4.7) - J.R.R. Tolkien
3. Crime and Punishment (Classic, 4.9) - Fyodor Dostoevsky

### H2 Console (за тестирање)

Доколку користите H2 профил:
```properties
spring.profiles.active=h2
```

Пристапете до H2 конзола:
```
http://localhost:8080/h2-console
```

**Креденцијали:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (празно)

---

## CRUD Операции

### 1. CREATE (Додавање книга)

**URL:** `GET /books/book-form`

1. Отворете формата за додавање
2. Пополнете:
   - Наслов
   - Жанр
   - Просечен рејтинг
   - Избор на автор (dropdown)
3. Submit → `POST /books/add`

**Backend:**
```java
@PostMapping("/add")
public String saveBook(@RequestParam String title,
                       @RequestParam String genre,
                       @RequestParam Double averageRating,
                       @RequestParam Long authorId) {
    bookService.save(title, genre, averageRating, authorId);
    return "redirect:/books";
}
```

**Repository:**
```java
Book book = new Book(title, genre, averageRating, author);
bookRepository.save(book);  // Hibernate: INSERT INTO books ...
```

---

### 2. READ (Прегледување)

**URL:** `GET /books`

Прикажува листа на сите книги од базата.

**Backend:**
```java
@GetMapping
public String getBooksPage(Model model) {
    model.addAttribute("books", bookService.listAll());
    return "listBooks";
}
```

**Repository:**
```java
List<Book> books = bookRepository.findAll();
// Hibernate: SELECT * FROM books JOIN authors ...
```

---

### 3. UPDATE (Ажурирање)

**URL:** `GET /books/book-form/{id}`

1. Кликнете Edit на листата
2. Формата се пополнува со постоечки податоци
3. Изменете ги полињата
4. Submit → `POST /books/edit/{id}`

**Backend:**
```java
@PostMapping("/edit/{bookId}")
public String editBook(@PathVariable Long bookId,
                       @RequestParam String title,
                       @RequestParam String genre,
                       @RequestParam Double averageRating,
                       @RequestParam Long authorId) {
    bookService.edit(bookId, title, genre, averageRating, authorId);
    return "redirect:/books";
}
```

**Repository:**
```java
Book book = bookRepository.findById(id).orElseThrow(...);
book.setTitle(title);
book.setGenre(genre);
// ...
bookRepository.save(book);  // Hibernate: UPDATE books ...
```

---

### 4. DELETE (Бришење)

**URL:** `POST /books/delete/{id}`

1. Кликнете Delete копче
2. Книгата се брише од базата

**Backend:**
```java
@PostMapping("/delete/{id}")
public String deleteBook(@PathVariable Long id) {
    bookService.deleteById(id);
    return "redirect:/books";
}
```

**Repository:**
```java
bookRepository.deleteById(id);
// Hibernate: DELETE FROM books WHERE id = ?
```

---

## API Endpoints

| HTTP Method | Endpoint                  | Опис                          |
|-------------|---------------------------|-------------------------------|
| GET         | `/books`                  | Листа на сите книги           |
| GET         | `/books/book-form`        | Форма за додавање книга      |
| GET         | `/books/book-form/{id}`   | Форма за ажурирање книга     |
| POST        | `/books/add`              | Зачувување нова книга        |
| POST        | `/books/edit/{id}`        | Ажурирање на книга           |
| POST        | `/books/delete/{id}`      | Бришење на книга             |

---

## Како работи интеграцијата со база?

### 1. JPA Ентитети

**Book.java:**
```java
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private double averageRating;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
```

**Анотации:**
- `@Entity` - Означува дека класата е JPA ентитет
- `@Table(name = "books")` - Мапира кон табела `books`
- `@Id` - Примарен клуч
- `@GeneratedValue` - Auto-increment
- `@ManyToOne` - Many books → One author
- `@JoinColumn` - Надворешен клуч

---

### 2. JPA Repository

```java
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor_Id(Long authorId);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAverageRatingGreaterThanEqual(Double rating);
}
```

**Што добиваме:**
- `findAll()` - SELECT * FROM books
- `findById(id)` - SELECT * WHERE id = ?
- `save(book)` - INSERT или UPDATE
- `deleteById(id)` - DELETE WHERE id = ?
- **Custom методи** - Автоматски генерирани SQL queries

---

### 3. Service слој

```java
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book save(String title, String genre,
                     Double rating, Long authorId) {
        Author author = authorRepository.findById(authorId)
            .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = new Book(title, genre, rating, author);
        return bookRepository.save(book);  // Hibernate ја зачувува
    }
}
```

---

### 4. Hibernate во акција

При `bookRepository.save(book)`, Hibernate:

1. **INSERT** (нова книга):
```sql
INSERT INTO books (title, genre, average_rating, author_id)
VALUES ('1984', 'Dystopian', 4.8, 1);
```

2. **UPDATE** (постоечка книга):
```sql
UPDATE books
SET title = '1984', genre = 'Dystopian', average_rating = 4.8, author_id = 1
WHERE id = 5;
```

3. **SELECT** со релации:
```sql
SELECT b.*, a.*
FROM books b
LEFT JOIN authors a ON b.author_id = a.id
WHERE b.id = 5;
```

---

## Тестирање

### Unit тестови
```bash
mvn test
```

### Рачно тестирање

1. **Додајте нова книга**
   - `/books/book-form`
   - Пополнете форма → Submit

2. **Проверете во база**
   ```sql
   psql -U postgres -d books
   SELECT * FROM books;
   ```

3. **Изменете книга**
   - Кликнете Edit
   - Променете податоци → Submit

4. **Проверете промени**
   ```sql
   SELECT * FROM books WHERE id = 4;
   ```

5. **Избришете книга**
   - Кликнете Delete

6. **Проверете бришење**
   ```sql
   SELECT * FROM books;  -- Книгата не треба да постои
   ```

---

## Troubleshooting

### 1. Грешка: "password authentication failed"

**Решение:**
- Проверете го password-от во `application-prod.properties`
- Осигурајте се дека PostgreSQL серверот работи

```bash
# Windows
services.msc → PostgreSQL → Start

# Linux
sudo systemctl start postgresql
```

---

### 2. Грешка: "database books does not exist"

**Решение:**
```sql
psql -U postgres
CREATE DATABASE books;
\q
```

---

### 3. Порт 8080 е зафатен

**Решение:**
```properties
# application.properties
server.port=8081
```

---

### 4. Табелите не се креираат

**Решение:**
- Проверете `spring.jpa.hibernate.ddl-auto=update`
- Рестартирајте ја апликацијата
- Проверете логови за грешки

---

## Автор

- **Име:** [Вашето име]
- **Индекс:** [Вашиот индекс]
- **Предмет:** Веб Програмирање
- **Факултет:** ФИНКИ, УКИМ

---

## Лиценца

Овој проект е креиран за образовни цели.

---

## Дополнителни ресурси

- [Spring Boot Документација](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Hibernate ORM](https://hibernate.org/orm/)
- [PostgreSQL](https://www.postgresql.org/)
- [Thymeleaf](https://www.thymeleaf.org/)
