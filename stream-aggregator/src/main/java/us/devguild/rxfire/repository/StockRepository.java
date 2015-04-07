package us.devguild.rxfire.repository;

import org.springframework.data.gemfire.mapping.Region;
import org.springframework.data.repository.CrudRepository;
import us.devguild.rxfire.model.Stock;

/**
 * Created by vcarvalho on 4/7/15.
 */
@Region("stocks")
public interface StockRepository extends CrudRepository<Stock,String>{

}
