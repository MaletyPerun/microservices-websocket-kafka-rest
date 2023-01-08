package com.example.microservice3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Data
@ToString
public class MessageDto implements Serializable {

    // TODO: 08.01.2023 ВОИ
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private int id;
    @NotNull
    private int session;
    private LocalDateTime MC1_timestamp;
    private LocalDateTime MC2_timestamp;
    private LocalDateTime MC3_timestamp;
    private LocalDateTime end_timestamp;

}
