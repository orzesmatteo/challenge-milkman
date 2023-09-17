package it.milkman.challenge.server.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ResourceMismatchException extends RuntimeException {

    private final UUID givenUUID;

    private final UUID dtoUUID;

    public ResourceMismatchException(String message, UUID givenUUID, UUID dtoUUID) {
        super(message);
        this.givenUUID = givenUUID;
        this.dtoUUID = dtoUUID;
    }

    public ResourceMismatchException(String message, Throwable cause, UUID givenUUID, UUID dtoUUID) {
        super(message, cause);
        this.givenUUID = givenUUID;
        this.dtoUUID = dtoUUID;
    }
}
