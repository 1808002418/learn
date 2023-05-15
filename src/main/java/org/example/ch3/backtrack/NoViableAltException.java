package org.example.ch3.backtrack;

public class NoViableAltException extends RecognitionException {
    public NoViableAltException(String message) {
        super(message);
    }
}
