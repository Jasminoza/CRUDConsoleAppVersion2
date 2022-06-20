## **Задача**: 
##### Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

* ###### Developer (id, firstName, lastName, List<Skill> skills, Specialty specialty)
* ###### Skill
* ###### Specialty
* ###### Status (enum ACTIVE, DELETED)

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

##### Требования:

1. ###### Придерживаться шаблона MVC (пакеты model, repository, service, controller, view)
2. ###### Для миграции БД использовать https://www.liquibase.org/
3. ###### Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito).
4. ###### Для импорта библиотек использовать Maven

## Технологии: Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito.

