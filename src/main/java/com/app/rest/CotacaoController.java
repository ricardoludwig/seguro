package com.app.rest;

import com.app.dto.CotacaoDTO;
import com.app.service.CotacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Criação, leitura e exclusão de cotações",
        description = "Endpoints para criação leitura e exclusão " +
                "de cotações, por usuário existente no token JWT")
@RestController
@RequestMapping("/api/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService service;

    @SecurityRequirement(name = "bearer-jwt")
    @PostMapping(value = "/criar",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CotacaoResponse> criar(
            @RequestBody CriarCotacaoRequest cotacaoRequest) {
        String userName = (String) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        service.criar(cotacaoRequest, userName);

        CotacaoResponse response = new CotacaoResponse(userName, "Adicionado");
        return new ResponseEntity<>(response, CREATED);
    }

    @SecurityRequirement(name = "bearer-jwt")
    @GetMapping(value = "/listar",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CotacaoListResponse> listar() {
        String userName = (String) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        try {
            List<CotacaoDTO> cotacaoList = service.buscar(userName);
            CotacaoListResponse response =
                    new CotacaoListResponse(userName,
                            "Quantidade de cotações encontradas: "
                                    + cotacaoList.size(), cotacaoList);
            return new ResponseEntity<CotacaoListResponse>(response, OK);
        } catch (Exception ex) {
            CotacaoListResponse response =
                    new CotacaoListResponse(userName,
                            "Não existe cadastro para esse email");
            return new ResponseEntity<>(response, OK);
        }
    }

    @SecurityRequirement(name = "bearer-jwt")
    @DeleteMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CotacaoResponse> excluir(@PathVariable(value = "id",
            required = true) BigInteger id) {

        String userName = (String) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        boolean excluido = service.excluir(id, userName);

        if (excluido) {
            CotacaoResponse response =
                    new CotacaoResponse(userName,
                            "Excluído a cotação: " + id);
            return new ResponseEntity<>(response, OK);
        }

        CotacaoResponse response =
                new CotacaoResponse(userName,
                        "Cotação não encontrada " + id);
        return new ResponseEntity<>(response, NOT_FOUND);

    }
}
