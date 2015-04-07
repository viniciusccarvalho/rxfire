package us.devguild.rxfire.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Stock {
    public Stock(String id, Double value, Long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

    public Stock(){}

    @Id
	private String id;

    private Long timestamp;

    private Double value;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }



    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }
}
