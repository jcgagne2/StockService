package ch.bdt.spike.spring.cloud.stockservice.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockPrice {
    private String symbol;
    private double price;
    private String currency;

}
