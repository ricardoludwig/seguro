package com.app.rest;

import com.app.dto.CotacaoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CotacaoListResponse {
    private String username;
    private String message;
    private List<CotacaoDTO> cotacaoList;

    public CotacaoListResponse(String username, String message, List<CotacaoDTO> cotacaoList) {
        this.username = username;
        this.message = message;
        this.cotacaoList = cotacaoList;
    }

    public CotacaoListResponse(String username, String message) {
        this.username = username;
        this.message = message;
    }
}
