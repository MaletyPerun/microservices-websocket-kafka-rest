package com.example.microservice2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDto implements Serializable {

    // TODO: 08.01.2023 валидация и обработка исключений
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private int id;
    @NotNull
    private int session_id;
    private LocalDateTime MC1_timestamp;
    private LocalDateTime MC2_timestamp;
    private LocalDateTime MC3_timestamp;
    private LocalDateTime end_timestamp;

}
