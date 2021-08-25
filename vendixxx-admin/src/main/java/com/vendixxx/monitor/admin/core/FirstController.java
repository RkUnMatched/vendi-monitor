package com.vendixxx.monitor.admin.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.vendixxx.monitor.client.caller.RpcCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author liuzheng
 * @date 2021-01-14
 * @since 2021
 */
@RestController
@RequestMapping("/test")
public class FirstController {

    @Autowired
    private RpcCaller rpcCaller;

    @Autowired
    JointService jointService;

    @Autowired
    JointService2 jointService2;

    @Autowired
    AnnoJointService annoJointService;

    @ResponseBody
    @RequestMapping("/getCompany")
    //@ServiceMeta(application = "monitor",service = "monitor.getCompany")
    public ComplexData getCompany(@RequestBody Company company){
        ComplexData complexData = new ComplexData();
        complexData.setAmount("1000");
        complexData.setNum(new BigDecimal("5.55"));
        Company company1 = new Company();
        company1.setName("vendixxx");
        company1.setBoss("CEO");
        complexData.setCompany(Lists.newArrayList(company1));
        complexData.setNames(Sets.newHashSet("郑爽","张瀚"));
        return complexData;
    }

    @ResponseBody
    @RequestMapping("/testName")
    public Company testName(@RequestBody Company company){
        ComplexData company1 = rpcCaller.call("monitor.getCompany",company, ComplexData.class);
        Map<String,Object> map = Maps.newHashMap();
        map.put("name","刘征");
        map.put("age",12);
        Company company2 = rpcCaller.getcall("monitor.testGet",map, Company.class);
        return company;
    }


    @ResponseBody
    @RequestMapping("/testGet")
    //@ServiceMeta(application = "monitor" ,service = "monitor.testGet")
    public Company testGet(@RequestParam(value = "name")String name,
                           @RequestParam(value = "age")int age){
        Company company1 = new Company();
        company1.setName(name);
        company1.setBoss(name);
        company1.setAge(age);
        return company1;
    }

    @ResponseBody
    @RequestMapping("/testJoint")
    public Company testJoint(@RequestBody Company company){
        //jointService.validate("m1");
        //jointService2.validate("m2");
        ComplexData complexData = new ComplexData();
        complexData.setAmount("123.45");
        complexData.setNames(Sets.newHashSet("西毒欧阳锋","北丐洪七公"));
        complexData.setNum(BigDecimal.ONE);
        Company company1 = new Company();
        company1.setBoss("影魔"+System.currentTimeMillis());
        company1.setName("蓝猫"+System.currentTimeMillis());
        company1.setAge(20);
        complexData.setCompany(Lists.newArrayList(company1));
        annoJointService.validate(complexData);
        /*annoJointService.validate1("m2");
        annoJointService.validate3();
        annoJointService.validate2("m1","m2");*/
        return company;
    }



}
