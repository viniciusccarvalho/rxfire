package us.devguild.rxfire.services;

import com.gemstone.gemfire.cache.query.CqEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.listener.ContinuousQueryDefinition;
import org.springframework.data.gemfire.listener.ContinuousQueryListenerContainer;
import org.springframework.data.gemfire.listener.adapter.ContinuousQueryListenerAdapter;
import org.springframework.stereotype.Component;
import rx.Observable;
import us.devguild.rxfire.event.SubscriberEventAdapter;

/**
 * Created by vcarvalho on 4/7/15.
 */
@Component
public class StockStreamService {

    @Autowired
    private ContinuousQueryListenerContainer cqlc;

    public Observable<CqEvent> toObservable(String query){

        return Observable.create((subscriber) -> {
            ContinuousQueryDefinition def = new ContinuousQueryDefinition(query, new ContinuousQueryListenerAdapter(new SubscriberEventAdapter(subscriber)));
            cqlc.addListener(def);

        });
    }

}
