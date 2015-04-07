package us.devguild.rxfire.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.listener.ContinuousQueryDefinition;
import org.springframework.data.gemfire.listener.ContinuousQueryListenerContainer;
import org.springframework.data.gemfire.listener.adapter.ContinuousQueryListenerAdapter;
import rx.Observable;
import us.devguild.rxfire.event.SubscriberEventAdapter;
import us.devguild.rxfire.model.Stock;

/**
 * Created by vcarvalho on 4/7/15.
 */
public class StockStreamService {

    @Autowired
    private ContinuousQueryListenerContainer cqlc;

    public Observable<Stock> toObservable(String query){

        return Observable.create((subscriber) -> {
            ContinuousQueryDefinition def = new ContinuousQueryDefinition(query, new ContinuousQueryListenerAdapter(new SubscriberEventAdapter(subscriber)));
            cqlc.addListener(def);
        });
    }

}
