package io.vasiliyplatonov.graph;

public class notExistingNodeException extends Exception {
    public notExistingNodeException() {
        super();
    }

    public notExistingNodeException(String message) {
        super(message);
    }

}
