# rxfire
### Reactive Extensions for gemfire

Code from (blog post)[http://vcc.devguild.us/blog/2015/04/07/rxfire-gemfire-meets-rx/] on how to convert [Gemfire](http://pivotal.io/big-data/pivotal-gemfire) Continuous Query support into [RXJava](https://github.com/ReactiveX/RxJava) streams

To run, simply start the gemfire server that is on the /server subproject:

```
./gradlew :server:bootRun
```

Run the tests on stream-aggregator to verify the results.

What's really worthy here is:

``` java 

public class SubscriberEventAdapter implements EventAdapter{

    final Subscriber subscriber;

    public SubscriberEventAdapter(Subscriber subscriber){
        this.subscriber = subscriber;
    }


    @Override
    public void handleEvent(CqEvent event) {
        subscriber.onNext(event);
    }


}
```

Adapter to pass the CqEvent to the Subscriber

``` java

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

```

Observer creation (Hot Observer), each new event from the CQListener will invoke Observer.onNext()

```
  Observable<CqEvent> observable = streamService.toObservable("SELECT * FROM /stocks t where t.value > 1");
        observable.map((event) -> {
            return (Stock) ((PdxInstance) event.getNewValue()).getObject();
        }).filter((stock) -> {
            return stock.getId().equals("PVT");
        }).take(5).subscribe((stock) -> {
                    System.out.println(stock);
                }, (error) -> {
                    error.printStackTrace();
                }
        );


```

Simple example, transforms from PDXInstance into the Stock Object, filters only certain types of Stocks. 


