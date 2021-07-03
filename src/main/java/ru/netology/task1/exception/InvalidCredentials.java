package ru.netology.task1.exception;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String msg) {
        super(msg);
    }
}