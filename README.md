#### Цель задания – разработать 3 микросервиса для передачи сообщения определенного формата с использовнаием Apache Kafka, WebSocket и REST API.

-------

### ТЗ:

Создать три взаимодействующих между собой микросервиса МС1, МС2 и МС3. Микросервисы взаимодействую между собой следующим образом:
1) МС1 создает сообщение следующего формата:


      {
         id: integer,
         “session_id”: integer,
         “MC1_timestamp”: datetime,
         “MC2_timestamp”: datetime,
         “MC3_timestamp”: datetime,
         “end_timestamp”: datetime
      }
“session_id” - номер сеанса взаимодействия 

записывает в поле “MC1_timestamp” текущее время и отправляет сообщение в МС2 через WebSocket;

2) МС2 принимает сообщение от МС1, записывает в поле сообщения “МС2_timestamp” текущее время и отправляет сообщение в МС3 через топик брокера Kafka;

3) МС3 принимает сообщение от МС2, записывает в поле сообщения “МС3_timestamp” текущее время и отправляет сообщение в МС1 посредством отправки http запроса POST с телом, содержащим сообщение;

4) МС1 принимает сообщение от МС3, записывает в поле“end_timestamp” текущее время, записывает сообщение в базу данных;

5) повторить цикл взаимодействия в течение заданного интервала взаимодействия.
   Длительность интервала взаимодействия задается в секундах параметром в конфигурационном файле.
   Запуск микросервисов и окружения производить в docker-compose.
   Старт взаимодействия осуществить отправкой запроса GET на /start/ без параметров в МС1.
   Досрочную остановку взаимодействия осуществить отправкой запроса GET на /stop/ без параметров в МС1.
   Начало взаимодействия микросервисов индицировать на консоль.
   Завершение взаимодействия индицировать на консоль с выводом следующих параметров:
- время взаимодействия; 
- количество сообщений, сгенерированных во время взаимодействия.

-------

Время взаимодействия микросервисов задается параметром `app.ms1.work.time` в ресурсах первого микросервиса (microservice-1)
Сообщения между МС1-МС2 и МС3-МС1 передаются в JSON формате с дополнительной настройкой для корректной записи объектов класса LocalDateTime.
Каждый микросервис запускается на определенному порту, порты указаны в файлах конфигурации.

-------

#### Запуск микросервисов и окружения 
Для запуска микросервисов используется Docker, загрузить проект из GitHub
В командной строке выполнить:

      docker-compose up

-------

API:

####  Запустить процесс взаимодействия микросервисов
`curl --location --request GET 'http://localhost:53251/MS1/start`

#### Остановить процесс взаимодействия микросервисов
`curl --location --request GET 'http://localhost:53251/MS1/stop`

#### Получить сообщение с номером {id}
`curl --location --request GET 'http://localhost:53251/MS1/message?id={id}`

#### Получить все сообщения 
`curl --location --request GET 'http://localhost:53251/MS1/all`
