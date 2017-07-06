package com.vbokhan.informationhandling.exception;

public class WrongDataException extends Exception {
    public WrongDataException() {
        super();
    }

    public WrongDataException(String message) {
        super(message);
    }

    public WrongDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDataException(Throwable cause) {
        super(cause);
    }
}
