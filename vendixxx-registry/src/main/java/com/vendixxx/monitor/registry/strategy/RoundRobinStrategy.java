package com.vendixxx.monitor.registry.strategy;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Random robin 选择
 */
public class RoundRobinStrategy implements Strategy {
    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public <T> T getInstance(List<T> all) {
        if (all == null || all.size() == 0) {
            return null;
        }
        int thisIndex = Math.abs(index.getAndIncrement());
        return all.get(thisIndex % all.size());
    }


}