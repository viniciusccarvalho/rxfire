package us.devguild.rxfire.event;

import org.springframework.util.ErrorHandler;

/**
 * Created by vcarvalho on 4/7/15.
 */
public class GenericErrorHandler implements ErrorHandler{

    @Override
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
}
