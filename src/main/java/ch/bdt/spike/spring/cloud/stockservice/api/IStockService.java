package ch.bdt.spike.spring.cloud.stockservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IStockService {
    @GetMapping(path = "/getStockPrice")
    public StockPrice getStockPrice(@RequestParam(required = true) String symbol);
}
