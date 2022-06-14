package ru.boomearo.numberexcractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {

    private String error;
    private long time;

    public ErrorData(String error) {
        this.error = error;
        this.time = System.currentTimeMillis();
    }
}
