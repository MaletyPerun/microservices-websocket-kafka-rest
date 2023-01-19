package com.example.microservice1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Data
@AllArgsConstructor
public class MessageDto implements Serializable {

    // TODO: 08.01.2023 ВОИ
    // TODO: 17.01.2023 использовать jsonProperty, но будет отваливаться МС3, что отправляет невалидное сообщение
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private int id;
    @NotNull
    private int session;
    @NotNull
    @JsonProperty("MC1_timestamp")
    private LocalDateTime mc1Timestamp;
    @NotNull
    @JsonProperty("MC2_timestamp")
    private LocalDateTime mc2Timestamp;
    @NotNull
    @JsonProperty("MC3_timestamp")
    private LocalDateTime mc3Timestamp;
//    @NotNull
    @JsonProperty("end_timestamp")
    private LocalDateTime endTimestamp;

}
