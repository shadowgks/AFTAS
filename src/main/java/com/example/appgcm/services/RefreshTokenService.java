package com.example.appgcm.services;

import com.example.appgcm.models.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Long idUser);
    RefreshToken verifyExpiration(RefreshToken token);
    RefreshToken generateNewToken(RefreshToken token);
}
