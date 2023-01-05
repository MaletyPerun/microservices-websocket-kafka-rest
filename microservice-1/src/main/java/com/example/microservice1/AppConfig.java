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
        // TODO: 05.01.2023 правильно подключить БД к проекту
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
