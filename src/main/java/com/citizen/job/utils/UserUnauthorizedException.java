package com.citizen.job.utils;

public class UserUnauthorizedException extends RuntimeException{
    public UserUnauthorizedException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "User us not authorized!";
    }
}
