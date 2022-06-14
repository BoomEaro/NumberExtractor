package ru.boomearo.numberextractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Response {

    protected List<ErrorData> errors;

}
