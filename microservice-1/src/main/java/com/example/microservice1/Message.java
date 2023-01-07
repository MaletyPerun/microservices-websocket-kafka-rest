package com.example.microservice1;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
    @Column(name = "session_id", nullable = false)
    private int session_id;

    @Column(name = "MC1_timestamp", nullable = false)
    private LocalDateTime MC1_timestamp;

    @Column(name = "MC2_timestamp")
    private LocalDateTime MC2_timestamp;

    @Column(name = "MC3_timestamp")
    private LocalDateTime MC3_timestamp;

    @Column(name = "end_timestamp")
    private LocalDateTime end_timestamp;

}
