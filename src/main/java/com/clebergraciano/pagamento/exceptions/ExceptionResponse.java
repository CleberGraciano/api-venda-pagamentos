package com.clebergraciano.pagamento.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse implements Serializable {
    private static final long serialVersion = 1L;

    private Date timestamp;
    private String message;
    private String details;
}
