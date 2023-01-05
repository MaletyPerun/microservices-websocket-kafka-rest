package com.example.microservice2;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Data
@Builder
public class MessageDto implements Serializable {

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
