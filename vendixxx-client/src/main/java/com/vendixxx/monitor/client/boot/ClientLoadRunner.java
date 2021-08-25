package com.vendixxx.monitor.client.boot;



import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.registry.listener.BeanInit;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * curator 客户端
 * @author liuzheng
 * @date 2021-01-14
 * @since 2021
 */
public class ClientLoadRunner {

    private static AtomicBoolean initialized = new AtomicBoolean(false);


    public static void doInit(MonitorCommonProperties monitorCommonProperties,String ...packageNames) throws Exception {
        if (!initialized.compareAndSet(false, true)) {
            return;
        }
        try {
            ServiceLoader<BeanInit> loader = ServiceLoader.load(BeanInit.class);
            List<BeanInit> initList = new ArrayList<BeanInit>();
            for (BeanInit initFunc : loader) {
                initList.add(initFunc);
            }
            Collections.sort(initList, Comparator.comparing(BeanInit::ordered));
            for(BeanInit beanInit:initList){
                beanInit.processBeforeInitialization(monitorCommonProperties);
                beanInit.init(monitorCommonProperties,packageNames);
                beanInit.procesAfterInitialization(monitorCommonProperties);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Error error) {
            error.printStackTrace();
            throw error;
        }
    }

    public static void doClose(String ...packageName) {
        try {
            ServiceLoader<BeanInit> loader = ServiceLoader.load(BeanInit.class);
            List<BeanInit> initList = new ArrayList<BeanInit>();
            for (BeanInit initFunc : loader) {
                initList.add(initFunc);
            }
            Collections.sort(initList, Comparator.comparing(BeanInit::ordered));
            for(BeanInit beanInit:initList){
                beanInit.destroy();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } catch (Error error) {
            error.printStackTrace();
            throw error;
        }
    }

    private ClientLoadRunner() {

    }

}
