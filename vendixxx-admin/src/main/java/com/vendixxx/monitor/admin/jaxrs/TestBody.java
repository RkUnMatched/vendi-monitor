package com.vendixxx.monitor.admin.jaxrs;

import lombok.Data;

import java.util.List;

/**
 * @author liuzheng
 * @date 2021-01-29
 * @since 2021
 */
@Data
public class TestBody {

    String name;
    List<Integer> list;
    List<Helloworld> helloworldList;
}
