package com.spfka.tiendaonline.utilities;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Clase encargada de mapear la respuesta
 * @version 1.0.0
 * @author Camilo Morales S
 */
@Data
@Component
public class Response {

    /**
     * Estado de la respuestas
     */
    private Boolean ok;

    /**
     * Status code de la respuesta
     */
    private Integer status;

    /**
     * Mernsaje de la respuesta
     */
    private String message;

    /**
     * datos de la respuesta
     */
    private Object data;

    public void responseMessage(Boolean ok, HttpStatus status, String message, Object data) {
        this.ok = ok;
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    public void responseMessage(Boolean ok, HttpStatus status, String message) {
        this.ok = ok;
        this.status = status.value();
        this.message = message;
        this.data = List.of();
    }

}
