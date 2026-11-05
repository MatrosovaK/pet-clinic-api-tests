
Feature: Создание владельца животного

  Как пользователь PetClinic
  Я хочу создавать владельцев животных
  Чтобы регистрировать их в системе

  Background:
    Given API PetClinic доступен

  @positive @regression
  Scenario Outline: Успешное создание разных владельцев
    When я создаю владельца "<firstName>" с фамилией "<lastName>"
    Then код ответа должен быть 201
    And владелец должен быть успешно создан

    Examples:
      | firstName | lastName |
      | George    | Franklin |
      | Betty     | Davis    |
      | John      | Smith    |

  @negative @regression
  Scenario: Создание владельца с пустым именем
    When я отправляю запрос на создание владельца без имени
    Then система должна вернуть ошибку

  @positive @regression
  Scenario: Получение информации о созданном владельце
    And владелец с ID 1 существует
    When я запрашиваю информацию о владельце с ID 1
    Then код ответа должен быть 200
    And информация о владельце должна быть возвращена
