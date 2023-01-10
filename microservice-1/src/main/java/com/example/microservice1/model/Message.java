package com.example.microservice1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;


import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Message {

    // TODO: 08.01.2023 ВОИ

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // FIXME: 08.01.2023 поправить валидацию полей
    @Column(name = "session_id", nullable = false)
    private int session;

    @Column(name = "MC1_timestamp", nullable = false)
    private LocalDateTime MC1_timestamp;

    @Column(name = "MC2_timestamp")
    private LocalDateTime MC2_timestamp;

    @Column(name = "MC3_timestamp")
    private LocalDateTime MC3_timestamp;

    @Column(name = "end_timestamp")
    private LocalDateTime end_timestamp;

}
