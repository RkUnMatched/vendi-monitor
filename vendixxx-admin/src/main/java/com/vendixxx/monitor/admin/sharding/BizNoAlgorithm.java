package com.vendixxx.monitor.admin.sharding;

import com.google.common.collect.Lists;
import com.vendixxx.monitor.common.utils.CollectionUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 分片规则
 *
 * @author liuzheng
 * @date 2021-01-25
 * @since 2021
 */
@Component
public class BizNoAlgorithm implements PreciseShardingAlgorithm<String> {

    private Object lock = new Object();

    private final static Map<String, List<String>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {

        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalStateException("no sharding tables：" + preciseShardingValue.getLogicTableName() + "." + preciseShardingValue.getColumnName());
        }
        String value = preciseShardingValue.getValue();
        String key = preciseShardingValue.getLogicTableName() + "." + preciseShardingValue.getColumnName();
        int hashCode = value.hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        int size = collection.size();

        List<String> cacheList = cacheMap.get(key);
        if (cacheList == null) {
            synchronized (lock) {
                cacheList = cacheMap.get(key);
                if (cacheList == null) {
                    cacheList = Lists.newArrayList(collection);
                    cacheMap.put(key, cacheList);
                }
            }
        }
        String quote = cacheList.get(hashCode % size);
        return quote;
    }


}
