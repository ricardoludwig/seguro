package com.app.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "Schema de resposta para criação de cliente" +
        "e recuperação de token")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class ClienteResponse {

    @Schema(description = "Mensagem descritiva")
    private String message;

    @Schema(description = "Token")
    private String token;

}
