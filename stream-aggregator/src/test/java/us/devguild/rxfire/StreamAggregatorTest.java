package us.devguild.rxfire;

import com.gemstone.gemfire.cache.query.CqEvent;
import com.gemstone.gemfire.pdx.PdxInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rx.Observable;
import us.devguild.rxfire.model.Stock;
import us.devguild.rxfire.repository.StockRepository;
import us.devguild.rxfire.services.StockStreamService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vcarvalho on 4/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StreamAggregatorTest {

    @Autowired
    private StockStreamService streamService;

    @Autowired
    private StockRepository repository;

    private List<String> symbols = Arrays.asList("EMC", "VMW", "MSFT", "GOOG", "AAPL", "PVT");

    private ExecutorService pool = Executors.newCachedThreadPool();

    private CountDownLatch latch;

    @Before
    public void setup(){
        latch = new CountDownLatch(100);
        pool.submit(new StockTicker());
    }

    @Test
    public void testStreamAggregation() throws Exception{
        Observable<CqEvent> observable = streamService.toObservable("SELECT * FROM /stocks t where t.value > 1");
       observable.map((event) -> {
            return (Stock)((PdxInstance)event.getNewValue()).getObject();
        }).filter((stock) -> {
           return stock.getId().equals("EMC");
        }).take(5).subscribe((stock) -> {
                    System.out.println(stock);
                }, (error) -> {
                   error.printStackTrace();
               }
        );





        latch.await();
    }


     class StockTicker implements Runnable{
        @Override
        public void run() {
            Random r = new Random();

            while(latch.getCount() > 0){
                Stock stock = new Stock(symbols.get(r.nextInt(symbols.size())),r.nextDouble()*100,System.currentTimeMillis());
                StreamAggregatorTest.this.repository.save(stock);
                latch.countDown();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }


        }
    }


}
