package com.example.microservice2;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @NotNull
    private int id;

    @NotNull
    private int session_id;

    private LocalDateTime MC1_timestamp;

    private LocalDateTime MC2_timestamp;

    private LocalDateTime MC3_timestamp;

    private LocalDateTime end_timestamp;
}
