package com.app.rest;

import com.app.dto.ClienteDTO;
import com.app.security.CredentialsDTO;
import com.app.security.JWTUtil;
import com.app.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Tag(name = "Autenticação com token JWT",
        description = "Endpoints para criação de usuário e senha e geração " +
                "de token JWT")
@RestController
@RequestMapping("/api/auth")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Cadastra novo cliente",
            description = "Cadastro novo cliente liberando acesso para realizar cotação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado",
                    content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "422", description = "Cliente já cadastrado",
                    content = @Content)
    })
    @PostMapping(value = "/registrar",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ClienteResponse> registrar(@RequestBody ClienteDTO clienteDTO) {
        String encodedPass = passwordEncoder.encode(clienteDTO.getPassword());
        clienteDTO.setPassword(encodedPass);

        boolean criado = service.criar(clienteDTO);
        if (criado) {
            String token = jwtUtil.geraToken(clienteDTO.getUsername());
            ClienteResponse response =
                    new ClienteResponse
                            .ClienteResponseBuilder()
                            .token(token)
                            .message("Token criado")
                            .build();
            return new ResponseEntity<>(response, CREATED);
        }
        ClienteResponse response = new ClienteResponse
                .ClienteResponseBuilder()
                .message("Não foi possível criar o seu usuário")
                .build();
        return new ResponseEntity<>(response, UNPROCESSABLE_ENTITY);

    }

    @Operation(summary = "Atualização do Token",
            description = "Endpoint para recuperar um novo token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Token atualizado",
                    content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos",
                    content = @Content)
    })
    @PostMapping(value = "/refresh-token",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ClienteResponse> refreshToken(@RequestBody CredentialsDTO body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getUsername(),
                            body.getPassword());
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.geraToken(body.getUsername());

            ClienteResponse response =
                    new ClienteResponse
                            .ClienteResponseBuilder()
                            .token(token)
                            .message("Token atualizado")
                            .build();

            return new ResponseEntity<>(response, OK);

        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
    }
}
