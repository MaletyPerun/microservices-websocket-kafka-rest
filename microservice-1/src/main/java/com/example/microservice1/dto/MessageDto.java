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
    @JsonProperty("end_timestamp")
    private LocalDateTime endTimestamp;

}
