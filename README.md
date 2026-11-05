# PetClinic API — Cucumber Tests

## Описание проекта

Автоматизированное тестирование REST API приложения PetClinic с использованием Java, Maven, Rest Assured и Cucumber.

## Используемые технологии

- Java
- Maven
- Rest Assured
- Cucumber
- JUnit 5

## Реализованные сценарии

- Успешное создание владельца животного.
- Создание владельца без имени (негативный сценарий).
- Получение информации о владельце.
- Использование Scenario Outline и Examples.
- Использование Background, Hooks и тегов.

## Запуск тестов

1. Запустить приложение PetClinic.
2. Убедиться, что API доступен по адресу `http://localhost:9966/petclinic/api`.
3. Выполнить команду:

```bash
mvn test
```

## Отчёт

После выполнения тестов HTML-отчёт находится здесь:

`target/cucumber-report.html`

## Feature-файл

Основные BDD-сценарии находятся в:

`src/test/resources/features/create_owner.feature`
