package com.example.microservice1.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // FIXME: 08.01.2023 поправить валидацию полей
    @Column(name = "session_id")
    private int session;

    @Column(name = "MC1_timestamp")
    private LocalDateTime MC1_timestamp;

    @Column(name = "MC2_timestamp")
    private LocalDateTime MC2_timestamp;

    @Column(name = "MC3_timestamp")
    private LocalDateTime MC3_timestamp;

    @Column(name = "end_timestamp")
    private LocalDateTime end_timestamp;

}
