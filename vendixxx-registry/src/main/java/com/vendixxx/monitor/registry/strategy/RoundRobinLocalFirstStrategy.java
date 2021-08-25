package com.vendixxx.monitor.registry.strategy;

import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.common.utils.NetworkUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本地优先策略
 *
 * @author liuzheng
 * @date 2021-01-20
 */
public class RoundRobinLocalFirstStrategy implements Strategy {

    private final AtomicInteger index = new AtomicInteger(0);
    private final static  String localIP = NetworkUtils.getIp();

    @Override
    public <T> T getInstance(List<T> all) {
        if (all == null || all.size() == 0) {
            return null;
        }
        /*if(all.size() == 1){
            return all.get(0);
        }*/

        /** 如果本地IP匹配成功，则优先返回本地*/
        if( all.get(0) instanceof InstanceDetail){
            for(T ins : all){
                InstanceDetail serviceInstance = (InstanceDetail)ins;
                String remoteIp = serviceInstance.getAddress();
                if(StringUtils.equals(localIP,remoteIp.split(":")[0])){
                    return ins;
                }
            }
        }
        int thisIndex = Math.abs(index.getAndIncrement());
        return all.get(thisIndex % all.size());
    }


}