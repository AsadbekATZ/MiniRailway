package com.example.MiniRailway.exception;

public class WrongSearchException extends RuntimeException{
    public WrongSearchException(String message) {
        super(message);
    }
}
