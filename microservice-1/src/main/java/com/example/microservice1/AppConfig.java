package com.example.microservice1;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Profile("Dev")
    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException {
        // TODO: 05.01.2023 разобраться с подключением к БД и в IDEA просматривать сохраненные сущности: смотреть стажировку
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}


// TODO: 05.01.2023 ПЛАН
// 1. настроить цепочку передачи сообщения (готово) + логику запуска и остановки взаимодействия между сервисами (готово)
// 1.1 добавить задержки во время заполнения полей в микросервисах (готово)
// 1.2 настроить структурный (по полям) формат записи сущности в БД
// 2. настроить просмотр сущностей в БД
// 3. настроить сохранение сообщений в БД
// 4. почистить все модули от лишних классов и комментариев, распределить все по своим папкам с правильными наименованиями
// 5. настроить валидацию сообщений
// 6. настроить исключения при ошибках приема-передачи сообщений (исходя из валидации)
// 7. упаковать все в докер
// 8. подготовить README с инструкцией запуска от gradle-wrapper
// 9. обновить докер, загрузить в гит и докерхаб