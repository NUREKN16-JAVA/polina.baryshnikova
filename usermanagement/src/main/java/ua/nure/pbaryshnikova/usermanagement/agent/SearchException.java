package ua.nure.pbaryshnkova.usermanagement.agent;

/**
 * Created by Polina on 25-Dec-18.
 */

public class SearchException extends Exception {

    private static final long serialVersionUID = 1L;


    public SearchException(String e) {
        super(e);
    }

    public SearchException() {
        super();
    }

    public SearchException(Throwable cause) {
        super(cause);
    }

}