package system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 10:44 2018/3/14
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String test(){
        return "hello";
    }
}
