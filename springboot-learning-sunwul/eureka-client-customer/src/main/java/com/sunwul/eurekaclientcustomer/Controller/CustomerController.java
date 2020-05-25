package com.sunwul.eurekaclientcustomer.Controller;

import com.sunwul.eurekaclientcustomer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*****
 * @author: Sunwul
 * @date: 2020/1/15 13:46
 * PS: 
 */
@RestController
public class CustomerController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public String customer(){
        return helloService.helloService();
    }
}
