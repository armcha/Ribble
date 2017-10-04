package io.armcha.arch;

/**
 * Created by Chatikyan on 01.07.2017.
 */

class MvpException extends RuntimeException {

    MvpException(String message) {
        super(message);
    }

    MvpException(String message, Throwable cause) {
        super(message, cause);
    }

    MvpException(Throwable cause) {
        super(cause);
    }
}
