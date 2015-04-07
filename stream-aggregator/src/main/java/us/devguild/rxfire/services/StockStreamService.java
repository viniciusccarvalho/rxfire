package us.devguild.rxfire.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.listener.ContinuousQueryListenerContainer;
import rx.Observable;
import us.devguild.rxfire.model.Stock;

/**
 * Created by vcarvalho on 4/7/15.
 */
public class StockStreamService {

    @Autowired
    private ContinuousQueryListenerContainer cqlc;

    public Observable<Stock> toObservable(){

        return Observable.create((subscriber) -> {

        });
    }

}
