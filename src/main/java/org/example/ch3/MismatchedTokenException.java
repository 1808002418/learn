package org.example.ch3;

public class MismatchedTokenException extends RecognitionException{

    public MismatchedTokenException(String message) {
        super(message);
    }
}
