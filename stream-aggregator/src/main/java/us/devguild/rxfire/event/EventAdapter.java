package us.devguild.rxfire.event;

import com.gemstone.gemfire.cache.query.CqEvent;

/**
 * Created by vcarvalho on 4/7/15.
 */
public interface EventAdapter {

    void handleEvent(CqEvent event);
    void handleEvent(Throwable t);


}
