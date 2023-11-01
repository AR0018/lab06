package it.unibo.exceptions.fakenetwork.impl;

public class NetworkException extends java.io.IOException {
    public NetworkException() {
        super();
        System.out.println("Network error: no response");
    }

    public NetworkException(String message) {
        super();
        System.out.println("Network error while sending message: "+message);
    }
}
