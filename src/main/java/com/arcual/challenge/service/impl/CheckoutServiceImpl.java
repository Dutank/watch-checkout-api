package com.arcual.challenge.service.impl;

import com.arcual.challenge.model.Discount;
import com.arcual.challenge.model.WatchCatalogue;
import com.arcual.challenge.service.CheckoutService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static Map<String, WatchCatalogue> catalogueRepo = new HashMap<>();
    static {
        catalogueRepo.put("001", new WatchCatalogue("001", "Rolex", 100, new Discount(3, 200)));
        catalogueRepo.put("002", new WatchCatalogue("002", "Michael Kors", 80, new Discount(2, 120)));
        catalogueRepo.put("003", new WatchCatalogue("003", "Swatch", 50, null));
        catalogueRepo.put("004", new WatchCatalogue("004", "Casio", 30, null));
    }


    @Override
    public Long checkout(List<String> watches) {
        Map<String, Long> counts = new HashMap<>();
        countWatches(watches, counts);
        return calculateTotal(counts);
    }

    private static long calculateTotal(Map<String, Long> counts) {
        long result = 0L;
        for (Map.Entry<String, Long> count : counts.entrySet()) {
            long N = count.getValue();
            WatchCatalogue watchCatalogue = catalogueRepo.get(count.getKey());
            if (watchCatalogue.getDiscount() != null) {
                long M = watchCatalogue.getDiscount().getBundleSize();
                result += watchCatalogue.getUnitPrice() * (N % M) + watchCatalogue.getDiscount().getPrice() * (N / M);
            } else {
                result += watchCatalogue.getUnitPrice() * N;
            }
        }
        return result;
    }

    private void countWatches(List<String> watches, Map<String, Long> counts) {
        for (String id: watches) {
            if (counts.containsKey(id)) {
                counts.put(id, counts.get(id) + 1);
            } else {
                counts.put(id, 1l);
            }
        }
    }
}
