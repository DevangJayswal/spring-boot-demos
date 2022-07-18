package com.adidas.promo.backend.domain.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = -817080463442384038L;

    private String username;
    private String sessionId;
    private String accessToken;
}
