package com.example.microservice3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("MC1_timestamp")
    private LocalDateTime mc1Timestamp;
    @JsonProperty("MC2_timestamp")
    private LocalDateTime mc2Timestamp;
    @JsonProperty("MC3_timestamp")
    private LocalDateTime mc3Timestamp;
    @JsonProperty("end_timestamp")
    private LocalDateTime endTimestamp;

}
