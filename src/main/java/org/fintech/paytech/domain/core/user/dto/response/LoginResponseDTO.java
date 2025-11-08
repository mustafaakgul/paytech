package org.fintech.paytech.domain.core.user.dto.response;

public class LoginResponseDTO {

    private String accessToken;
    private String refreshToken;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public LoginResponseDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
