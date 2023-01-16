package com.example.microservice1.config;

import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;

@Configuration
//@PropertySource(value = "classpath:application.yml")
public class AppConfig {

    @Profile("Dev")
    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}


// TODO: 05.01.2023 ПЛАН
// 1. настроить цепочку передачи сообщения (готово) + логику запуска и остановки взаимодействия между сервисами (готово)
// 1.1 добавить задержки во время заполнения полей в микросервисах (готово) UPD: понизить задержку (готово)
// 1.2 настроить структурный (по полям) формат записи сущности в БД (спросить)
// 2. настроить просмотр сущностей в БД (готово: через RestApi MS1)
// 3. настроить сохранение сообщений в БД (готово)
// 4. почистить все модули от лишних классов и комментариев, распределить все по своим папкам с правильными наименованиями (готово)
// 5. настроить валидацию сообщений
// 6. настроить исключения при ошибках приема-передачи сообщений (исходя из валидации)
// 7. упаковать все в докер
// 7-1 указать запуск программы с параметром работы микросервисов
// 8. подготовить README с инструкцией запуска от gradle-wrapper
// 9. обновить докер, загрузить в гит и докерхаб

// 2.1 перенести все конфиги кафки в отдельную либу над модулями
// 2.2


// application.ym