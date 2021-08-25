package com.vendixxx.monitor.admin.core;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author liuzheng
 * @date 2021-01-20
 * @since 2021
 */
@Data
public class ComplexData {

    List<Company> company;

    Set<String> names;

    BigDecimal num;

    String amount;

}
