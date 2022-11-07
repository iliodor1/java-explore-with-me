# Explore with me
Explore with me - это приложение в котором вы можете предложить любое мероприятие, от выставки до похода в кино, и собрать
компанию друзей для участия в нем.
### Стек технологий
- Java 11
- Spring Boot 2.7.2
- Maven 4.0.0
- PostgreSQL 42.3.6

### Описание сервисов
Приложение состоит из двух сервисов:
#### 1. Основной сервис — содержит всё необходимое для работы продукта.
* Для неавторизованных пользователей доступен просмотр событий.
* Авторизованные пользователи могут просматривать, добавлять события и отправлять запросы на участие в событиях.
* Администратор может добавлять пользователей, публиковать и редактировать события, создавать и редактировать подборки событий, закреплять и откреплять подборки событий.


#### 2. Сервис статистики — хранит количество просмотров и позволяет делать различные выборки для анализа работы приложения.

Сервис собирает статистику о количестве обращений пользователей к спискам событий и о количестве запросов к подробной информации о событии.
## Запуск
#### Шаги:
1. Clone code
   `git clone https://github.com/iliodor1/java-explore-with-me.git`
2. Install `mvn clean install`
3. Build the Docker Compose
   `docker-compose build`
4. Run Docker Compose
   `docker-compose up`

   Вы можете получить доступ к серверу в вашем браузере,
   чтобы убедиться, что они работают правильно:

   * Main Server URL
   http://localhost:8080
   * Спецификация основного
   сервиса: [ewm-main-service-spec.json](https://raw.githubusercontent.com/yandex-praktikum/java-explore-with-me/main/ewm-main-service-spec.json)
   * Stat Server URL
      http://localhost:9090/
   * Спецификация сервиса статистики: [ewm-stats-service.json](https://raw.githubusercontent.com/yandex-praktikum/java-explore-with-me/main/ewm-stats-service-spec.json)

### Схема базы данных

![dbdiagram](https://github.com/iliodor1/java-explore-with-me/blob/develop/dbdiagram.png)


### Автор

* **Гайнанов Эльдар** eldar.gai@yandex.ru



