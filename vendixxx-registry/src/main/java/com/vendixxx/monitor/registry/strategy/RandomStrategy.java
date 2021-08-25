package com.vendixxx.monitor.registry.strategy;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

/**
 * 随机策略
 *
 * @author liuzheng
 * @date 2021-01-20
 */
@Slf4j
public class RandomStrategy implements Strategy {

    private final Random random =   new Random();

    @Override
    public <T> T getInstance(List<T> all) {
        if(all.size() == 0) {
            return null;
        } else {
            int thisIndex = random.nextInt(all.size());

            log.debug("choose index:{} from all: {}", thisIndex, all);
            return  all.get(thisIndex);
        }
    }
}
