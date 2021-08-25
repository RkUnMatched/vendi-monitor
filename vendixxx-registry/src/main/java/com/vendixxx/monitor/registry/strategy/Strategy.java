package com.vendixxx.monitor.registry.strategy;

import java.util.List;

/**
 * 负载均衡策略
 *
 * @author liuzheng
 * @date 2021-01-20
 */
public interface Strategy {

    public <T> T getInstance(List<T> all);

}
