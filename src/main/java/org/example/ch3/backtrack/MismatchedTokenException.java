package org.example.ch3.backtrack;

public class MismatchedTokenException extends RecognitionException{

    public MismatchedTokenException(String message) {
        super(message);
    }
}
