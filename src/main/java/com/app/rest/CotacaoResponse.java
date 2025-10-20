package com.app.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CotacaoResponse {
    private String userName;

    private String message;

    public CotacaoResponse(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

}
