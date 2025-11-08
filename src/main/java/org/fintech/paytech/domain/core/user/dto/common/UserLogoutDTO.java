package org.fintech.paytech.domain.core.user.dto.common;

public class UserLogoutDTO {
    private String token;

    public UserLogoutDTO() {
    }

    public UserLogoutDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
