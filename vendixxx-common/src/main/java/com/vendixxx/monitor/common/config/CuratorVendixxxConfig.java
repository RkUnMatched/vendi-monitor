package com.vendixxx.monitor.common.config;

/**
 * @author liuzheng
 * @date 2021-01-14
 * @since 2021
 */
public interface CuratorVendixxxConfig {

    String basePath = "vendixxx:/service";

    String env = "env";

    String degrade = "degrade";

    String controllerMapping = "controllerMapping";

    String methodMapping = "methodMapping";

    int INIT_ONE = 1;

    int INIT_DIRECT_REF = 10;

    int INIT_THIRD_REF = 100;

    int NUM_0 = 0;

    int NUM_1 = 1;

    String EMPTY_STR = "";


}
