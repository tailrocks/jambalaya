package com.zhokhov.jambalaya.graphql.model;

import edu.umd.cs.findbugs.annotations.NonNull;

import static com.zhokhov.jambalaya.checks.Preconditions.checkNotNull;

/**
 * The basic class for GraphQL business logic errors.
 *
 * @param <T> the type of error code enum
 * @author Alexey Zhokhov
 */
public abstract class AbstractError<T extends Enum> {

    private final T code;
    private final String message;

    /**
     * @param code    the code error
     * @param message the error message
     */
    public AbstractError(@NonNull T code, @NonNull String message) {
        checkNotNull(code, "code");
        checkNotNull(message, "message");

        this.code = code;
        this.message = message;
    }

    /**
     * @return enum value of the error code
     */
    public T getCode() {
        return code;
    }

    /**
     * @return the error message - detailed message which can be shown to the user
     */
    public String getMessage() {
        return message;
    }

}
