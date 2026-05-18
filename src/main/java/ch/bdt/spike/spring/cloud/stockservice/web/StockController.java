package ch.bdt.spike.spring.cloud.stockservice.web;

import ch.bdt.spike.spring.cloud.stockservice.api.IStockService;
import ch.bdt.spike.spring.cloud.stockservice.api.StockPrice;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@Slf4j
public class StockController implements IStockService {
    //@Autowired
    //@Lazy
    //private Disc

    @Value("${spring.application.name}")
    private String appName;

    private Map<String, StockPrice> stockPrices;

    @PostConstruct
    public void init() {
        log.info("init");
        String[] symbols = {"AAPL", "MSFT", "GOOG", "BTC"};
        Random random = new Random();
        Map<String, StockPrice> vStockPrices = new HashMap<>();
        for (String symbol : symbols) {
            StockPrice stockPrice = new StockPrice();
            double price = 1 + random.nextDouble() * 100;
            // On arrondi pour conserver 2 décimales
            price = Math.round(price * 100.0) / 100.0;
            stockPrice.setPrice(price);
            stockPrice.setSymbol(symbol);
            stockPrice.setCurrency("USD");
            vStockPrices.put(symbol, stockPrice);
        }
        stockPrices = vStockPrices;
    }

    @GetMapping(path = "/")
    public String hello() {
        return "Hello from " + appName;
        //return "Hello from " + eurekaClient.getApplication(appName).getName();
    }

    @Override
    public StockPrice getStockPrice(@RequestParam(required = true) String symbol) {
        log.info("getStockPrice for symbol: " + symbol);
        if ("BTC".equals(symbol)) {
            throw new IllegalArgumentException("bitcoin pas accepté ici");
        }
        return stockPrices.get(symbol.toUpperCase());
    }
}
