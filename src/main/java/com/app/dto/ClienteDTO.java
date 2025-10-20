package com.app.dto;

import com.app.model.ClienteEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class ClienteDTO {

    @Schema(description = "Nome do cliente", example = "Ricardo",
            requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotBlank
    private String nome;

    @Schema(description = "Nome de usuário do aplicação", example = "dev_rlp",
            requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotBlank
    private String username;

    @Schema(description = "Senha do usuário do aplicação", example = "dev_@xP12r",
            requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotBlank
    private String password;

    @Schema(description = "Email do usuário do aplicação",
            example = "dev_rlp@teste.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotBlank
    private String email;

    public static ClienteDTO valueOf(ClienteEntity entity) {
        return new ClienteDTOBuilder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .build();
    }
}