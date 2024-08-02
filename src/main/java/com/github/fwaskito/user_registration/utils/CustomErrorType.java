package com.github.fwaskito.user_registration.utils;

import com.github.fwaskito.user_registration.dto.UserDTO;

public class CustomErrorType extends UserDTO {

    private String errorMessage;

    public CustomErrorType(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
