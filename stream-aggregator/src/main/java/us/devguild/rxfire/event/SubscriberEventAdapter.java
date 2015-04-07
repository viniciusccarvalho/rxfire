package us.devguild.rxfire.event;

import com.gemstone.gemfire.cache.query.CqEvent;
import rx.Subscriber;

/**
 * Created by vcarvalho on 4/7/15.
 */
public class SubscriberEventAdapter implements EventAdapter{

    final Subscriber subscriber;

    public SubscriberEventAdapter(Subscriber subscriber){
        this.subscriber = subscriber;
    }


    @Override
    public void handleEvent(CqEvent event) {
        subscriber.onNext(event);
    }

    @Override
    public void handleEvent(Throwable t) {
        subscriber.onError(t);
    }
}
