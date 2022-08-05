package br.com.kactus.crud.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ReturnError {
    private HttpStatus code;
    private String message;
}
